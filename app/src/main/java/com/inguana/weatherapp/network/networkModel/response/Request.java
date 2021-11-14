package com.inguana.weatherapp.network.networkModel.response;

import com.google.gson.annotations.SerializedName;

public class Request {

    @SerializedName("type")
    private String type;
    @SerializedName("query")
    private String query;

    public Request() {

    }

    public Request(String type, String query) {
        this.type = type;
        this.query = query;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
}
