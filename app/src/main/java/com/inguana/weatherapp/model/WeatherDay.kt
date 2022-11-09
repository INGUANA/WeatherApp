package com.inguana.weatherapp.model

import com.google.gson.annotations.SerializedName

class WeatherDay(
    var date: String,
    var maxtempC: String,
    var maxtempF: String,
    var mintempC: String,
    var mintempF: String,
    @field:SerializedName(
        "hourly"
    ) var weatherHour: List<WeatherHour?>
) {

    override fun toString(): String {
        return "WeatherDay{" +
                "date='" + date + '\'' +
                ", maxtempC='" + maxtempC + '\'' +
                ", maxtempF='" + maxtempF + '\'' +
                ", mintempC='" + mintempC + '\'' +
                ", mintempF='" + mintempF + '\'' +
                ", weatherHour=" + weatherHour +
                '}'
    }
}