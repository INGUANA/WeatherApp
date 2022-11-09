package com.inguana.weatherapp.adapters.viewholders

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.inguana.weatherapp.R
import com.inguana.weatherapp.adapters.interfaces.OnAreaListener

class AreaViewHolder(itemView: View, onAreaListener: OnAreaListener) :
    RecyclerView.ViewHolder(itemView), View.OnClickListener {
    var resultTypeRecyclerViewListItem: TextView
    var areaRecyclerViewListItem: TextView
    var onAreaListener: OnAreaListener

    init {
        resultTypeRecyclerViewListItem = itemView.findViewById(R.id.resultTypeRecyclerViewListItem)
        areaRecyclerViewListItem = itemView.findViewById(R.id.areaRecyclerViewListItem)
        itemView.setOnClickListener(this)
        this.onAreaListener = onAreaListener
    }

    override fun onClick(view: View) {
        onAreaListener.onAreaClick(areaRecyclerViewListItem.text.toString())
    }
}