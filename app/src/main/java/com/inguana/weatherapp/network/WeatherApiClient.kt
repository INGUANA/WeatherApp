package com.inguana.weatherapp.network

import androidx.lifecycle.MutableLiveData
import com.inguana.weatherapp.model.Area
import com.inguana.weatherapp.network.networkModel.response.WeatherGETResponse
import com.inguana.weatherapp.utils.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherApiClient private constructor() {
    val errorMessage: MutableLiveData<String> = MutableLiveData()
    val area: MutableLiveData<Area?> = MutableLiveData()

    fun searchWeather(searchQuery: String?) {
        val weatherAppApi = ServiceGenerator.weatherAppApi
        val responseCall = weatherAppApi
            .searchWeather(
                Constants.API_KEY,
                searchQuery,
                "5",
                "1",
                "no",
                "json"
            )
        responseCall!!.enqueue(object : Callback<WeatherGETResponse> {
            override fun onResponse(
                call: Call<WeatherGETResponse>,
                response: Response<WeatherGETResponse>
            ) {
                if (response.code() == 200) {
                    val areaFromResponse = response.body()?.data
                    if (areaFromResponse == null) {
                        errorMessage.setValue("An error occurred while fetching data.")
                        area.setValue(null)
                    } else {
                        area.setValue(areaFromResponse)
                    }
                } else {
                    errorMessage.setValue("An error occurred while fetching data.")
                    area.setValue(null)
                }
            }

            override fun onFailure(call: Call<WeatherGETResponse>, t: Throwable) {
                errorMessage.setValue("An error occurred while fetching data.")
                area.setValue(null)
            }
        })
    }

    companion object {
        var instance: WeatherApiClient? = null
            get() {
                if (field == null) {
                    field = WeatherApiClient()
                }
                return field
            }
            private set
    }
}