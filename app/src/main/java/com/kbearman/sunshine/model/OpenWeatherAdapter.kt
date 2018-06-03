package com.kbearman.sunshine.model

import com.kbearman.sunshine.model.retrofit.WeatherResponse
import java.text.SimpleDateFormat
import java.util.*

class OpenWeatherAdapter
{
    companion object {
        fun convertWeatherResponseToDayWeather(weatherResponse: WeatherResponse): List<SimpleDayWeather> {
            var weatherList = ArrayList<SimpleDayWeather>()
            for(day in weatherResponse.list!!)
            {
                var dayWeather = SimpleDayWeather()
                dayWeather.setCity(weatherResponse.city.toString())
                var sdf : SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                var cal:Calendar = Calendar.getInstance()
                cal.time = sdf.parse(day.dtTxt)
                dayWeather.setDate(cal)
                dayWeather.setDay(cal.time.day.toString())
                dayWeather.setDescription(day.weather!!.get(0).description!!)
                dayWeather.setHighTemp(day.main?.tempMax!!)
                dayWeather.setlowTemp(day.main?.tempMin!!)
                dayWeather.setHumidity(day.main!!.humidity!!.toInt())
                dayWeather.setPressure(day.main!!.pressure.toString())
                dayWeather.setWind(day.wind!!.speed.toString())
                weatherList.add(dayWeather)
            }

            return weatherList
        }
    }
}