package com.kbearman.sunshine.model.retrofit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class List<T> {

    @SerializedName("dt")
    @Expose
    var dt: Long? = null
    @SerializedName("temp")
    @Expose
    var temp: Temp? = null
    @SerializedName("pressure")
    @Expose
    var pressure: Double? = null
    @SerializedName("humidity")
    @Expose
    var humidity: Long? = null
    @SerializedName("weather")
    @Expose
    var weather: List<Weather>? = null
    @SerializedName("speed")
    @Expose
    var speed: Double? = null
    @SerializedName("deg")
    @Expose
    var deg: Long? = null
    @SerializedName("clouds")
    @Expose
    var clouds: Long? = null

}
