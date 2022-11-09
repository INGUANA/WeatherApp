package com.inguana.weatherapp.network;

import static com.inguana.weatherapp.utils.Constants.BASE_URL;
import static com.inguana.weatherapp.utils.Constants.NETWORK_TIMEOUT;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {

    private static OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
            .connectTimeout(NETWORK_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(NETWORK_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(NETWORK_TIMEOUT, TimeUnit.SECONDS)
            .build();

    private static Retrofit.Builder retrofitBuilder =
            new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okHttpClient)
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = retrofitBuilder.build();

    private static WeatherAppApi weatherAppApi = retrofit.create(WeatherAppApi.class);

    public static WeatherAppApi getWeatherAppApi() {
        return weatherAppApi;
    }
}
