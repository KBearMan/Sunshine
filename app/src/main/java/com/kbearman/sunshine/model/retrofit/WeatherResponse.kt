package com.kbearman.sunshine.model.retrofit

/**
 * Created by Shiva on 5/30/2018.
 */

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class WeatherResponse {

    @SerializedName("cod")
    @Expose
    var cod: String? = null
    @SerializedName("message")
    @Expose
    var message: Double? = null
    @SerializedName("city")
    @Expose
    var city: City? = null
    @SerializedName("cnt")
    @Expose
    var cnt: Long? = null
    @SerializedName("list")
    @Expose
    var list: List<com.kbearman.sunshine.model.retrofit.List<Any?>>? = null

}
