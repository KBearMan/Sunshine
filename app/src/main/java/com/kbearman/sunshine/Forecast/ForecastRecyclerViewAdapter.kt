package com.kbearman.sunshine.Forecast

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.kbearman.sunshine.R
import com.kbearman.sunshine.R.id.main_activity_icon
import com.kbearman.sunshine.model.DayWeather
import kotlinx.android.synthetic.main.recycler_view_day_entry.view.*

/**
 * Created by Shiva on 5/31/2018.
 */

class ForecastRecyclerViewAdapter(private val myDataSet: ArrayList<DayWeather>, private val mClickListener: DayClickListener) : RecyclerView.Adapter<ForecastRecyclerViewAdapter.DayHolder>()
{
    override fun onBindViewHolder(holder: DayHolder, position: Int)
    {
        holder.dayWeather = myDataSet.get(position)
        holder.dayTextView.setText(holder.dayWeather!!.getDay())
        holder.descriptionTextView.setText(holder.dayWeather!!.getShortDescription())
        holder.highTextView.setText(String.format("%.1f",holder.dayWeather!!.getHighTemp())+"°")
        holder.lowTextView.setText(String.format("%.1f",holder.dayWeather!!.getLowTemp())+"°")
        Glide.with(holder.iconImageView.context).load(holder.dayWeather!!.getSmallIcon()).into(holder.iconImageView)
    }

    override fun getItemCount(): Int
    {
        return myDataSet.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayHolder
    {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_view_day_entry, parent, false) as View
        return DayHolder(view,mClickListener)
    }

    class DayHolder(v: View, mListener:DayClickListener) : RecyclerView.ViewHolder(v), View.OnClickListener {
        private var view: View = v
        val dayTextView: TextView = view.recycler_view_day_item_day
        val descriptionTextView: TextView = view.recycler_view_day_item_description
        val highTextView: TextView = view.recycler_view_day_item_high
        val lowTextView: TextView = view.recycler_view_day_item_low
        val iconImageView: ImageView = view.recycler_view_day_item_icon
        var dayWeather : DayWeather? = null
        private var listener = mListener

        init
        {
            v.setOnClickListener(this)
        }

        override fun onClick(v: View)
        {
            dayWeather?.let { listener.onClick(it) }
        }
    }

    interface DayClickListener
    {
        fun onClick(weather:DayWeather)
    }
}