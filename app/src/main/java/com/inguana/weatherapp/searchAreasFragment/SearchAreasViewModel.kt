package com.inguana.weatherapp.searchAreasFragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.inguana.weatherapp.model.Area
import com.inguana.weatherapp.network.repositories.WeatherRepository

class SearchAreasViewModel : ViewModel() {
    private val weatherRepository: WeatherRepository?
    private var searchQuery: String?
    var currentArea: Area?
        private set

    init {
        weatherRepository = WeatherRepository.Companion.instance
        searchQuery = ""
        currentArea = null
    }

    fun setSearchQuery(searchQuery: String?) {
        this.searchQuery = searchQuery
    }

    fun setArea(currentArea: Area?) {
        this.currentArea = currentArea
    }

    fun searchWeather(searchQuery: String?) {
        this.searchQuery = searchQuery
        weatherRepository!!.searchWeather(searchQuery)
    }

    fun refreshWeather() {
        weatherRepository!!.searchWeather(searchQuery)
    }

    val area: MutableLiveData<Area?>?
        get() = weatherRepository?.area
    val errorMessage: MutableLiveData<String>?
        get() = weatherRepository?.errorMessage
}