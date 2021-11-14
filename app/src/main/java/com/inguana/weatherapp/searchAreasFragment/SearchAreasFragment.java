package com.inguana.weatherapp.searchAreasFragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.inguana.weatherapp.R;
import com.inguana.weatherapp.adapters.AreaRecyclerViewAdapter;
import com.inguana.weatherapp.adapters.interfaces.HomeActivityFunctionalities;
import com.inguana.weatherapp.adapters.interfaces.OnAreaListener;
import com.inguana.weatherapp.model.Area;
import com.inguana.weatherapp.network.networkModel.response.Request;
import com.inguana.weatherapp.utils.ErrorMessageSnackbar;
import com.inguana.weatherapp.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class SearchAreasFragment extends Fragment implements OnAreaListener, LifecycleObserver {

    private RecyclerView recyclerViewSearchCitiesFragment;
    private AreaRecyclerViewAdapter areaRecyclerViewAdapter;
    private SearchAreasViewModel searchAreasViewModel;
    private SearchView searchViewSearchCitiesFragment;
    private NavController navController;
    private HomeActivityFunctionalities homeActivityFunctionalities;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            homeActivityFunctionalities = (HomeActivityFunctionalities) context;
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        searchAreasViewModel = new ViewModelProvider(requireActivity()).get(SearchAreasViewModel.class);
        navController = NavHostFragment.findNavController(this);

        subscribeObservers();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.search_areas_fragment, container, false);
        searchViewSearchCitiesFragment = view.findViewById(R.id.searchViewSearchCitiesFragment);
        recyclerViewSearchCitiesFragment = view.findViewById(R.id.recyclerViewSearchCitiesFragment);

        initializeRecyclerView();

        searchViewSearchCitiesFragment.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(newText.isEmpty()) {
                    areaRecyclerViewAdapter.setAreaNameList(setFavouriteList());
                } else {
                    areaRecyclerViewAdapter.displayProgressBar();
                    searchAreasViewModel.searchWeather(newText);
                }
                return false;
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initializeFavouriteAreas();
    }

    private void initializeFavouriteAreas() {
        if(searchViewSearchCitiesFragment.getQuery().length() == 0) {
            areaRecyclerViewAdapter.setAreaNameList(setFavouriteList());
        }
    }

    private List<Request> setFavouriteList() {
        List<String> favouriteList;
        if(homeActivityFunctionalities.getFavouriteSet() == null) {
            favouriteList = new ArrayList<>();
        } else {
            favouriteList = new ArrayList<String>(homeActivityFunctionalities.getFavouriteSet());
        }
        return Utils.convertArrayListToRequestList(favouriteList);
    }


    private void initializeRecyclerView() {
        areaRecyclerViewAdapter = new AreaRecyclerViewAdapter(this);
        recyclerViewSearchCitiesFragment.setAdapter(areaRecyclerViewAdapter);
        recyclerViewSearchCitiesFragment.setLayoutManager(new LinearLayoutManager(getContext()));

    }

    private void subscribeObservers() {

        searchAreasViewModel.getArea().observe(this, new Observer<Area>() {
            @Override
            public void onChanged(Area area) {
                if (area != null) {
                    searchAreasViewModel.setArea(area);
                    areaRecyclerViewAdapter.setAreaNameList(area.getRequest());
                    initializeFavouriteAreas();
                }
            }
        });

        searchAreasViewModel.getErrorMessage().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String errorMessage) {
                ErrorMessageSnackbar.showErrorMessage(getView(), errorMessage);
                areaRecyclerViewAdapter.hideLoading();
                areaRecyclerViewAdapter.setQueryError();
            }
        });
    }

    @Override
    public void onAreaClick(String areaName) {
        searchAreasViewModel.setSearchQuery(areaName);
        navController.navigate(R.id.action_searchAreasFragment_to_fiveDaysForecastFragment);
    }


}
