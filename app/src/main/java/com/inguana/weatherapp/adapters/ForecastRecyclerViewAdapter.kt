package com.inguana.weatherapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.inguana.weatherapp.R
import com.inguana.weatherapp.adapters.interfaces.OnDayListener
import com.inguana.weatherapp.adapters.viewholders.DayViewHolder
import com.inguana.weatherapp.model.WeatherDay
import com.inguana.weatherapp.utils.Utils

class ForecastRecyclerViewAdapter(private val onDayListener: OnDayListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var weatherDayList: List<WeatherDay?>? = null
    fun getWeatherDayList(): List<WeatherDay?>? {
        return weatherDayList
    }

    fun setWeatherDayList(weatherDayList: List<WeatherDay?>?) {
        this.weatherDayList = weatherDayList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.day_recyclerview_list_item, parent, false)
        val backgroundColour = if (viewType == 0) R.color.light_grey else R.color.white
        view.setBackgroundColor(parent.resources.getColor(backgroundColour, parent.context.theme))
        return DayViewHolder(view, onDayListener)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as DayViewHolder).dayDayListItem.text = Utils.getDayFromDate(
            weatherDayList!![position]?.date
        )
        holder.temperatureDayListItem.text = holder.itemView.resources.getString(
            R.string.label_temperature,
            weatherDayList!![position]?.mintempC, weatherDayList!![position]?.mintempF
        )
        val requestOptions = RequestOptions()
            .placeholder(R.drawable.ic_launcher_background)
        Glide.with(holder.itemView.context)
            .setDefaultRequestOptions(requestOptions)
            .load(weatherDayList!![position]?.weatherHour?.get(0)?.weatherIconUrl?.get(0)?.value)
            .into(holder.weatherImageDayListItem)
        holder.temperatureLastDayListItem.text = holder.itemView.resources.getString(
            R.string.label_temperature,
            weatherDayList!![position]?.maxtempC ?: "-", weatherDayList!![position]?.maxtempF ?: "-"
        )
        Glide.with(holder.itemView.context)
            .setDefaultRequestOptions(requestOptions)
            .load(weatherDayList!![position]?.weatherHour?.get(11)?.weatherIconUrl?.get(0)?.value)
            .into(holder.weatherImageLastDayListItem)
    }

    fun getSelectedWeatherDay(position: Int): WeatherDay? {
        return weatherDayList!![position]
    }

    override fun getItemViewType(position: Int): Int {
        return if (weatherDayList != null) position % 2 else 0
    }

    override fun getItemCount(): Int {
        return if (weatherDayList != null) weatherDayList!!.size else 0
    }
}