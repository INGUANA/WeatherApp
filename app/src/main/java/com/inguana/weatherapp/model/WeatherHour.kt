package com.inguana.weatherapp.model;

import com.google.gson.annotations.SerializedName;
import com.inguana.weatherapp.network.networkModel.response.WeatherOneStringValueObject;

import java.util.List;

public class WeatherHour {

    private String time;
    private String tempC;
    private String tempF;
    @SerializedName("windspeedMiles")
    private String windSpeedMiles;
    @SerializedName("windspeedKmph")
    private String windSpeedKmph;
    private List<WeatherOneStringValueObject> weatherIconUrl;
    private List<WeatherOneStringValueObject> weatherDesc;
    private String humidity;
    private String pressure;
    @SerializedName("FeelsLikeC")
    private String feelsLikeC;
    @SerializedName("FeelsLikeF")
    private String feelsLikeF;
    @SerializedName("chanceofrain")
    private String chanceOfRain;

    public WeatherHour(String time, String tempC, String tempF, String windSpeedMiles, String windSpeedKmph, String humidity,
                       String pressure, String feelsLikeC, String feelsLikeF, String chanceOfRain,
                       List<WeatherOneStringValueObject> weatherIconUrl, List<WeatherOneStringValueObject> weatherDesc) {
        this.time = time;
        this.tempC = tempC;
        this.tempF = tempF;
        this.windSpeedMiles = windSpeedMiles;
        this.windSpeedKmph = windSpeedKmph;
        this.humidity = humidity;
        this.pressure = pressure;
        this.feelsLikeC = feelsLikeC;
        this.feelsLikeF = feelsLikeF;
        this.chanceOfRain = chanceOfRain;
        this.weatherIconUrl = weatherIconUrl;
        this.weatherDesc = weatherDesc;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTempC() {
        return tempC;
    }

    public void setTempC(String tempC) {
        this.tempC = tempC;
    }

    public String getTempF() {
        return tempF;
    }

    public void setTempF(String tempF) {
        this.tempF = tempF;
    }

    public String getWindSpeedMiles() {
        return windSpeedMiles;
    }

    public void setWindSpeedMiles(String windSpeedMiles) {
        this.windSpeedMiles = windSpeedMiles;
    }

    public String getWindSpeedKmph() {
        return windSpeedKmph;
    }

    public void setWindSpeedKmph(String windSpeedKmph) {
        this.windSpeedKmph = windSpeedKmph;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getFeelsLikeC() {
        return feelsLikeC;
    }

    public void setFeelsLikeC(String feelsLikeC) {
        this.feelsLikeC = feelsLikeC;
    }

    public String getFeelsLikeF() {
        return feelsLikeF;
    }

    public void setFeelsLikeF(String feelsLikeF) {
        this.feelsLikeF = feelsLikeF;
    }

    public String getChanceOfRain() {
        return chanceOfRain;
    }

    public void setChanceOfRain(String chanceOfRain) {
        this.chanceOfRain = chanceOfRain;
    }

    public List<WeatherOneStringValueObject> getWeatherIconUrl() {
        return weatherIconUrl;
    }

    public void setWeatherIconUrl(List<WeatherOneStringValueObject> weatherIconUrl) {
        this.weatherIconUrl = weatherIconUrl;
    }

    public List<WeatherOneStringValueObject> getWeatherDesc() {
        return weatherDesc;
    }

    public void setWeatherDesc(List<WeatherOneStringValueObject> weatherDesc) {
        this.weatherDesc = weatherDesc;
    }

    @Override
    public String toString() {
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
                '}';
    }
}
