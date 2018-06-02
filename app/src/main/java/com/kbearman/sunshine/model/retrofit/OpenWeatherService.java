package com.kbearman.sunshine.model.retrofit;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Shiva on 5/30/2018.
 */

public interface OpenWeatherService
{
    @GET("forecast&mode=json&cnt={count}&units=imperial&APPID=3aa158b2f14a9f493a8c725f8133d704")
    Observable<List<WeatherResponse>> getCityForecast(@Path("count") Integer count,@Query("q") String city);
}
