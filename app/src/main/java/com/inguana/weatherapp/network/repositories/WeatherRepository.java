package com.inguana.weatherapp.network.repositories;

import androidx.lifecycle.MutableLiveData;

import com.inguana.weatherapp.model.Area;
import com.inguana.weatherapp.network.WeatherApiClient;

public class WeatherRepository {

    private static WeatherRepository instance;
    private static WeatherApiClient weatherApiClient;

    public static WeatherRepository getInstance() {
        if(instance == null) {
            instance = new WeatherRepository();
        }
        return instance;
    }

    private WeatherRepository() {
        weatherApiClient = WeatherApiClient.getInstance();
    }

    public void searchWeather(String searchQuery) {
        weatherApiClient.searchWeather(searchQuery);
    }

    public MutableLiveData<Area> getArea() {
        return weatherApiClient.getArea();
    }

    public MutableLiveData<String> getErrorMessage() {
        return weatherApiClient.getErrorMessage();
    }
}
