package com.inguana.weatherapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.inguana.weatherapp.R;
import com.inguana.weatherapp.adapters.interfaces.OnAreaListener;
import com.inguana.weatherapp.adapters.viewholders.AreaViewHolder;
import com.inguana.weatherapp.adapters.viewholders.ErrorViewHolder;
import com.inguana.weatherapp.adapters.viewholders.ProgressBarViewHolder;
import com.inguana.weatherapp.network.networkModel.response.Request;

import java.util.ArrayList;
import java.util.List;

public class AreaRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int CITY_TYPE = 1;
    private static final int PROGRESS_BAR_TYPE = 2;
    private static final int ERROR_TYPE = 3;

    private List<Request> areaNameList;
    private OnAreaListener onAreaListener;

    public AreaRecyclerViewAdapter(OnAreaListener onAreaListener) {
        this.onAreaListener = onAreaListener;
    }

    public void setAreaNameList(List<Request> areaNameList) {
        this.areaNameList = areaNameList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        RecyclerView.ViewHolder result = null;

        switch (viewType) {
            case CITY_TYPE: {
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.area_recyclerview_list_item, parent, false);
                result = new AreaViewHolder(view, onAreaListener);
                break;
            }
            case PROGRESS_BAR_TYPE: {
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.progress_bar_recyclerview_list_item, parent, false);
                result = new ProgressBarViewHolder(view);
                break;
            }
            case ERROR_TYPE: {
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.error_recyclerview_list_item, parent, false);
                result = new ErrorViewHolder(view);
                break;
            }
            default: {
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.area_recyclerview_list_item, parent, false);
                result = new AreaViewHolder(view, onAreaListener);
            }
        }

        return result;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        int itemViewType = getItemViewType(position);
        if (itemViewType == CITY_TYPE) {
            ((AreaViewHolder) holder).getResultTypeRecyclerViewListItem().setText(areaNameList.get(position).getType());
            ((AreaViewHolder) holder).getAreaRecyclerViewListItem().setText(areaNameList.get(position).getQuery());

            int backgroundColour = position % 2 == 0 ? R.color.light_grey : R.color.white;
            holder.itemView.setBackgroundColor(holder.itemView.getResources().getColor(backgroundColour, holder.itemView.getContext().getTheme()));
        }
    }

    @Override
    public int getItemViewType(int position) {
        int result;

        if (areaNameList.get(position).getQuery().equals("LOADING IN PROGRESS...")) {
            result = PROGRESS_BAR_TYPE;
        } else if (areaNameList.get(position).getQuery().equals("ERROR...")) {
            result = ERROR_TYPE;
        } else {
            result = CITY_TYPE;
        }

        return result;
    }

    public void setQueryError() {
        hideLoading();
        Request errorCityName = new Request();
        errorCityName.setQuery("ERROR...");
        areaNameList.add(errorCityName);
        notifyDataSetChanged();
    }

    public void hideLoading() {
        if (isLoading()) {
            for (Request city : areaNameList) {
                if (city.getQuery().equals("LOADING IN PROGRESS...")) {
                    areaNameList.remove(city);
                }
            }
            notifyDataSetChanged();
        }
    }

    public void displayProgressBar() {
        if (!isLoading()) {
            Request cityName = new Request();
            cityName.setQuery("LOADING IN PROGRESS...");
            List<Request> progressBarList = new ArrayList<>();
            progressBarList.add(cityName);
            areaNameList = progressBarList;
            notifyDataSetChanged();
        }
    }

    private boolean isLoading() {
        boolean result = false;
        if (areaNameList != null) {
            if (areaNameList.size() > 0) {
                if (areaNameList.get(areaNameList.size() - 1).getQuery().equals("LOADING IN PROGRESS...")) {
                    result = true;
                }
            }
        }
        return result;
    }

    @Override
    public int getItemCount() {
        return areaNameList != null ? areaNameList.size() : 0;
    }
}
