package com.inguana.weatherapp.utils

import android.content.Context
import com.inguana.weatherapp.network.networkModel.response.Request
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object Utils {
    fun setPreference(context: Context, key: String, value: Set<String?>?) {
        val settings = context.getSharedPreferences(Constants.PREFS_NAME, 0)
        val editor = settings.edit()
        editor.clear()
        editor.putStringSet(key, value)
        editor.apply()
    }

    fun getPreference(context: Context, key: String): MutableSet<String?>? {
        val settings = context.getSharedPreferences(Constants.PREFS_NAME, 0)
        return settings.getStringSet(key, HashSet())
    }

    @kotlin.jvm.JvmStatic
    fun isFavourite(favouriteSet: Set<String?>?, areaName: String?): Boolean {
        val result: Boolean = if (favouriteSet != null && favouriteSet.isNotEmpty()) {
            val count = favouriteSet.stream()
                .filter { Favourite: String? -> Favourite == areaName }
                .count()
            count == 1L
        } else {
            false
        }
        return result
    }

    @kotlin.jvm.JvmStatic
    fun getDayFromDate(dateString: String?): String {
        var result = ""
        if (dateString != null && !dateString.isEmpty()) {
            result = try {
                val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                SimpleDateFormat("EEEE", Locale.ENGLISH).format(sdf.parse(dateString))
            } catch (e: Exception) {
                ""
            }
        }
        return result
    }

    @kotlin.jvm.JvmStatic
    fun convertToReadableTime(time: String?): String {
        var time = time
        val result: String
        do {
            if (time!!.length >= 4) break
            time = "0$time"
        } while (time!!.length <= 4)
        result = try {
            val sdf = SimpleDateFormat("HHmm", Locale.ENGLISH)
            SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(sdf.parse(time))
        } catch (e: ParseException) {
            ""
        }
        return result
    }

    @kotlin.jvm.JvmStatic
    fun convertArrayListToRequestList(favouriteList: List<String?>): MutableList<Request?> {
        val requestsList: MutableList<Request?> = ArrayList()
        for (favItem in favouriteList) {
            requestsList.add(Request("Favourite Area", favItem))
        }
        return requestsList
    }
}