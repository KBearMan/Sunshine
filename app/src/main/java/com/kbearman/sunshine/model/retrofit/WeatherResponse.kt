package com.kbearman.sunshine.model.retrofit


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class WeatherResponse internal constructor() {

    @SerializedName("cod")
    @Expose
    var cod: String? = null
    @SerializedName("message")
    @Expose
    var message: Double? = null
    @SerializedName("cnt")
    @Expose
    var cnt: Long? = null
    @SerializedName("list")
    @Expose
    private var list:java.util.List<List<Any?>>? = null
    @SerializedName("city")
    @Expose
    var city: City? = null
}
