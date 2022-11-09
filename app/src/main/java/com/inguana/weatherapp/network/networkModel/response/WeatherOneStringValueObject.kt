package com.inguana.weatherapp.network.networkModel.response;

public class WeatherOneStringValueObject {

    private String value;

    public WeatherOneStringValueObject(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "WeatherOneValueObject{" +
                "value='" + value + '\'' +
                '}';
    }
}
