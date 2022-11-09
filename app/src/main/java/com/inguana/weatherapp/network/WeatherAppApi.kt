package com.inguana.weatherapp.network

import com.inguana.weatherapp.network.networkModel.response.WeatherGETResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAppApi {
    @GET("weather.ashx")
    fun searchWeather(
        @Query("key") key: String?,
        @Query("q") q: String?,
        @Query("num_of_days") numOfDays: String?,
        @Query("tp") tp: String?,
        @Query("mca") mca: String?,
        @Query("format") format: String?
    ): Call<WeatherGETResponse>?
}