package com.inguana.weatherapp.network.repositories

import androidx.lifecycle.MutableLiveData
import com.inguana.weatherapp.model.Area
import com.inguana.weatherapp.network.WeatherApiClient

class WeatherRepository private constructor() {
    init {
        weatherApiClient = WeatherApiClient.instance
    }

    fun searchWeather(searchQuery: String?) {
        weatherApiClient!!.searchWeather(searchQuery)
    }

    val area: MutableLiveData<Area?>?
        get() = weatherApiClient?.area
    val errorMessage: MutableLiveData<String>?
        get() = weatherApiClient?.errorMessage

    companion object {
        var instance: WeatherRepository? = null
            get() {
                if (field == null) {
                    field = WeatherRepository()
                }
                return field
            }
            private set
        private var weatherApiClient: WeatherApiClient? = null
    }
}