package com.inguana.weatherapp.hourForecastFragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.inguana.weatherapp.R
import com.inguana.weatherapp.adapters.HourForecastRecyclerViewAdapter
import com.inguana.weatherapp.adapters.interfaces.HomeActivityFunctionalities
import com.inguana.weatherapp.adapters.interfaces.ShowProgressBar
import com.inguana.weatherapp.searchAreasFragment.SearchAreasViewModel
import com.inguana.weatherapp.utils.Utils

class HourForecastFragment : Fragment() {
    private var recyclerViewHourForecastFragment: RecyclerView? = null
    private var hourForecastRecyclerViewAdapter: HourForecastRecyclerViewAdapter? = null
    private var searchAreasViewModel: SearchAreasViewModel? = null
    private var navController: NavController? = null
    private var areaHourForecastFragment: TextView? = null
    private var showProgressBar: ShowProgressBar? = null
    private var homeActivityFunctionalities: HomeActivityFunctionalities? = null
    private var favouriteAreaHourForecastFragment: ImageView? = null
    private var selectedDay = 0
    private var areaName: String? = null
    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            showProgressBar = context as ShowProgressBar
            homeActivityFunctionalities = context as HomeActivityFunctionalities
        } catch (e: ClassCastException) {
            e.printStackTrace()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        searchAreasViewModel = ViewModelProvider(requireActivity()).get(
            SearchAreasViewModel::class.java
        )
        navController = NavHostFragment.findNavController(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.hour_forecast_fragment, container, false)
        recyclerViewHourForecastFragment = view.findViewById(R.id.recyclerViewHourForecastFragment)
        areaHourForecastFragment = view.findViewById(R.id.areaHourForecastFragment)
        favouriteAreaHourForecastFragment =
            view.findViewById(R.id.favouriteAreaHourForecastFragment)
        selectedDay = HourForecastFragmentArgs.fromBundle(
            arguments ?: Bundle()
        ).selectedDay
        areaName = searchAreasViewModel?.currentArea?.request?.get(0)?.query
        areaHourForecastFragment?.text = areaName
        if (homeActivityFunctionalities != null) {
            onFavouriteChangeDrawable()
        }
        initializeRecyclerView()
        favouriteAreaHourForecastFragment?.setOnClickListener(View.OnClickListener {
            if (homeActivityFunctionalities != null) {
                onFavouriteFunctionality()
                onFavouriteChangeDrawable()
            }
        })
        return view
    }

    private fun initializeRecyclerView() {
        hourForecastRecyclerViewAdapter = HourForecastRecyclerViewAdapter()
        recyclerViewHourForecastFragment!!.adapter = hourForecastRecyclerViewAdapter
        recyclerViewHourForecastFragment!!.layoutManager = LinearLayoutManager(context)
        hourForecastRecyclerViewAdapter!!.setWeatherHourList(searchAreasViewModel?.currentArea?.weatherDayList?.get(selectedDay)?.weatherHour)
    }

    private fun onFavouriteChangeDrawable() {
        if (Utils.isFavourite(homeActivityFunctionalities!!.getFavouriteSet(), areaName)) {
            favouriteAreaHourForecastFragment!!.setImageDrawable(
                AppCompatResources.getDrawable(
                    requireContext(), R.drawable.ic_baseline_favorite_24
                )
            )
        } else {
            favouriteAreaHourForecastFragment!!.setImageDrawable(
                AppCompatResources.getDrawable(
                    requireContext(), R.drawable.ic_round_favorite_border_24
                )
            )
        }
    }

    private fun onFavouriteFunctionality() {
        if (Utils.isFavourite(homeActivityFunctionalities!!.getFavouriteSet(), areaName)) {
            homeActivityFunctionalities!!.removeFavouriteSet(areaName)
        } else {
            homeActivityFunctionalities!!.setFavouriteSet(areaName)
        }
    }
}