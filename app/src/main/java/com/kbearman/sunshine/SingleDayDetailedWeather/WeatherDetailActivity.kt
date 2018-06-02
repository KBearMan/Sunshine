package com.kbearman.sunshine.SingleDayDetailedWeather

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.kbearman.sunshine.R
import kotlinx.android.synthetic.main.weather_detail_frame.*

/**
 * Created by Shiva on 5/30/2018.
 */
class WeatherDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.weather_detail_frame)
        setSupportActionBar(toolbar)
    }

}
