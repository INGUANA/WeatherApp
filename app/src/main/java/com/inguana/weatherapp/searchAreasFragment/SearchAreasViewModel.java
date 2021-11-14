package com.inguana.weatherapp.searchAreasFragment;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.inguana.weatherapp.model.Area;
import com.inguana.weatherapp.network.repositories.WeatherRepository;

public class SearchAreasViewModel extends ViewModel {

    private WeatherRepository weatherRepository;
    private String searchQuery;
    private Area currentArea;

    public SearchAreasViewModel() {
        weatherRepository = WeatherRepository.getInstance();
        searchQuery = "";
        currentArea = null;
    }

    public void setSearchQuery(String searchQuery) {
        this.searchQuery = searchQuery;
    }

    public void setArea(Area currentArea) {
        this.currentArea = currentArea;
    }

    public Area getCurrentArea() {
        return currentArea;
    }

    public void searchWeather(String searchQuery) {
        this.searchQuery = searchQuery;
        weatherRepository.searchWeather(searchQuery);
    }

    public void refreshWeather() {
        weatherRepository.searchWeather(searchQuery);
    }

    public MutableLiveData<Area> getArea() {
        return weatherRepository.getArea();
    }

    public MutableLiveData<String> getErrorMessage() {
        return weatherRepository.getErrorMessage();
    }
}
