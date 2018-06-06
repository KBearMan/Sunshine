package com.kbearman.sunshine.SingleDayDetailedWeather

import android.arch.lifecycle.ViewModel
import com.kbearman.sunshine.model.DayWeather
import com.kbearman.sunshine.model.WeatherRepo
import java.text.SimpleDateFormat

/**
 * Created by Shiva on 5/31/2018.
 */
class WeatherDetailViewModel : ViewModel()
{
    interface WeatherDetailViewModelInteractor
    {
        fun shareData(weather:String)
        fun emailData(weather:String)
        fun startAboutPage(info:String)
    }

    private val TAG = WeatherDetailViewModel::class.java.simpleName
    val weatherRepository = WeatherRepo.getInstance()
    lateinit var activityInteractor:WeatherDetailViewModelInteractor

    fun getDaysWeather():DayWeather
    {
        return weatherRepository.selectedDay
    }

    fun shareButtonPressed()
    {
        activityInteractor.shareData(constructWeatherMessage())
    }

    fun emailButtonPressed()
    {
        activityInteractor.emailData(constructWeatherMessage())
    }

    fun constructWeatherMessage():String
    {
        var day = weatherRepository.selectedDay
        var builder:StringBuilder = StringBuilder()
        builder.append("Weather Forecast")
        builder.append("\n")
        builder.append("City:")
        builder.append(day.getCity())
        builder.append("\n")
        builder.append("Date:")
        var sdf = SimpleDateFormat("MM/dd/yyyy")
        builder.append(sdf.format(day.getDate().time))
        builder.append("\n")
        builder.append("High:")
        builder.append(day.getHighTemp().toString())
        builder.append("\n")
        builder.append("Low:")
        builder.append(day.getLowTemp())
        builder.append("\n")
        builder.append("Weather:")
        builder.append(day.getShortDescription())
        return builder.toString()
    }

    fun aboutButtonPressed()
    {
        activityInteractor.startAboutPage("This is the detailed weather screen for the day of " + weatherRepository.selectedDay.getDay())
    }

}