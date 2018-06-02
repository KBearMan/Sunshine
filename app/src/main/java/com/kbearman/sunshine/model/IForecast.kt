package com.kbearman.sunshine.model

import io.reactivex.Observable

interface IForecast
{
    fun getForecastObservable(): Observable<DayWeather>
    fun getForecastByCity(cityName:String,dayCount:Integer)

}