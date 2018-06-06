package com.kbearman.sunshine.SingleDayDetailedWeather

import android.content.Context
import android.widget.TextView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by Shiva on 6/5/2018.
 */
class WeatherAttributeRecyclerAdapter(internal var attributeList: ArrayList<String>, context: Context) : RecyclerView.Adapter<WeatherAttributeRecyclerAdapter.WeatherAttributeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherAttributeRecyclerAdapter.WeatherAttributeViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(android.R.layout.simple_list_item_1, parent, false)
        return WeatherAttributeViewHolder(v)
    }

    override fun onBindViewHolder(holder: WeatherAttributeRecyclerAdapter.WeatherAttributeViewHolder, position: Int) {
        holder.text.text = attributeList.get(position)
    }

    override fun getItemCount(): Int {
        return attributeList.size
    }

    class WeatherAttributeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var text: TextView

        init {
            text = itemView.findViewById(android.R.id.text1)
        }
    }
}