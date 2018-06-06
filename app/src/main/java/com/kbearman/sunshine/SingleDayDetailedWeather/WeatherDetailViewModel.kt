package com.kbearman.sunshine.SingleDayDetailedWeather

import android.arch.lifecycle.ViewModel
import com.kbearman.sunshine.model.DayWeather
import com.kbearman.sunshine.model.WeatherRepo

/**
 * Created by Shiva on 5/31/2018.
 */
class WeatherDetailViewModel : ViewModel()
{
    private val TAG = WeatherDetailViewModel::class.java.simpleName
    val weatherRepository = WeatherRepo.getInstance()
    fun getDaysWeather():DayWeather
    {
        return weatherRepository.selectedDay
    }
}