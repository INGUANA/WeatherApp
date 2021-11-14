package com.inguana.weatherapp.adapters.viewholders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.inguana.weatherapp.R;

public class HourViewHolder extends RecyclerView.ViewHolder {

    TextView timeOfDayHourListItem;
    ImageView weatherImageHourListItem;
    TextView weatherDescriptionHourListItem;
    TextView chanceOfRainHourListItem;
    TextView temperatureHourListItem;
    TextView windSpeedHourListItem;
    TextView pressureHourListItem;
    TextView feelsLikeHourListItem;
    TextView humidityHourListItem;

    public TextView getTimeOfDayHourListItem() {
        return timeOfDayHourListItem;
    }

    public ImageView getWeatherImageHourListItem() {
        return weatherImageHourListItem;
    }

    public TextView getWeatherDescriptionHourListItem() {
        return weatherDescriptionHourListItem;
    }

    public TextView getChanceOfRainHourListItem() {
        return chanceOfRainHourListItem;
    }

    public TextView getTemperatureHourListItem() {
        return temperatureHourListItem;
    }

    public TextView getWindSpeedHourListItem() {
        return windSpeedHourListItem;
    }

    public TextView getPressureHourListItem() {
        return pressureHourListItem;
    }

    public TextView getFeelsLikeHourListItem() {
        return feelsLikeHourListItem;
    }

    public TextView getHumidityHourListItem() {
        return humidityHourListItem;
    }

    public HourViewHolder(@NonNull View itemView) {
        super(itemView);
        timeOfDayHourListItem = itemView.findViewById(R.id.timeOfDayHourListItem);
        weatherImageHourListItem = itemView.findViewById(R.id.weatherImageHourListItem);
        weatherDescriptionHourListItem = itemView.findViewById(R.id.weatherDescriptionHourListItem);
        chanceOfRainHourListItem = itemView.findViewById(R.id.chanceOfRainHourListItem);
        temperatureHourListItem = itemView.findViewById(R.id.temperatureHourListItem);
        windSpeedHourListItem = itemView.findViewById(R.id.windSpeedHourListItem);
        pressureHourListItem = itemView.findViewById(R.id.pressureHourListItem);
        feelsLikeHourListItem = itemView.findViewById(R.id.feelsLikeHourListItem);
        humidityHourListItem = itemView.findViewById(R.id.humidityHourListItem);

    }
}
