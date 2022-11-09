package com.inguana.weatherapp.fiveDaysForecastFragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.inguana.weatherapp.R;
import com.inguana.weatherapp.adapters.ForecastRecyclerViewAdapter;
import com.inguana.weatherapp.adapters.interfaces.HomeActivityFunctionalities;
import com.inguana.weatherapp.adapters.interfaces.OnDayListener;
import com.inguana.weatherapp.adapters.interfaces.ShowProgressBar;
import com.inguana.weatherapp.model.Area;
import com.inguana.weatherapp.searchAreasFragment.SearchAreasViewModel;
import com.inguana.weatherapp.utils.ErrorMessageSnackbar;
import com.inguana.weatherapp.utils.Utils;

public class FiveDaysForecastFragment extends Fragment implements OnDayListener {

    private RecyclerView recyclerViewFiveDaysForecastFragment;
    private ForecastRecyclerViewAdapter forecastRecyclerViewAdapter;
    private SearchAreasViewModel searchAreasViewModel;
    private NavController navController;
    private SwipeRefreshLayout swipeRefreshFiveDayForecastFragment;
    private TextView areaFiveDayForecastFragment;
    private HomeActivityFunctionalities homeActivityFunctionalities;
    private ShowProgressBar showProgressBar;
    private ImageView favouriteAreaFiveDayForecastFragment;
    private String areaName;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            homeActivityFunctionalities = (HomeActivityFunctionalities) context;
            showProgressBar = (ShowProgressBar) context;
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

        View view = inflater.inflate(R.layout.five_day_forecast_fragment, container, false);
        recyclerViewFiveDaysForecastFragment = view.findViewById(R.id.recyclerViewFiveDaysForecastFragment);
        swipeRefreshFiveDayForecastFragment = view.findViewById(R.id.swipeRefreshFiveDayForecastFragment);
        areaFiveDayForecastFragment = view.findViewById(R.id.areaFiveDayForecastFragment);
        favouriteAreaFiveDayForecastFragment = view.findViewById(R.id.favouriteAreaFiveDayForecastFragment);

        initializeRecyclerView();

        swipeRefreshFiveDayForecastFragment.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                searchAreasViewModel.refreshWeather();
            }
        });

        favouriteAreaFiveDayForecastFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (homeActivityFunctionalities != null) {
                    onFavouriteFunctionality();
                    onFavouriteChangeDrawable();
                }
            }
        });

        makeInitialCalls();

        return view;
    }

    private void makeInitialCalls() {
        showProgressBar.showProgressBar(true);
        searchAreasViewModel.refreshWeather();
    }


    private void initializeRecyclerView() {
        forecastRecyclerViewAdapter = new ForecastRecyclerViewAdapter(this);
        recyclerViewFiveDaysForecastFragment.setAdapter(forecastRecyclerViewAdapter);
        recyclerViewFiveDaysForecastFragment.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
    }

    private void subscribeObservers() {

        searchAreasViewModel.getArea().observe(this, new Observer<Area>() {
            @Override
            public void onChanged(Area area) {
                if (area != null) {
                    showProgressBar.showProgressBar(false);
                    swipeRefreshFiveDayForecastFragment.setRefreshing(false);
                    searchAreasViewModel.setArea(area);
                    String currentAreaName = area.getRequest().get(0).getQuery();
                    areaName = currentAreaName;
                    areaFiveDayForecastFragment.setText(areaName);
                    if (homeActivityFunctionalities != null) {
                        onFavouriteChangeDrawable();
                    }
                    forecastRecyclerViewAdapter.setWeatherDayList(area.getWeatherDayList());
                }
            }
        });

        searchAreasViewModel.getErrorMessage().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String errorMessage) {
                ErrorMessageSnackbar.showErrorMessage(getView(), errorMessage);
                swipeRefreshFiveDayForecastFragment.setRefreshing(false);
            }
        });
    }

    @Override
    public void onDayClick(int position) {
        FiveDaysForecastFragmentDirections.ActionFiveDaysForecastFragmentToHourForecastFragment action =
                FiveDaysForecastFragmentDirections.actionFiveDaysForecastFragmentToHourForecastFragment(
                        position);
        navController.navigate(action);
    }

    private void onFavouriteChangeDrawable() {
        if (Utils.isFavourite(homeActivityFunctionalities.getFavouriteSet(), areaName)) {
            favouriteAreaFiveDayForecastFragment.setImageDrawable(AppCompatResources.getDrawable(getContext(), R.drawable.ic_baseline_favorite_24));
        } else {
            favouriteAreaFiveDayForecastFragment.setImageDrawable(AppCompatResources.getDrawable(getContext(), R.drawable.ic_round_favorite_border_24));
        }
    }


    private void onFavouriteFunctionality() {
        if (Utils.isFavourite(homeActivityFunctionalities.getFavouriteSet(), areaName)) {
            homeActivityFunctionalities.removeFavouriteSet(areaName);
        } else {
            homeActivityFunctionalities.setFavouriteSet(areaName);
        }
    }

}
