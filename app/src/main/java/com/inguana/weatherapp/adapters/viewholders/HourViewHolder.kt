package com.inguana.weatherapp.adapters.viewholders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.inguana.weatherapp.R

class HourViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var timeOfDayHourListItem: TextView
    var weatherImageHourListItem: ImageView
    var weatherDescriptionHourListItem: TextView
    var chanceOfRainHourListItem: TextView
    var temperatureHourListItem: TextView
    var windSpeedHourListItem: TextView
    var pressureHourListItem: TextView
    var feelsLikeHourListItem: TextView
    var humidityHourListItem: TextView

    init {
        timeOfDayHourListItem = itemView.findViewById(R.id.timeOfDayHourListItem)
        weatherImageHourListItem = itemView.findViewById(R.id.weatherImageHourListItem)
        weatherDescriptionHourListItem = itemView.findViewById(R.id.weatherDescriptionHourListItem)
        chanceOfRainHourListItem = itemView.findViewById(R.id.chanceOfRainHourListItem)
        temperatureHourListItem = itemView.findViewById(R.id.temperatureHourListItem)
        windSpeedHourListItem = itemView.findViewById(R.id.windSpeedHourListItem)
        pressureHourListItem = itemView.findViewById(R.id.pressureHourListItem)
        feelsLikeHourListItem = itemView.findViewById(R.id.feelsLikeHourListItem)
        humidityHourListItem = itemView.findViewById(R.id.humidityHourListItem)
    }
}