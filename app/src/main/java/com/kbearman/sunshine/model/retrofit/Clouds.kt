package com.kbearman.sunshine.model.retrofit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Clouds {

    @SerializedName("all")
    @Expose
    var all: Long? = null

}
