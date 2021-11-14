package com.inguana.weatherapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherDay {

    private String date;
    private String maxtempC;
    private String maxtempF;
    private String mintempC;
    private String mintempF;

    @SerializedName("hourly")
    private List<WeatherHour> weatherHour;

    public WeatherDay(String date, String maxtempC, String maxtempF, String mintempC, String mintempF, List<WeatherHour> weatherHour) {
        this.date = date;
        this.maxtempC = maxtempC;
        this.maxtempF = maxtempF;
        this.mintempC = mintempC;
        this.mintempF = mintempF;
        this.weatherHour = weatherHour;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMaxtempC() {
        return maxtempC;
    }

    public void setMaxtempC(String maxtempC) {
        this.maxtempC = maxtempC;
    }

    public String getMaxtempF() {
        return maxtempF;
    }

    public void setMaxtempF(String maxtempF) {
        this.maxtempF = maxtempF;
    }

    public String getMintempC() {
        return mintempC;
    }

    public void setMintempC(String mintempC) {
        this.mintempC = mintempC;
    }

    public String getMintempF() {
        return mintempF;
    }

    public void setMintempF(String mintempF) {
        this.mintempF = mintempF;
    }

    public List<WeatherHour> getWeatherHour() {
        return weatherHour;
    }

    public void setWeatherHour(List<WeatherHour> weatherHour) {
        this.weatherHour = weatherHour;
    }

    @Override
    public String toString() {
        return "WeatherDay{" +
                "date='" + date + '\'' +
                ", maxtempC='" + maxtempC + '\'' +
                ", maxtempF='" + maxtempF + '\'' +
                ", mintempC='" + mintempC + '\'' +
                ", mintempF='" + mintempF + '\'' +
                ", weatherHour=" + weatherHour +
                '}';
    }
}
