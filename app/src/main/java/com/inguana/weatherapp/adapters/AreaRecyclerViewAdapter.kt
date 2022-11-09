package com.inguana.weatherapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.inguana.weatherapp.R
import com.inguana.weatherapp.adapters.interfaces.OnAreaListener
import com.inguana.weatherapp.adapters.viewholders.AreaViewHolder
import com.inguana.weatherapp.adapters.viewholders.ErrorViewHolder
import com.inguana.weatherapp.adapters.viewholders.ProgressBarViewHolder
import com.inguana.weatherapp.network.networkModel.response.Request

class AreaRecyclerViewAdapter(private val onAreaListener: OnAreaListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var areaNameList: MutableList<Request?>? = null
    fun setAreaNameList(areaNameList: MutableList<Request?>?) {
        this.areaNameList = areaNameList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var view: View? = null
        var result: RecyclerView.ViewHolder? = null
        when (viewType) {
            CITY_TYPE -> {
                view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.area_recyclerview_list_item, parent, false)
                result = AreaViewHolder(view, onAreaListener)
            }
            PROGRESS_BAR_TYPE -> {
                view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.progress_bar_recyclerview_list_item, parent, false)
                result = ProgressBarViewHolder(view)
            }
            ERROR_TYPE -> {
                view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.error_recyclerview_list_item, parent, false)
                result = ErrorViewHolder(view)
            }
            else -> {
                view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.area_recyclerview_list_item, parent, false)
                result = AreaViewHolder(view, onAreaListener)
            }
        }
        return result
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val itemViewType = getItemViewType(position)
        if (itemViewType == CITY_TYPE) {
            (holder as AreaViewHolder).resultTypeRecyclerViewListItem.text =
                areaNameList!![position]?.type
            holder.areaRecyclerViewListItem.text =
                areaNameList!![position]?.query
            val backgroundColour = if (position % 2 == 0) R.color.light_grey else R.color.white
            holder.itemView.setBackgroundColor(
                holder.itemView.resources.getColor(
                    backgroundColour,
                    holder.itemView.context.theme
                )
            )
        }
    }

    override fun getItemViewType(position: Int): Int {
        val result: Int
        result = if (areaNameList!![position]?.query == "LOADING IN PROGRESS...") {
            PROGRESS_BAR_TYPE
        } else if (areaNameList!![position]?.query == "ERROR...") {
            ERROR_TYPE
        } else {
            CITY_TYPE
        }
        return result
    }

    fun setQueryError() {
        hideLoading()
        val errorCityName = Request()
        errorCityName.query = "ERROR..."
        areaNameList!!.add(errorCityName)
        notifyDataSetChanged()
    }

    fun hideLoading() {
        if (isLoading) {
            for (city in areaNameList!!) {
                if (city?.query == "LOADING IN PROGRESS...") {
                    areaNameList!!.remove(city)
                }
            }
            notifyDataSetChanged()
        }
    }

    fun displayProgressBar() {
        if (!isLoading) {
            val cityName = Request()
            cityName.query = "LOADING IN PROGRESS..."
            val progressBarList: MutableList<Request?> = ArrayList()
            progressBarList.add(cityName)
            areaNameList = progressBarList
            notifyDataSetChanged()
        }
    }

    private val isLoading: Boolean
        private get() {
            var result = false
            if (areaNameList != null) {
                if (areaNameList!!.size > 0) {
                    if (areaNameList!![areaNameList!!.size - 1]?.query == "LOADING IN PROGRESS...") {
                        result = true
                    }
                }
            }
            return result
        }

    override fun getItemCount(): Int {
        return if (areaNameList != null) areaNameList!!.size else 0
    }

    companion object {
        private const val CITY_TYPE = 1
        private const val PROGRESS_BAR_TYPE = 2
        private const val ERROR_TYPE = 3
    }
}