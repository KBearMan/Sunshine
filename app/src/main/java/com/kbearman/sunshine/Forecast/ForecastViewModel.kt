package com.kbearman.sunshine.Forecast

import android.arch.lifecycle.ViewModel
import android.text.format.DateUtils
import android.util.Log
import com.kbearman.sunshine.model.DayWeather
import com.kbearman.sunshine.model.SimpleDayWeather
import com.kbearman.sunshine.model.WeatherRepo
import io.reactivex.rxkotlin.subscribeBy
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

/**
 * Created by Shiva on 5/31/2018.
 */
class ForecastViewModel : ViewModel()
{

    interface ForecastViewModelInteractor
    {
        fun newDataReceived()
        fun startAboutPage(info:String)
        fun startSingleDayActivity(day:DayWeather)
        fun showErrorMessage(message:String)
    }
    lateinit var activityInteractor:ForecastViewModelInteractor
    val weatherRepository = WeatherRepo.getInstance()
    var selectedCity = "Atlanta"
    var forecastList:ArrayList<DayWeather> = ArrayList()
    private val TAG = ForecastViewModel::class.java.simpleName
    var todaysWeather:DayWeather = SimpleDayWeather()
    init {
        Log.d(TAG,"ForecastViewModel initialized")
        weatherRepository.getForecastObservable().timeout(8,TimeUnit.SECONDS).subscribeBy(
                onNext = { days ->
                    for(day in days)
                    {
                        if(DateUtils.isToday(day.getDate().timeInMillis))
                        {
                            todaysWeather = day
                        }
                        else
                        {
                            forecastList.add(day)
                            forecastList.sortBy { it.getDate().timeInMillis }
                        }
                        activityInteractor.newDataReceived()
                    }
                },
                onError =  {
                    if(it is TimeoutException)
                    {
                        activityInteractor.showErrorMessage("Could not connect to weather service, please check your internet connection.")
                    }
                    else {
                        activityInteractor.showErrorMessage(it.message!!)
                    }
                },
                onComplete = { println("Done!") }
        )

        weatherRepository.getForecastByCity(selectedCity,Integer(5))
    }

    fun dayClicked(day:DayWeather)
    {
        weatherRepository.selectedDay = day
        activityInteractor.startSingleDayActivity(day)
    }

    fun aboutButtonPressed()
    {
        activityInteractor.startAboutPage("This is the multi-day forecast screen.")
    }
}