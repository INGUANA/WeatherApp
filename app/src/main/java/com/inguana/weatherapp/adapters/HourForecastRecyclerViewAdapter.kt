package com.inguana.weatherapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.inguana.weatherapp.R
import com.inguana.weatherapp.adapters.viewholders.HourViewHolder
import com.inguana.weatherapp.model.WeatherHour
import com.inguana.weatherapp.utils.Utils

class HourForecastRecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var weatherHourList: List<WeatherHour?>? = null
    fun setWeatherHourList(weatherHourList: List<WeatherHour?>?) {
        this.weatherHourList = weatherHourList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.hour_recyclerview_list_item, parent, false)
        val backgroundColour = if (viewType == 0) R.color.light_grey else R.color.white
        view.setBackgroundColor(parent.resources.getColor(backgroundColour, parent.context.theme))
        return HourViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentWeatherHour = weatherHourList!![position]
        (holder as HourViewHolder).timeOfDayHourListItem.text =
            Utils.convertToReadableTime(currentWeatherHour?.time)
        val requestOptions = RequestOptions()
            .placeholder(R.drawable.ic_launcher_background)
        Glide.with(holder.itemView.context)
            .setDefaultRequestOptions(requestOptions)
            .load(currentWeatherHour?.weatherIconUrl?.get(0)?.value)
            .into(holder.weatherImageHourListItem)
        holder.weatherDescriptionHourListItem.text = currentWeatherHour?.weatherDesc?.get(0)?.value
        holder.chanceOfRainHourListItem.text = holder.itemView.resources.getString(
            R.string.label_chance_of_rain,
            currentWeatherHour?.chanceOfRain
        )
        holder.temperatureHourListItem.text = holder.itemView.resources.getString(
            R.string.label_temperature_text,
            holder.itemView.resources.getString(
                R.string.label_temperature,
                currentWeatherHour?.tempC,
                currentWeatherHour?.tempF
            )
        )
        holder.windSpeedHourListItem.text = holder.itemView.resources.getString(
            R.string.label_wind_speed,
            currentWeatherHour?.windSpeedKmph, currentWeatherHour?.windSpeedMiles
        )
        holder.pressureHourListItem.text = holder.itemView.resources.getString(
            R.string.label_pressure,
            currentWeatherHour?.pressure
        )
        holder.feelsLikeHourListItem.text = holder.itemView.resources.getString(
            R.string.label_feels_like,
            holder.itemView.resources.getString(
                R.string.label_temperature,
                currentWeatherHour?.feelsLikeC,
                currentWeatherHour?.feelsLikeF
            )
        )
        holder.humidityHourListItem.text = holder.itemView.resources.getString(
            R.string.label_humidity,
            currentWeatherHour?.humidity
        )
    }

    override fun getItemViewType(position: Int): Int {
        return if (weatherHourList != null) position % 2 else 0
    }

    override fun getItemCount(): Int {
        return if (weatherHourList != null) weatherHourList!!.size else 0
    }
}