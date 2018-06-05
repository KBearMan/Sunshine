package com.kbearman.sunshine.model

import java.util.*

/**
 * Created by Shiva on 5/31/2018.
 */

class SimpleDayWeather : DayWeather
{
    private lateinit var day: String
    private lateinit var city:String
    private lateinit var date: Calendar
    private lateinit var description: String
    private var highTemp: Double? = 0.0
    private var lowTemp: Double? = 0.0
    private var humidity: Int? = 0
    private lateinit var pressure: String
    private lateinit var wind: String
    private var icon: Int = 0

    fun setDay(day:String)
    {
      this.day = day
    }

    fun setCity(city:String)
    {
        this.city = city
    }

    fun setDate(date:Calendar)
    {
        this.date = date
    }
    fun setDescription(description:String)
    {
        this.description = description
    }
    fun setHighTemp(highTemp: Double?)
    {
        this.highTemp = highTemp
    }

    fun setlowTemp(lowTemp: Double?)
    {
        this.lowTemp = lowTemp
    }

    fun setHumidity(humidity: Int?)
    {
        this.humidity = humidity
    }

    fun setPressure(pressure:String)
    {
        this.pressure = pressure
    }

    fun setWind(wind:String)
    {
        this.wind = wind
    }

    fun setIcon(id:Int)
    {
        this.icon = id
    }

    override fun getIcon(): Int {
        return icon
    }
    override fun getCity(): String {
        return city
    }

    override fun getDate(): Calendar {
        return date
    }

    override fun getDay(): String {
        return day
    }

    override fun getDescription(): String {
        return description
    }

    override fun getLowTemp(): Double? {
        return lowTemp
    }

    override fun getHighTemp(): Double? {
        return highTemp
    }

    override fun getMetrics(): Map<String, String> {
        return mapOf(
                "Humidity" to humidity.toString()+"%",
                "Pressure" to pressure,
                "Wind" to wind)

    }

}