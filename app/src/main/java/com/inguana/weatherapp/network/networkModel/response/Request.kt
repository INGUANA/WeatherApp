package com.inguana.weatherapp.network.networkModel.response

import com.google.gson.annotations.SerializedName

class Request {
    @SerializedName("type")
    var type: String? = null

    @SerializedName("query")
    var query: String? = null

    constructor() {}
    constructor(type: String?, query: String?) {
        this.type = type
        this.query = query
    }
}