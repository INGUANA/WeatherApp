package com.inguana.weatherapp

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.inguana.weatherapp.adapters.interfaces.HomeActivityFunctionalities
import com.inguana.weatherapp.utils.Constants
import com.inguana.weatherapp.utils.Utils

class HomeScreenActivity : BaseActivity(), HomeActivityFunctionalities {
    private var navController: NavController? = null
    private var favouriteSet: MutableSet<String?>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_screen_activity)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment?
        navController = navHostFragment!!.navController
        favouriteSet = Utils.getPreference(this, Constants.PREF_FAVOURITE)
    }

    override fun getFavouriteSet(): Set<String?>? {
        return favouriteSet
    }

    override fun setFavouriteSet(favouriteName: String?) {
        favouriteSet?.add(favouriteName)
        Utils.setPreference(this, Constants.PREF_FAVOURITE, favouriteSet)
    }

    override fun removeFavouriteSet(removeName: String?) {
        favouriteSet!!.remove(removeName)
        Utils.setPreference(this, Constants.PREF_FAVOURITE, favouriteSet)
    }
}