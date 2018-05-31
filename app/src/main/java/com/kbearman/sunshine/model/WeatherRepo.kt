package com.kbearman.sunshine.model

import android.util.Log
import com.kbearman.sunshine.model.retrofit.List
import com.kbearman.sunshine.model.retrofit.OpenWeatherService
import com.kbearman.sunshine.model.retrofit.WeatherResponse
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET




/**
 * Created by Shiva on 5/30/2018.
 */
class WeatherRepo private constructor(var retrofit: Any) : IForecast
{
    var weatherObservable : PublishSubject<List<WeatherResponse>> = PublishSubject.create()

    lateinit var weatherService :OpenWeatherService

    override fun getForecastObservable(): Observable<com.kbearman.sunshine.model.retrofit.List<WeatherResponse>> {
                return weatherObservable
    }

    override fun getForecastByCity(cityName: String, dayCount: Integer) {
        weatherService.getCityForecast(cityName,dayCount.toInt())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result -> weatherObservable.onNext(result)},
                        { error -> Log.e("WeatherRepo",error.message) }
                )
    }
    //PROPER API CALL
    //http://api.openweathermap.org/data/2.5/forecast?q=Atlanta&mode=json&cnt=5&units=imperial&APPID=c823a132edfb2ceb3700abee63ab4223

    init {
        retrofit = Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/data/2.5/")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        weatherService = (retrofit as Retrofit?)!!.create(OpenWeatherService::class.java)
    }


    companion object {
        private val mInstance: WeatherRepo = WeatherRepo("")

        @Synchronized
        fun getInstance(): WeatherRepo {
            return mInstance
        }
    }


}