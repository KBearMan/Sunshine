package com.kbearman.sunshine.Forecast

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.kbearman.sunshine.R

class ForecastActivity : AppCompatActivity() {

    private lateinit var forecastViewModel:ForecastViewModel
    private lateinit var dayListAdapter:ForecastRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        forecastViewModel = ViewModelProviders.of(this).get(ForecastViewModel::class.java)
        dayListAdapter = ForecastRecyclerViewAdapter()

    }




}
