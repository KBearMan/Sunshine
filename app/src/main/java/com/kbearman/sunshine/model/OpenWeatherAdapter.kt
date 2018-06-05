package com.kbearman.sunshine.model

import android.text.format.DateUtils
import com.kbearman.sunshine.R
import com.kbearman.sunshine.model.retrofit.WeatherResponse
import java.text.SimpleDateFormat
import java.util.*

class OpenWeatherAdapter
{
    companion object {
        fun convertWeatherResponseToDayWeather(weatherResponse: WeatherResponse): List<SimpleDayWeather> {
            var weatherList = ArrayList<SimpleDayWeather>()
            for(day in weatherResponse.list!!)
            {
                var dayWeather = SimpleDayWeather()
                dayWeather.setCity(weatherResponse.city?.name!!)
                var cal = Calendar.getInstance()
                cal.timeInMillis = day.dt!!
                dayWeather.setDate(cal)
                dayWeather.setDay(getWeekdayText(cal))
                dayWeather.setDescription(day.weather!!.get(0).description!!)
                dayWeather.setHighTemp(day.main?.tempMax)
                dayWeather.setlowTemp(day.main?.tempMin)
                dayWeather.setHumidity(day.main?.humidity?.toInt())
                dayWeather.setPressure(day.main?.pressure.toString())
                dayWeather.setWind(day.wind?.speed.toString())
                dayWeather.setIcon(getIconFromDescription(dayWeather.getDescription()))
                weatherList.add(dayWeather)
            }

            return weatherList
        }

        private fun getIconFromDescription(description: String): Int {
            var resourceToReturn = R.mipmap.ic_logo
            when
            {
                description.toLowerCase().contains("light") &&
                        description.toLowerCase().contains("clouds") -> resourceToReturn = R.mipmap.art_light_clouds
                description.toLowerCase().contains("light")  &&
                        description.toLowerCase().contains("rain") -> resourceToReturn = R.mipmap.art_light_rain

                description.toLowerCase().contains("clear") -> resourceToReturn = R.mipmap.art_clear
                description.toLowerCase().contains("clouds")  -> resourceToReturn = R.mipmap.art_clouds
                description.toLowerCase().contains("fog") -> resourceToReturn = R.mipmap.art_fog
                description.toLowerCase().contains("rain") -> resourceToReturn = R.mipmap.art_rain
                description.toLowerCase().contains("snow")-> resourceToReturn = R.mipmap.art_snow
                description.toLowerCase().contains("storm") -> resourceToReturn = R.mipmap.art_storm
            }
            return resourceToReturn
        }

        private fun getWeekdayText(calendar:Calendar): String {
            if(DateUtils.isToday(calendar.timeInMillis))
            {
                return "Today"
            }
            else if (DateUtils.isToday((calendar.timeInMillis) - (1000*60*60*24))) // Tomorrow
            {
                return "Tomorrow"
            }
            else
            {
                val sdf = SimpleDateFormat("EEEE")
                return sdf.format(calendar.time)
            }
        }

        private fun convertStringToCalendar(openWeatherDateString:String):Calendar
        {
            val sdf : SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            var cal:Calendar = Calendar.getInstance()
            cal.time = sdf.parse(openWeatherDateString)
            return cal
        }
    }
}