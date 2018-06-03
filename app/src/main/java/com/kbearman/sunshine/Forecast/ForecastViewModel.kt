package com.kbearman.sunshine.Forecast

import android.arch.lifecycle.ViewModel
import android.util.Log
import com.kbearman.sunshine.model.DayWeather
import com.kbearman.sunshine.model.SimpleDayWeather
import com.kbearman.sunshine.model.WeatherRepo
import io.reactivex.rxkotlin.subscribeBy

/**
 * Created by Shiva on 5/31/2018.
 */
class ForecastViewModel : ViewModel()
{
    val weatherRepository = WeatherRepo.getInstance()
    var selectedCity = "Atlanta"
    var forecastList:ArrayList<DayWeather> = ArrayList()
    private val TAG = ForecastViewModel::class.java.simpleName
    var todaysWeather:DayWeather = SimpleDayWeather()
    init {
        Log.d(TAG,"ForecastViewModel initialized")
        weatherRepository.getForecastObservable().subscribeBy(
                onNext = { days -> forecastList.addAll(days)},
                onError =  { it.printStackTrace() },
                onComplete = { println("Done!") }
        )

        weatherRepository.getForecastByCity(selectedCity,Integer(5))
    }

}