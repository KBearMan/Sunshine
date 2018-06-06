package com.kbearman.sunshine.Forecast

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.opengl.Visibility
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.animation.AnimationUtils
import android.widget.Toast
import com.bumptech.glide.Glide
import com.kbearman.sunshine.R
import com.kbearman.sunshine.SingleDayDetailedWeather.WeatherDetailActivity
import com.kbearman.sunshine.model.DayWeather
import kotlinx.android.synthetic.main.activity_forecast_content.*
import kotlinx.android.synthetic.main.activity_forecast_frame.*
import java.text.SimpleDateFormat

class ForecastActivity : AppCompatActivity() {

    private val TAG = ForecastActivity::class.java.simpleName
    private lateinit var forecastViewModel:ForecastViewModel
    private lateinit var dayListAdapter:ForecastRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forecast_frame)
        setSupportActionBar(toolbar)
        supportActionBar?.setLogo(R.mipmap.art_clear)
        showLoading()
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
            override fun showErrorMessage(message: String) {
                runOnUiThread(Runnable {
                    Toast.makeText(this@ForecastActivity, "ERROR:" + message, Toast.LENGTH_SHORT).show()
                })
            }

            override fun startAboutPage(info: String) {
                val builder = AlertDialog.Builder(this@ForecastActivity, R.style.ThemeOverlay_AppCompat_Dialog)
                builder.setMessage(info)
                        .setTitle("About")
                val dialog = builder.create()
                dialog.show()            }

            override fun newDataReceived() {
                try
                {
                    if(main_activity_loading_icon.visibility == VISIBLE)
                    {
                        hideLoading()
                    }

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

    fun showLoading()
    {
        main_activity_loading_icon.visibility = View.VISIBLE
        main_activity_loading_icon.startAnimation(AnimationUtils.loadAnimation(this,R.anim.loading))
        main_activity_loading_text.visibility = View.VISIBLE
        main_activity_day_list.visibility = View.INVISIBLE
        main_activity_icon.visibility = View.INVISIBLE
        main_activity_date.visibility = View.INVISIBLE
        main_activity_high_temp.visibility = View.INVISIBLE
        main_activity_low_temp.visibility = View.INVISIBLE
        main_activity_weather_description.visibility = View.INVISIBLE
    }

    fun hideLoading()
    {
        main_activity_loading_icon.clearAnimation()
        main_activity_loading_icon.visibility = View.INVISIBLE
        main_activity_loading_text.visibility = View.INVISIBLE
        main_activity_day_list.visibility = View.VISIBLE
        main_activity_icon.visibility = View.VISIBLE
        main_activity_date.visibility = View.VISIBLE
        main_activity_high_temp.visibility = View.VISIBLE
        main_activity_low_temp.visibility = View.VISIBLE
        main_activity_weather_description.visibility = View.VISIBLE
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
