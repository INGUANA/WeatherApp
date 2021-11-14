package com.inguana.weatherapp.adapters.interfaces;

import java.util.Set;

public interface HomeActivityFunctionalities {

    Set<String> getFavouriteSet();

    void setFavouriteSet(String favouriteName);

    void removeFavouriteSet(String removeName);
}
