package com.inguana.weatherapp.hourForecastFragment;

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
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.inguana.weatherapp.R;
import com.inguana.weatherapp.adapters.HourForecastRecyclerViewAdapter;
import com.inguana.weatherapp.adapters.interfaces.HomeActivityFunctionalities;
import com.inguana.weatherapp.adapters.interfaces.ShowProgressBar;
import com.inguana.weatherapp.searchAreasFragment.SearchAreasViewModel;
import com.inguana.weatherapp.utils.Utils;

public class HourForecastFragment extends Fragment {
    private RecyclerView recyclerViewHourForecastFragment;
    private HourForecastRecyclerViewAdapter hourForecastRecyclerViewAdapter;
    private SearchAreasViewModel searchAreasViewModel;
    private NavController navController;
    private TextView areaHourForecastFragment;
    private ShowProgressBar showProgressBar;
    private HomeActivityFunctionalities homeActivityFunctionalities;
    private ImageView favouriteAreaHourForecastFragment;
    private int selectedDay;
    private String areaName;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            showProgressBar = (ShowProgressBar) context;
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
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.hour_forecast_fragment, container, false);
        recyclerViewHourForecastFragment = view.findViewById(R.id.recyclerViewHourForecastFragment);
        areaHourForecastFragment = view.findViewById(R.id.areaHourForecastFragment);
        favouriteAreaHourForecastFragment = view.findViewById(R.id.favouriteAreaHourForecastFragment);

        selectedDay = HourForecastFragmentArgs.fromBundle(getArguments()).getSelectedDay();
        areaName = searchAreasViewModel.getCurrentArea().getRequest().get(0).getQuery();

        areaHourForecastFragment.setText(areaName);
        if (homeActivityFunctionalities != null) {
            onFavouriteChangeDrawable();
        }
        initializeRecyclerView();

        favouriteAreaHourForecastFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (homeActivityFunctionalities != null) {
                    onFavouriteFunctionality();
                    onFavouriteChangeDrawable();
                }
            }
        });

        return view;
    }


    private void initializeRecyclerView() {
        hourForecastRecyclerViewAdapter = new HourForecastRecyclerViewAdapter();
        recyclerViewHourForecastFragment.setAdapter(hourForecastRecyclerViewAdapter);
        recyclerViewHourForecastFragment.setLayoutManager(new LinearLayoutManager(getContext()));

        hourForecastRecyclerViewAdapter.setWeatherHourList(searchAreasViewModel.getCurrentArea().getWeatherDayList().get(selectedDay).getWeatherHour());
    }

    private void onFavouriteChangeDrawable() {
        if (Utils.isFavourite(homeActivityFunctionalities.getFavouriteSet(), areaName)) {
            favouriteAreaHourForecastFragment.setImageDrawable(AppCompatResources.getDrawable(getContext(), R.drawable.ic_baseline_favorite_24));
        } else {
            favouriteAreaHourForecastFragment.setImageDrawable(AppCompatResources.getDrawable(getContext(), R.drawable.ic_round_favorite_border_24));
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
