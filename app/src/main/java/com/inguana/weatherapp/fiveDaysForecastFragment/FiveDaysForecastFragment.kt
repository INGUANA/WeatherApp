package com.inguana.weatherapp.fiveDaysForecastFragment

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
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import com.inguana.weatherapp.R
import com.inguana.weatherapp.adapters.ForecastRecyclerViewAdapter
import com.inguana.weatherapp.adapters.interfaces.HomeActivityFunctionalities
import com.inguana.weatherapp.adapters.interfaces.OnDayListener
import com.inguana.weatherapp.adapters.interfaces.ShowProgressBar
import com.inguana.weatherapp.searchAreasFragment.SearchAreasViewModel
import com.inguana.weatherapp.utils.ErrorMessageSnackbar
import com.inguana.weatherapp.utils.Utils

class FiveDaysForecastFragment : Fragment(), OnDayListener {
    private var recyclerViewFiveDaysForecastFragment: RecyclerView? = null
    private var forecastRecyclerViewAdapter: ForecastRecyclerViewAdapter? = null
    private var searchAreasViewModel: SearchAreasViewModel? = null
    private var navController: NavController? = null
    private var swipeRefreshFiveDayForecastFragment: SwipeRefreshLayout? = null
    private var areaFiveDayForecastFragment: TextView? = null
    private var homeActivityFunctionalities: HomeActivityFunctionalities? = null
    private var showProgressBar: ShowProgressBar? = null
    private var favouriteAreaFiveDayForecastFragment: ImageView? = null
    private var areaName: String? = null
    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            homeActivityFunctionalities = context as HomeActivityFunctionalities
            showProgressBar = context as ShowProgressBar
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
        subscribeObservers()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.five_day_forecast_fragment, container, false)
        recyclerViewFiveDaysForecastFragment =
            view.findViewById(R.id.recyclerViewFiveDaysForecastFragment)
        swipeRefreshFiveDayForecastFragment =
            view.findViewById(R.id.swipeRefreshFiveDayForecastFragment)
        areaFiveDayForecastFragment = view.findViewById(R.id.areaFiveDayForecastFragment)
        favouriteAreaFiveDayForecastFragment =
            view.findViewById(R.id.favouriteAreaFiveDayForecastFragment)
        initializeRecyclerView()
        swipeRefreshFiveDayForecastFragment?.setOnRefreshListener(OnRefreshListener { searchAreasViewModel!!.refreshWeather() })
        favouriteAreaFiveDayForecastFragment?.setOnClickListener(View.OnClickListener {
            if (homeActivityFunctionalities != null) {
                onFavouriteFunctionality()
                onFavouriteChangeDrawable()
            }
        })
        makeInitialCalls()
        return view
    }

    private fun makeInitialCalls() {
        showProgressBar!!.showProgressBar(true)
        searchAreasViewModel!!.refreshWeather()
    }

    private fun initializeRecyclerView() {
        forecastRecyclerViewAdapter = ForecastRecyclerViewAdapter(this)
        recyclerViewFiveDaysForecastFragment!!.adapter = forecastRecyclerViewAdapter
        recyclerViewFiveDaysForecastFragment!!.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun subscribeObservers() {
        searchAreasViewModel?.area?.observe(this) { area ->
            if (area != null) {
                showProgressBar!!.showProgressBar(false)
                swipeRefreshFiveDayForecastFragment!!.isRefreshing = false
                searchAreasViewModel!!.setArea(area)
                val currentAreaName = area.request[0]?.query
                areaName = currentAreaName
                areaFiveDayForecastFragment!!.text = areaName
                if (homeActivityFunctionalities != null) {
                    onFavouriteChangeDrawable()
                }
                forecastRecyclerViewAdapter!!.setWeatherDayList(area.weatherDayList)
            }
        }
        searchAreasViewModel?.errorMessage?.observe(this) { errorMessage ->
            ErrorMessageSnackbar.showErrorMessage(view, errorMessage)
            swipeRefreshFiveDayForecastFragment!!.isRefreshing = false
        }
    }

    override fun onDayClick(position: Int) {
        val action =
            FiveDaysForecastFragmentDirections.actionFiveDaysForecastFragmentToHourForecastFragment(
                position
            )
        navController!!.navigate(action)
    }

    private fun onFavouriteChangeDrawable() {
        if (Utils.isFavourite(homeActivityFunctionalities!!.getFavouriteSet(), areaName)) {
            favouriteAreaFiveDayForecastFragment!!.setImageDrawable(
                AppCompatResources.getDrawable(
                    requireContext(), R.drawable.ic_baseline_favorite_24
                )
            )
        } else {
            favouriteAreaFiveDayForecastFragment!!.setImageDrawable(
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