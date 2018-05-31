package com.kbearman.sunshine.model

import com.kbearman.sunshine.model.retrofit.WeatherResponse
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.http.GET




/**
 * Created by Shiva on 5/30/2018.
 */
class WeatherRepo private constructor(var retrofit: Any)
{
    //PROPER API CALL
    //http://api.openweathermap.org/data/2.5/forecast?q=Atlanta&mode=json&cnt=5&units=imperial&APPID=c823a132edfb2ceb3700abee63ab4223

    var cityForecasts : Map<String,>

    init {
        retrofit = Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/data/2.5/")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()

    }


    companion object {
        private val mInstance: WeatherRepo = WeatherRepo()

        @Synchronized
        fun getInstance(): WeatherRepo {
            return mInstance
        }
    }


}