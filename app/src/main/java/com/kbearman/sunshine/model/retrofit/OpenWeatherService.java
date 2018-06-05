package com.kbearman.sunshine.model.retrofit;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by Shiva on 5/30/2018.
 */

public interface OpenWeatherService
{
    @GET("forecast/daily")
    Observable<WeatherResponse> getCityForecast(@QueryMap Map<String, String> options);
}
