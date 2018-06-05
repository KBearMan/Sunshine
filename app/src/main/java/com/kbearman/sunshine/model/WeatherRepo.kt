package com.kbearman.sunshine.model

import android.util.Log
import com.kbearman.sunshine.model.retrofit.List
import com.kbearman.sunshine.model.retrofit.OpenWeatherService
import com.kbearman.sunshine.model.retrofit.WeatherResponse
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.flatMapSequence
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET




/**
 * Created by Shiva on 5/30/2018.
 */
class WeatherRepo private constructor(private var retrofit: Any) : IForecast
{
    private var weatherObservable : PublishSubject<kotlin.collections.List<DayWeather>> = PublishSubject.create()
    private val TAG = WeatherRepo::class.java.simpleName

    lateinit var weatherService :OpenWeatherService

    override fun getForecastObservable(): Observable<kotlin.collections.List<DayWeather>> {
                return weatherObservable
    }

    override fun getForecastByCity(cityName: String, dayCount: Integer) {
        Log.d(TAG,"getForecastByCity called")
        var queryMap: HashMap<String,String> = HashMap()
        queryMap.put("q",cityName)
        queryMap.put("mode","json")
        queryMap.put("cnt",dayCount.toString())
        queryMap.put("units","imperial")
        queryMap.put("APPID","3aa158b2f14a9f493a8c725f8133d704")
        //http://api.openweathermap.org/data/2.5/forecast/daily?q=Atlanta&mode=json&cnt=5&units=imperial&APPID=3aa158b2f14a9f493a8c725f8133d704

         weatherService.getCityForecast(queryMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
             //   .concatMapIterable { it ->  it}
                .map { myWeatherResponse ->
                    Log.d(TAG,"Mapping weatherResponse to " +
                            "dayWeather")
                    OpenWeatherAdapter.convertWeatherResponseToDayWeather(myWeatherResponse) }
                .subscribe(
                        { result -> weatherObservable.onNext(result)},
                        { error -> Log.e("WeatherRepo",error.message) }
                )
    }
    //PROPER API CALL
    //http://api.openweathermap.org/data/2.5/forecast?q=Atlanta&mode=json&cnt=5&units=imperial&APPID=c823a132edfb2ceb3700abee63ab4223

    init {
        Log.d(TAG,"WeatherRepo initialized")
        retrofit = Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/data/2.5/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        weatherService = (retrofit as Retrofit?)!!.create(OpenWeatherService::class.java)
    }


    companion object {
        private val mInstance: WeatherRepo = WeatherRepo("")

        @Synchronized
        fun getInstance(): WeatherRepo {
            return mInstance
        }
    }


}