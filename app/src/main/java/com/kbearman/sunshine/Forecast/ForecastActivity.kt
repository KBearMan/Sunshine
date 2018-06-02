package com.kbearman.sunshine.Forecast

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.kbearman.sunshine.R
import com.kbearman.sunshine.model.DayWeather
import kotlinx.android.synthetic.main.activity_main.*

class ForecastActivity : AppCompatActivity() {

    private lateinit var forecastViewModel:ForecastViewModel
    private lateinit var dayListAdapter:ForecastRecyclerViewAdapter
    private val TAG = ForecastActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        forecastViewModel = ViewModelProviders.of(this).get(ForecastViewModel::class.java)
        dayListAdapter = ForecastRecyclerViewAdapter(forecastViewModel.forecastList,object:ForecastRecyclerViewAdapter.DayClickListener{
            override fun onClick(weather: DayWeather) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })
        main_activity_day_list.adapter = dayListAdapter

    }




}
