package com.inguana.weatherapp.network.networkModel.response

import com.google.gson.annotations.SerializedName
import com.inguana.weatherapp.model.Area

class WeatherGETResponse(@field:SerializedName("data") var data: Area) {

    override fun toString(): String {
        return "WeatherGETResponse{" +
                "data=" + data +
                '}'
    }
}