package com.inguana.weatherapp.adapters.viewholders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.inguana.weatherapp.R;
import com.inguana.weatherapp.adapters.interfaces.OnAreaListener;

public class AreaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView resultTypeRecyclerViewListItem;
    TextView areaRecyclerViewListItem;
    OnAreaListener onAreaListener;

    public TextView getResultTypeRecyclerViewListItem() {
        return resultTypeRecyclerViewListItem;
    }

    public TextView getAreaRecyclerViewListItem() {
        return areaRecyclerViewListItem;
    }

    public AreaViewHolder(@NonNull View itemView, OnAreaListener onAreaListener) {
        super(itemView);
        resultTypeRecyclerViewListItem = itemView.findViewById(R.id.resultTypeRecyclerViewListItem);
        areaRecyclerViewListItem = itemView.findViewById(R.id.areaRecyclerViewListItem);
        itemView.setOnClickListener(this);
        this.onAreaListener = onAreaListener;

    }

    @Override
    public void onClick(View view) {
        onAreaListener.onAreaClick(areaRecyclerViewListItem.getText().toString());
    }
}
