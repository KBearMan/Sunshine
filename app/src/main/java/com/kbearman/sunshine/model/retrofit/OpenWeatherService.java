package com.kbearman.sunshine.model.retrofit;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Shiva on 5/30/2018.
 */

public interface OpenWeatherService
{
    @GET("forecast&mode=json&cnt={count}&units=imperial&APPID=c823a132edfb2ceb3700abee63ab4223")
    Observable<List<WeatherResponse>> getCityForecast(@Query("q") String city, @Path("count") Integer count);
}
