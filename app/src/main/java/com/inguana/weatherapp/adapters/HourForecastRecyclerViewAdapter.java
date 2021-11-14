package com.inguana.weatherapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.inguana.weatherapp.R;
import com.inguana.weatherapp.adapters.viewholders.HourViewHolder;
import com.inguana.weatherapp.model.WeatherHour;
import com.inguana.weatherapp.utils.Utils;

import java.util.List;

public class HourForecastRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<WeatherHour> weatherHourList;

    public HourForecastRecyclerViewAdapter() {
    }

    public void setWeatherHourList(List<WeatherHour> weatherHourList) {
        this.weatherHourList = weatherHourList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hour_recyclerview_list_item, parent, false);

        int backgroundColour = viewType == 0 ? R.color.light_grey : R.color.white;
        view.setBackgroundColor(parent.getResources().getColor(backgroundColour, parent.getContext().getTheme()));

        return new HourViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        WeatherHour currentWeatherHour = weatherHourList.get(position);
        ((HourViewHolder) holder).getTimeOfDayHourListItem().setText(Utils.convertToReadableTime(currentWeatherHour.getTime()));

        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.drawable.ic_launcher_background);

        Glide.with(holder.itemView.getContext())
                .setDefaultRequestOptions(requestOptions)
                .load(currentWeatherHour.getWeatherIconUrl().get(0).getValue())
                .into(((HourViewHolder) holder).getWeatherImageHourListItem());

        ((HourViewHolder) holder).getWeatherDescriptionHourListItem().setText(currentWeatherHour.getWeatherDesc().get(0).getValue());
        ((HourViewHolder) holder).getChanceOfRainHourListItem().setText(holder.itemView.getResources().getString(R.string.label_chance_of_rain,
                currentWeatherHour.getChanceOfRain()));
        ((HourViewHolder) holder).getTemperatureHourListItem().setText(holder.itemView.getResources().getString(R.string.label_temperature_text,
                holder.itemView.getResources().getString(R.string.label_temperature, currentWeatherHour.getTempC(), currentWeatherHour.getTempF())));
        ((HourViewHolder) holder).getWindSpeedHourListItem().setText(holder.itemView.getResources().getString(R.string.label_wind_speed,
                currentWeatherHour.getWindSpeedKmph(), currentWeatherHour.getWindSpeedMiles()));
        ((HourViewHolder) holder).getPressureHourListItem().setText(holder.itemView.getResources().getString(R.string.label_pressure,
                currentWeatherHour.getPressure()));
        ((HourViewHolder) holder).getFeelsLikeHourListItem().setText(holder.itemView.getResources().getString(R.string.label_feels_like,
                holder.itemView.getResources().getString(R.string.label_temperature, currentWeatherHour.getFeelsLikeC(), currentWeatherHour.getFeelsLikeF())));
        ((HourViewHolder) holder).getHumidityHourListItem().setText(holder.itemView.getResources().getString(R.string.label_humidity,
                currentWeatherHour.getHumidity()));
    }

    @Override
    public int getItemViewType(int position) {
        return weatherHourList != null
                ? position % 2
                : 0;
    }

    @Override
    public int getItemCount() {
        return weatherHourList != null ? weatherHourList.size() : 0;
    }
}
