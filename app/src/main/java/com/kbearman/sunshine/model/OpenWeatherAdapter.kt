package com.kbearman.sunshine.model

import com.kbearman.sunshine.model.retrofit.WeatherResponse

class OpenWeatherAdapter
{
    companion object {
        fun convertWeatherResponseToDayWeather(weatherResponse: WeatherResponse): SimpleDayWeather {
            return SimpleDayWeather()
        }
    }
}