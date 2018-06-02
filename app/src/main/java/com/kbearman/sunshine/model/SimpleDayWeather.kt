package com.kbearman.sunshine.model

import java.util.*
import kotlin.collections.HashMap

/**
 * Created by Shiva on 5/31/2018.
 */

class SimpleDayWeather : DayWeather
{
    private lateinit var day: String
    private lateinit var city:String
    private lateinit var date: Date
    private lateinit var description: String
    private lateinit var highTemp: Integer
    private lateinit var lowTemp: Integer
    private lateinit var humidity: Integer
    private lateinit var pressure: String
    private lateinit var wind: String

    override fun getCity(): String {
        return city
    }

    override fun getDate(): Date {
        return date
    }

    override fun getDay(): String {
        return day
    }

    override fun getDescription(): String {
        return description
    }

    override fun getLowTemp(): Integer {
        return lowTemp
    }

    override fun getHighTemp(): Integer {
        return highTemp
    }

    override fun getMetrics(): Map<String, String> {
        return mapOf(
                "Humidity" to humidity.toString()+"%",
                "Pressure" to pressure,
                "Wind" to wind)

    }

}