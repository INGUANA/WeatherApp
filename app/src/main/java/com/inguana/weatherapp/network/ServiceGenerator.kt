package com.inguana.weatherapp.network

import com.inguana.weatherapp.utils.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ServiceGenerator {
    private val okHttpClient = OkHttpClient().newBuilder()
        .connectTimeout(Constants.NETWORK_TIMEOUT.toLong(), TimeUnit.SECONDS)
        .readTimeout(Constants.NETWORK_TIMEOUT.toLong(), TimeUnit.SECONDS)
        .writeTimeout(Constants.NETWORK_TIMEOUT.toLong(), TimeUnit.SECONDS)
        .build()
    private val retrofitBuilder = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .client(okHttpClient)
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
    private val retrofit = retrofitBuilder.build()
    val weatherAppApi = retrofit.create(
        WeatherAppApi::class.java
    )
}