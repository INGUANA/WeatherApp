package com.inguana.weatherapp.network.networkModel.response;

import com.google.gson.annotations.SerializedName;
import com.inguana.weatherapp.model.Area;

public class WeatherGETResponse {

    @SerializedName("data")
    private Area area;

    public WeatherGETResponse(Area area) {
        this.area = area;
    }

    public Area getData() {
        return area;
    }

    public void setData(Area area) {
        this.area = area;
    }

    @Override
    public String toString() {
        return "WeatherGETResponse{" +
                "data=" + area +
                '}';
    }
}
