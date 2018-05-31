package com.kbearman.sunshine.model

/**
 * Created by Shiva on 5/30/2018.
 */
class WeatherRepo private constructor()
{
    //PROPER API CALL
    //http://api.openweathermap.org/data/2.5/forecast?q=Atlanta&mode=json&cnt=5&units=imperial&APPID=c823a132edfb2ceb3700abee63ab4223

    var cityForecasts : Map<>
    init {

    }

    companion object {
        private val mInstance: WeatherRepo = WeatherRepo()

        @Synchronized
        fun getInstance(): WeatherRepo {
            return mInstance
        }
    }


}