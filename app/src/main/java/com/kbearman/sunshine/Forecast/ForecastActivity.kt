package com.kbearman.sunshine.Forecast

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.kbearman.sunshine.R
import com.kbearman.sunshine.SingleDayDetailedWeather.WeatherDetailActivity
import com.kbearman.sunshine.model.DayWeather
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat

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
                forecastViewModel.dayClicked(weather)
            }

        })
        main_activity_day_list.layoutManager = LinearLayoutManager(applicationContext)
        main_activity_day_list.adapter = dayListAdapter
        forecastViewModel.mInteractor = object: ForecastViewModel.ForecastViewModelInteractor
        {
            override fun newDataReceived() {
                try
                {
                    if (main_activity_date.text == "" && forecastViewModel.todaysWeather != null) {
                        populateTodaysWeather()
                    }
                }
                catch(e:UninitializedPropertyAccessException)
                {

                }
                dayListAdapter.notifyDataSetChanged()
            }

            override fun startSingleDayActivity(day: DayWeather) {
                startActivity(Intent(applicationContext,WeatherDetailActivity::class.java))
            }

        }
    }

    fun populateTodaysWeather()
    {
        val sdf = SimpleDateFormat("MMMM dd")
        main_activity_date.text = "Today,"+sdf.format(forecastViewModel.todaysWeather.getDate().time)
        main_activity_low_temp.text = forecastViewModel.todaysWeather.getLowTemp().toString()+"°"
        main_activity_current_temp.text = forecastViewModel.todaysWeather.getHighTemp().toString()+"°"
        main_activity_weather_description.text = forecastViewModel.todaysWeather.getDescription()
        Glide.with(this.applicationContext).load(forecastViewModel.todaysWeather.getIcon()).into(main_activity_icon)
    }
}
