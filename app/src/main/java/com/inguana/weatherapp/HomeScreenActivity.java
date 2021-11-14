package com.inguana.weatherapp;

import static com.inguana.weatherapp.utils.Constants.PREF_FAVOURITE;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.inguana.weatherapp.adapters.interfaces.HomeActivityFunctionalities;
import com.inguana.weatherapp.utils.Utils;

import java.util.Set;

public class HomeScreenActivity extends BaseActivity implements HomeActivityFunctionalities {

    private NavController navController;

    private Set<String> favouriteSet;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen_activity);

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        navController = navHostFragment.getNavController();
        favouriteSet = Utils.getPreference(this, PREF_FAVOURITE);
    }

    @Override
    public Set<String> getFavouriteSet() {
        return favouriteSet;
    }

    @Override
    public void setFavouriteSet(String favouriteName) {
        favouriteSet.add(favouriteName);
        Utils.setPreference(this, PREF_FAVOURITE, favouriteSet);
    }

    @Override
    public void removeFavouriteSet(String removeName) {
        favouriteSet.remove(removeName);
        Utils.setPreference(this, PREF_FAVOURITE, favouriteSet);
    }
}
