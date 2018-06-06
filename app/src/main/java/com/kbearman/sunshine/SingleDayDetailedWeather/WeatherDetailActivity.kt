package com.kbearman.sunshine.SingleDayDetailedWeather

import android.app.PendingIntent.getActivity
import android.arch.lifecycle.ViewModelProviders
import android.content.DialogInterface
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.widget.ArrayAdapter
import com.kbearman.sunshine.R
import kotlinx.android.synthetic.main.activity_weather_detail_content.*
import kotlinx.android.synthetic.main.activity_weather_detail_frame.*
import java.text.SimpleDateFormat
import android.support.v7.widget.ShareActionProvider
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AlertDialog
import android.view.MenuItem




/**
 * Created by Shiva on 5/30/2018.
 */
class WeatherDetailActivity : AppCompatActivity() {
    override fun onBackPressed() {
        finish()
    }

    private lateinit var weatherDetailViewModel:WeatherDetailViewModel
    private lateinit var weatherStatAdapter:ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_detail_frame)
        setSupportActionBar(toolbar)
        supportActionBar?.setLogo(R.mipmap.art_clear)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
        getSupportActionBar()?.setDisplayShowHomeEnabled(true)

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
        weatherDetailViewModel.activityInteractor = object: WeatherDetailViewModel.WeatherDetailViewModelInteractor
        {
            override fun shareData(weather: String) {

                val sharingIntent = Intent(android.content.Intent.ACTION_SEND)
                sharingIntent.type = "text/plain"
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Weather")
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, weather)
                startActivity(Intent.createChooser(sharingIntent, "Share via"))
            }

            override fun emailData(weather: String) {
                val intent = Intent(Intent.ACTION_SENDTO)
                intent.data = Uri.parse("mailto:") // only email apps should handle this
                intent.putExtra(Intent.EXTRA_SUBJECT, weather)
                if (intent.resolveActivity(packageManager) != null) {
                    startActivity(intent)
                }
            }

            override fun startAboutPage(info: String) {
                val builder = AlertDialog.Builder(this@WeatherDetailActivity, R.style.ThemeOverlay_AppCompat_Dialog)
                builder.setMessage(info)
                        .setTitle("About")
                        .setNeutralButton("Ok",object:DialogInterface.OnClickListener{
                            override fun onClick(dialog: DialogInterface?, which: Int) {
                                dialog?.dismiss()
                            }
                        })
                val dialog = builder.create()
                dialog.show()
            }

        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        // Handle item selection
        when (item?.getItemId())
        {
            R.id.detail_menu_share -> {
                weatherDetailViewModel.shareButtonPressed()
                return true
            }
            R.id.detail_menu_about -> {
                weatherDetailViewModel.aboutButtonPressed()
                return true
            }
            R.id.detail_menu_email -> {
                weatherDetailViewModel.emailButtonPressed()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}
