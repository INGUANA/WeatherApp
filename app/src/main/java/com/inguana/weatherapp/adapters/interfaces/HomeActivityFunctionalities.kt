package com.inguana.weatherapp.adapters.interfaces

interface HomeActivityFunctionalities {
    fun getFavouriteSet(): Set<String?>?
    fun setFavouriteSet(favouriteName: String?)
    fun removeFavouriteSet(removeName: String?)
}