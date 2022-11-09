package com.inguana.weatherapp.model

import com.google.gson.annotations.SerializedName
import com.inguana.weatherapp.network.networkModel.response.WeatherOneStringValueObject

class WeatherHour(
    var time: String,
    var tempC: String,
    var tempF: String,
    @field:SerializedName(
        "windspeedMiles"
    ) var windSpeedMiles: String,
    @field:SerializedName("windspeedKmph") var windSpeedKmph: String,
    var humidity: String,
    var pressure: String,
    @field:SerializedName("FeelsLikeC") var feelsLikeC: String,
    @field:SerializedName(
        "FeelsLikeF"
    ) var feelsLikeF: String,
    @field:SerializedName("chanceofrain") var chanceOfRain: String,
    var weatherIconUrl: List<WeatherOneStringValueObject?>,
    var weatherDesc: List<WeatherOneStringValueObject?>
) {

    override fun toString(): String {
        return "WeatherHour{" +
                "time='" + time + '\'' +
                ", tempC='" + tempC + '\'' +
                ", tempF='" + tempF + '\'' +
                ", windSpeedMiles='" + windSpeedMiles + '\'' +
                ", windSpeedKmph='" + windSpeedKmph + '\'' +
                ", humidity='" + humidity + '\'' +
                ", pressure='" + pressure + '\'' +
                ", feelsLikeC='" + feelsLikeC + '\'' +
                ", feelsLikeF='" + feelsLikeF + '\'' +
                ", chanceOfRain='" + chanceOfRain + '\'' +
                ", weatherIconUrl='" + weatherIconUrl + '\'' +
                ", weatherDesc='" + weatherDesc + '\'' +
                '}'
    }
}