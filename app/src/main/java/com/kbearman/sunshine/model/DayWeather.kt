package com.kbearman.sunshine.model

import java.util.*

/**
 * Created by Shiva on 5/31/2018.
 */

interface DayWeather {
    fun getCity(): String
    fun getDate(): Calendar
    fun getDay(): String
    fun getDescription(): String
    fun getLowTemp(): Double?
    fun getHighTemp(): Double?
    fun getIcon():Int
    fun getMetrics(): Map<String, String>
}