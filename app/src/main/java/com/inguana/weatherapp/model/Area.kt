package com.inguana.weatherapp.model;

import com.google.gson.annotations.SerializedName;
import com.inguana.weatherapp.network.networkModel.response.Request;

import java.util.List;

public class Area {

    @SerializedName("weather")
    private List<WeatherDay> weatherDayList;

    @SerializedName("request")
    private List<Request> request;

    //=================================================
    //TODO: implement current condition to the app
    //=================================================

    public Area(List<WeatherDay> weatherDayList, List<Request> request) {
        this.weatherDayList = weatherDayList;
        this.request = request;
    }

    public List<WeatherDay> getWeatherDayList() {
        return weatherDayList;
    }

    public void setWeatherDayList(List<WeatherDay> weatherDayList) {
        this.weatherDayList = weatherDayList;
    }

    public List<Request> getRequest() {
        return request;
    }

    public void setRequest(List<Request> request) {
        this.request = request;
    }

    @Override
    public String toString() {
        return "Data{" +
                "weatherDayList=" + weatherDayList +
                ", request=" + request +
                '}';
    }
}
