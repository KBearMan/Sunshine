package com.kbearman.sunshine.model

import io.reactivex.Observable
import java.util.List

interface IForecast
{
    fun getForecastObservable(): Observable<kotlin.collections.List<DayWeather>>
    fun getForecastByCity(cityName:String,dayCount:Integer)

}