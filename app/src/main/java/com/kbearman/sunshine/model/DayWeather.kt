package com.kbearman.sunshine.model

import java.util.*

/**
 * Created by Shiva on 5/31/2018.
 */

interface DayWeather {
    fun getCity(): String
    fun getDate(): Date
    fun getDay(): String
    fun getDescription(): String
    fun getLowTemp(): Integer
    fun getHighTemp(): Integer
    fun getMetrics(): Map<String, String>
}