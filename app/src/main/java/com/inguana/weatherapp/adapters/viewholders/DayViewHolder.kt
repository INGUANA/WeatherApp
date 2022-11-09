package com.inguana.weatherapp.adapters.viewholders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.inguana.weatherapp.R
import com.inguana.weatherapp.adapters.interfaces.OnDayListener

class DayViewHolder(itemView: View, onDayListener: OnDayListener) :
    RecyclerView.ViewHolder(itemView), View.OnClickListener {
    var dayDayListItem: TextView
    var weatherImageDayListItem: ImageView
    var temperatureDayListItem: TextView
    var weatherImageLastDayListItem: ImageView
    var temperatureLastDayListItem: TextView
    var onDayListener: OnDayListener

    init {
        dayDayListItem = itemView.findViewById(R.id.dayDayListItem)
        weatherImageDayListItem = itemView.findViewById(R.id.weatherImageDayListItem)
        temperatureDayListItem = itemView.findViewById(R.id.temperatureDayListItem)
        weatherImageLastDayListItem = itemView.findViewById(R.id.weatherImageLastDayListItem)
        temperatureLastDayListItem = itemView.findViewById(R.id.temperatureLastDayListItem)
        itemView.setOnClickListener(this)
        this.onDayListener = onDayListener
    }

    override fun onClick(view: View) {
        onDayListener.onDayClick(bindingAdapterPosition)
    }
}