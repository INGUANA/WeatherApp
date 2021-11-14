package com.inguana.weatherapp.network;

import com.inguana.weatherapp.network.networkModel.response.WeatherGETResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherAppApi {

    @GET("weather.ashx")
    Call<WeatherGETResponse> searchWeather(
            @Query("key") String key,
            @Query("q") String q,
            @Query("num_of_days") String numOfDays,
            @Query("tp") String tp,
            @Query("mca") String mca,
            @Query("format") String format);

}