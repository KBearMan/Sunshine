package com.kbearman.sunshine.model.retrofit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class List<T> {

    @SerializedName("dt")
    @Expose
    var dt: Long? = null
    @SerializedName("main")
    @Expose
    var main: Main? = null
    @SerializedName("weather")
    @Expose
    var weather: java.util.List<Weather>? = null
    @SerializedName("clouds")
    @Expose
    var clouds: Int? = null
    @SerializedName("wind")
    @Expose
    var wind: Wind? = null
    @SerializedName("rain")
    @Expose
    var rain: Double? = null
    @SerializedName("sys")
    @Expose
    var sys: Sys? = null
    @SerializedName("dt_txt")
    @Expose
    var dtTxt: String? = null

}
