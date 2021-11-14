package com.inguana.weatherapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.inguana.weatherapp.R;
import com.inguana.weatherapp.adapters.interfaces.OnDayListener;
import com.inguana.weatherapp.adapters.viewholders.DayViewHolder;
import com.inguana.weatherapp.model.WeatherDay;
import com.inguana.weatherapp.utils.Utils;

import java.util.List;

public class ForecastRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<WeatherDay> weatherDayList;
    private OnDayListener onDayListener;

    public ForecastRecyclerViewAdapter(OnDayListener onDayListener) {
        this.onDayListener = onDayListener;
    }

    public List<WeatherDay> getWeatherDayList() {
        return weatherDayList;
    }

    public void setWeatherDayList(List<WeatherDay> weatherDayList) {
        this.weatherDayList = weatherDayList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.day_recyclerview_list_item, parent, false);

        int backgroundColour = viewType == 0 ? R.color.light_grey : R.color.white;
        view.setBackgroundColor(parent.getResources().getColor(backgroundColour, parent.getContext().getTheme()));

        return new DayViewHolder(view, onDayListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((DayViewHolder) holder).getDayDayListItem().setText(Utils.getDayFromDate(weatherDayList.get(position).getDate()));
        ((DayViewHolder) holder).getTemperatureDayListItem().setText(holder.itemView.getResources().getString(R.string.label_temperature,
                weatherDayList.get(position).getMintempC(), weatherDayList.get(position).getMintempF()));

        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.drawable.ic_launcher_background);

        Glide.with(holder.itemView.getContext())
                .setDefaultRequestOptions(requestOptions)
                .load(weatherDayList.get(position).getWeatherHour().get(0).getWeatherIconUrl().get(0).getValue())
                .into(((DayViewHolder) holder).getWeatherImageDayListItem());

        ((DayViewHolder) holder).getTemperatureLastDayListItem().setText(holder.itemView.getResources().getString(R.string.label_temperature,
                weatherDayList.get(position).getMaxtempC(), weatherDayList.get(position).getMaxtempF()));

        Glide.with(holder.itemView.getContext())
                .setDefaultRequestOptions(requestOptions)
                .load(weatherDayList.get(position).getWeatherHour().get(11).getWeatherIconUrl().get(0).getValue())
                .into(((DayViewHolder) holder).getWeatherImageLastDayListItem());
    }

    public WeatherDay getSelectedWeatherDay(int position) {
        return weatherDayList.get(position);
    }

    @Override
    public int getItemViewType(int position) {
        return weatherDayList != null
                ? position % 2
                : 0;
    }

    @Override
    public int getItemCount() {
        return weatherDayList != null ? weatherDayList.size() : 0;
    }
}
