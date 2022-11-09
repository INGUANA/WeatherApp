package com.inguana.weatherapp.network.networkModel.response

class WeatherOneStringValueObject(var value: String) {

    override fun toString(): String {
        return "WeatherOneValueObject{" +
                "value='" + value + '\'' +
                '}'
    }
}