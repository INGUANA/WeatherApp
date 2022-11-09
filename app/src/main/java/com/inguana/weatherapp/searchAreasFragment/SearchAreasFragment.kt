package com.inguana.weatherapp.searchAreasFragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.inguana.weatherapp.R
import com.inguana.weatherapp.adapters.AreaRecyclerViewAdapter
import com.inguana.weatherapp.adapters.interfaces.HomeActivityFunctionalities
import com.inguana.weatherapp.adapters.interfaces.OnAreaListener
import com.inguana.weatherapp.network.networkModel.response.Request
import com.inguana.weatherapp.utils.ErrorMessageSnackbar
import com.inguana.weatherapp.utils.Utils

class SearchAreasFragment : Fragment(), OnAreaListener, LifecycleObserver {
    private var recyclerViewSearchCitiesFragment: RecyclerView? = null
    private var areaRecyclerViewAdapter: AreaRecyclerViewAdapter? = null
    private var searchAreasViewModel: SearchAreasViewModel? = null
    private var searchViewSearchCitiesFragment: SearchView? = null
    private var navController: NavController? = null
    private var homeActivityFunctionalities: HomeActivityFunctionalities? = null
    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
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
        subscribeObservers()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.search_areas_fragment, container, false)
        searchViewSearchCitiesFragment = view.findViewById(R.id.searchViewSearchCitiesFragment)
        recyclerViewSearchCitiesFragment = view.findViewById(R.id.recyclerViewSearchCitiesFragment)
        initializeRecyclerView()
        searchViewSearchCitiesFragment?.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                if (newText.isEmpty()) {
                    areaRecyclerViewAdapter!!.setAreaNameList(setFavouriteList())
                } else {
                    areaRecyclerViewAdapter!!.displayProgressBar()
                    searchAreasViewModel!!.searchWeather(newText)
                }
                return false
            }
        })
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initializeFavouriteAreas()
    }

    private fun initializeFavouriteAreas() {
        if (searchViewSearchCitiesFragment!!.query.length == 0) {
            areaRecyclerViewAdapter!!.setAreaNameList(setFavouriteList())
        }
    }

    private fun setFavouriteList(): MutableList<Request?>? {
        val favouriteList: List<String?> = if (homeActivityFunctionalities!!.getFavouriteSet() == null) {
            ArrayList()
        } else {
            ArrayList(homeActivityFunctionalities!!.getFavouriteSet())
        }
        return Utils.convertArrayListToRequestList(favouriteList)
    }

    private fun initializeRecyclerView() {
        areaRecyclerViewAdapter = AreaRecyclerViewAdapter(this)
        recyclerViewSearchCitiesFragment!!.adapter = areaRecyclerViewAdapter
        recyclerViewSearchCitiesFragment!!.layoutManager = LinearLayoutManager(context)
    }

    private fun subscribeObservers() {
        searchAreasViewModel?.area?.observe(this) { area ->
            if (area != null) {
                searchAreasViewModel!!.setArea(area)
                areaRecyclerViewAdapter!!.setAreaNameList(area.request)
                initializeFavouriteAreas()
            }
        }
        searchAreasViewModel?.errorMessage?.observe(this) { errorMessage ->
            ErrorMessageSnackbar.showErrorMessage(view, errorMessage)
            areaRecyclerViewAdapter!!.hideLoading()
            areaRecyclerViewAdapter!!.setQueryError()
        }
    }

    override fun onAreaClick(areaName: String?) {
        searchAreasViewModel!!.setSearchQuery(areaName)
        navController!!.navigate(R.id.action_searchAreasFragment_to_fiveDaysForecastFragment)
    }
}