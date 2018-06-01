package com.kbearman.sunshine.model

/**
 * Created by Shiva on 5/31/2018.
 */

interface DayWeather {
    fun getDay(): String
    fun getDescription(): String
    fun getLowTemp(): Integer
    fun getHighTemp(): Integer
    fun getMetrics(): Map<String, String>
}