package com.inguana.weatherapp.adapters.viewholders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.inguana.weatherapp.R;
import com.inguana.weatherapp.adapters.interfaces.OnDayListener;

public class DayViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView dayDayListItem;
    ImageView weatherImageDayListItem;
    TextView temperatureDayListItem;
    ImageView weatherImageLastDayListItem;
    TextView temperatureLastDayListItem;
    OnDayListener onDayListener;

    public TextView getDayDayListItem() {
        return dayDayListItem;
    }

    public ImageView getWeatherImageDayListItem() {
        return weatherImageDayListItem;
    }

    public TextView getTemperatureDayListItem() {
        return temperatureDayListItem;
    }

    public ImageView getWeatherImageLastDayListItem() {
        return weatherImageLastDayListItem;
    }

    public TextView getTemperatureLastDayListItem() {
        return temperatureLastDayListItem;
    }

    public DayViewHolder(@NonNull View itemView, OnDayListener onDayListener) {
        super(itemView);
        dayDayListItem = itemView.findViewById(R.id.dayDayListItem);
        weatherImageDayListItem = itemView.findViewById(R.id.weatherImageDayListItem);
        temperatureDayListItem = itemView.findViewById(R.id.temperatureDayListItem);
        weatherImageLastDayListItem = itemView.findViewById(R.id.weatherImageLastDayListItem);
        temperatureLastDayListItem = itemView.findViewById(R.id.temperatureLastDayListItem);

        itemView.setOnClickListener(this);
        this.onDayListener = onDayListener;

    }

    @Override
    public void onClick(View view) {
        onDayListener.onDayClick(getBindingAdapterPosition());
    }
}
