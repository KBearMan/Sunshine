package com.kbearman.sunshine.model

import com.kbearman.sunshine.model.retrofit.List
import com.kbearman.sunshine.model.retrofit.WeatherResponse
import io.reactivex.Observable

interface IForecast
{
    fun getForecastObservable(): Observable<List<WeatherResponse>>
    fun getForecastByCity(cityName:String,dayCount:Integer)

}