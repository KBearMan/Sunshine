package com.kbearman.sunshine.Forecast

import android.arch.lifecycle.ViewModel
import com.kbearman.sunshine.model.DayWeather
import com.kbearman.sunshine.model.WeatherRepo

/**
 * Created by Shiva on 5/31/2018.
 */
class ForecastViewModel : ViewModel()
{
    val weatherRepository = WeatherRepo.getInstance()
    var selectedCity = "Atlanta"
    var forecastList:List<DayWeather> = ArrayList()
    init {

    }


}