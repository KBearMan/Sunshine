package com.kbearman.sunshine.Forecast

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.MenuItemCompat
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.ShareActionProvider
import android.view.Menu
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.kbearman.sunshine.R
import com.kbearman.sunshine.SingleDayDetailedWeather.WeatherDetailActivity
import com.kbearman.sunshine.model.DayWeather
import kotlinx.android.synthetic.main.activity_forecast_content.*
import kotlinx.android.synthetic.main.activity_forecast_frame.*
import java.text.SimpleDateFormat

class ForecastActivity : AppCompatActivity() {

    private lateinit var forecastViewModel:ForecastViewModel
    private lateinit var dayListAdapter:ForecastRecyclerViewAdapter
    private val TAG = ForecastActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forecast_frame)
        setSupportActionBar(toolbar)
        supportActionBar?.setLogo(R.mipmap.art_clear)

        forecastViewModel = ViewModelProviders.of(this).get(ForecastViewModel::class.java)
        dayListAdapter = ForecastRecyclerViewAdapter(forecastViewModel.forecastList,object:ForecastRecyclerViewAdapter.DayClickListener{
            override fun onClick(weather: DayWeather) {
                forecastViewModel.dayClicked(weather)
            }

        })
        main_activity_day_list.layoutManager = LinearLayoutManager(applicationContext)
        main_activity_day_list.adapter = dayListAdapter
        forecastViewModel.activityInteractor = object: ForecastViewModel.ForecastViewModelInteractor
        {
            override fun startAboutPage(info: String) {
                val builder = AlertDialog.Builder(this@ForecastActivity, R.style.ThemeOverlay_AppCompat_Dialog)
                builder.setMessage(info)
                        .setTitle("About")
                val dialog = builder.create()
                dialog.show()            }


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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.forecast_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        // Handle item selection
        when (item?.getItemId())
        {
            R.id.forecast_menu_about -> {
                forecastViewModel.aboutButtonPressed()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    fun populateTodaysWeather()
    {
        val sdf = SimpleDateFormat("MMMM dd")
        main_activity_date.text = "Today,"+sdf.format(forecastViewModel.todaysWeather.getDate().time)
        main_activity_low_temp.text = String.format("%.1f",forecastViewModel.todaysWeather.getLowTemp())+"°"
        main_activity_high_temp.text = String.format("%.1f",forecastViewModel.todaysWeather.getHighTemp())+"°"
        main_activity_weather_description.text = forecastViewModel.todaysWeather.getShortDescription()
        Glide.with(this.applicationContext).load(forecastViewModel.todaysWeather.getIcon()).into(main_activity_icon)
    }
}
