package com.kbearman.sunshine.model.retrofit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class WeatherResponse {

    @SerializedName("city")
    @Expose
    var city: City? = null
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
    var list: java.util.List<com.kbearman.sunshine.model.retrofit.List>? = null

}
