package com.inguana.weatherapp.network;

import androidx.lifecycle.MutableLiveData;

import com.inguana.weatherapp.model.Area;
import com.inguana.weatherapp.network.networkModel.response.WeatherGETResponse;
import com.inguana.weatherapp.utils.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherApiClient {

    private static WeatherApiClient instance;
    private MutableLiveData<String> errorMessage;
    private MutableLiveData<Area> area;

    public static WeatherApiClient getInstance() {
        if (instance == null) {
            instance = new WeatherApiClient();
        }
        return instance;
    }

    private WeatherApiClient() {
        area = new MutableLiveData<>();
        errorMessage = new MutableLiveData<>();
    }

    public void searchWeather(String searchQuery) {
        WeatherAppApi weatherAppApi = ServiceGenerator.getWeatherAppApi();
        Call<WeatherGETResponse> responseCall = weatherAppApi
                .searchWeather(Constants.API_KEY,
                        searchQuery,
                        "5",
                        "1",
                        "no",
                        "json");

        responseCall.enqueue(new Callback<WeatherGETResponse>() {
            @Override
            public void onResponse(Call<WeatherGETResponse> call, Response<WeatherGETResponse> response) {
                if (response.code() == 200) {
                    Area areaFromResponse = response.body().getData();
                    if (areaFromResponse == null) {
                        errorMessage.setValue("An error occurred while fetching data.");
                        area.setValue(null);
                    } else {
                        area.setValue(areaFromResponse);
                    }

                } else {
                    errorMessage.setValue("An error occurred while fetching data.");
                    area.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<WeatherGETResponse> call, Throwable t) {
                errorMessage.setValue("An error occurred while fetching data.");
                area.setValue(null);
            }
        });
    }

    public MutableLiveData<String> getErrorMessage() {
        return errorMessage;
    }

    public MutableLiveData<Area> getArea() {
        return area;
    }
}
