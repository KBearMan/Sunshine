package com.kbearman.sunshine.SingleDayDetailedWeather

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.widget.ArrayAdapter
import com.kbearman.sunshine.R
import kotlinx.android.synthetic.main.activity_weather_detail_content.*
import kotlinx.android.synthetic.main.activity_weather_detail_frame.*
import java.text.SimpleDateFormat

/**
 * Created by Shiva on 5/30/2018.
 */
class WeatherDetailActivity : AppCompatActivity() {
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu,menu)
        return true
    }

    private lateinit var weatherDetailViewModel:WeatherDetailViewModel
    private lateinit var weatherStatAdapter:ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_detail_frame)
        setSupportActionBar(toolbar)
        supportActionBar?.setLogo(R.mipmap.art_clear)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        weatherDetailViewModel = ViewModelProviders.of(this).get(WeatherDetailViewModel::class.java)
        weatherStatAdapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,android.R.id.text1,weatherDetailViewModel.getDaysWeather().getMetrics())
        weather_detail_attributes.layoutManager = LinearLayoutManager(applicationContext)

        weather_detail_attributes.adapter = WeatherAttributeRecyclerAdapter(ArrayList(weatherDetailViewModel.getDaysWeather().getMetrics()),this)

        weather_detail_high_temp.text = String.format("%.1f",weatherDetailViewModel.getDaysWeather().getHighTemp())+"°"
        val sdf = SimpleDateFormat("MMMM dd")
        weather_detail_date.text = sdf.format(weatherDetailViewModel.getDaysWeather().getDate().time)
        weather_detail_low_temp.text = String.format("%.1f",weatherDetailViewModel.getDaysWeather().getLowTemp())+"°"
        weather_detail_day.text = weatherDetailViewModel.getDaysWeather().getDay()
        weather_detail_description.text = weatherDetailViewModel.getDaysWeather().getShortDescription()
    }

}
