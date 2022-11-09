package com.inguana.weatherapp.model

import com.google.gson.annotations.SerializedName
import com.inguana.weatherapp.network.networkModel.response.Request

class Area     //=================================================
//TODO: implement current condition to the app
//=================================================
    (
    @field:SerializedName("weather") var weatherDayList: List<WeatherDay?>, @field:SerializedName(
    "request"
) var request: MutableList<Request?>
) {

    override fun toString(): String {
        return "Data{" +
                "weatherDayList=" + weatherDayList +
                ", request=" + request +
                '}'
    }
}