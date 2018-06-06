package com.kbearman.sunshine.model

import android.text.format.DateUtils
import com.kbearman.sunshine.R
import com.kbearman.sunshine.model.retrofit.Weather
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
                cal.timeInMillis = (day.dt!!*1000)
                dayWeather.setDate(cal)
                dayWeather.setDay(getWeekdayText(cal))
                var list: MutableList<Weather>? = day.weather
                dayWeather.setDescription(list?.get(0)?.description!!)
                dayWeather.setShortDescription(list.get(0)?.main!!)
                dayWeather.setHighTemp(day.temp.max)
                dayWeather.setlowTemp(day.temp.min)
                dayWeather.setHumidity(day.humidity?.toInt())
                dayWeather.setPressure(day.pressure.toString() + " hPa")
                dayWeather.setWind(day.speed.toString() + " km/h " + getCardinalFromDegrees(day.deg))
                dayWeather.setIcon(getIconFromDescription(day.weather.get(0).icon!!))
                dayWeather.setSmallIcon(getSmallIconFromDescription(day.weather.get(0).icon!!))
                weatherList.add(dayWeather)
            }

            return weatherList
        }

        private fun getCardinalFromDegrees(deg: Long?): String {
            var returnDirection:String = ""
            if (deg != null)
            {
                when
                {
                    deg > 338  || deg <= 22  -> returnDirection = "N"
                    deg > 22   && deg <= 67  -> returnDirection = "NE"
                    deg > 67   && deg <= 111 -> returnDirection = "E"
                    deg > 111  && deg <= 155 -> returnDirection = "SE"
                    deg > 155  && deg <= 199 -> returnDirection = "S"
                    deg > 199  && deg <= 243 -> returnDirection = "SW"
                    deg > 243  && deg <= 287 -> returnDirection = "W"
                    deg > 287  && deg <= 338 -> returnDirection = "NW"
                }
            }
            return returnDirection
        }

        private fun getIconFromDescription(description: String): Int {
            var resourceToReturn = R.mipmap.ic_logo
            when
            {
                description.contains("03") || description.contains("04")  -> resourceToReturn = R.mipmap.art_clouds
                description.contains("02") -> resourceToReturn = R.mipmap.art_light_clouds
                description.contains("09") -> resourceToReturn = R.mipmap.art_light_rain
                description.contains("01") -> resourceToReturn = R.mipmap.art_clear
                description.contains("50") -> resourceToReturn = R.mipmap.art_fog
                description.contains("10") -> resourceToReturn = R.mipmap.art_rain
                description.contains("13") -> resourceToReturn = R.mipmap.art_snow
                description.contains("11") -> resourceToReturn = R.mipmap.art_storm
            }
            return resourceToReturn
        }

        private fun getSmallIconFromDescription(description: String): Int {
            var resourceToReturn = R.mipmap.ic_logo
            when
            {
                description.contains("03") || description.contains("04")  -> resourceToReturn = R.mipmap.ic_cloudy
                description.contains("02") -> resourceToReturn = R.mipmap.ic_light_clouds
                description.contains("09") -> resourceToReturn = R.mipmap.ic_light_rain
                description.contains("01") -> resourceToReturn = R.mipmap.ic_clear
                description.contains("50") -> resourceToReturn = R.mipmap.ic_fog
                description.contains("10") -> resourceToReturn = R.mipmap.ic_rain
                description.contains("13") -> resourceToReturn = R.mipmap.ic_snow
                description.contains("11") -> resourceToReturn = R.mipmap.ic_storm
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