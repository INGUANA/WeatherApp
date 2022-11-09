package com.inguana.weatherapp.utils;

import static com.inguana.weatherapp.utils.Constants.PREFS_NAME;

import android.content.Context;
import android.content.SharedPreferences;

import com.inguana.weatherapp.network.networkModel.response.Request;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

public class Utils {

    public static void setPreference(Context context, String key, Set<String> value) {

        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.clear();
        editor.putStringSet(key, value);
        editor.apply();
    }

    public static Set<String> getPreference(Context context, String key) {
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
        return settings.getStringSet(key, new HashSet<String>());
    }

    public static boolean isFavourite(Set<String> favouriteSet, String areaName) {
        boolean result;
        if (favouriteSet != null && !favouriteSet.isEmpty()) {
            long count = favouriteSet.stream()
                    .filter(Favourite -> Favourite.equals(areaName))
                    .count();
            result = count == 1;
        } else {
            result = false;
        }
        return result;
    }

    public static String getDayFromDate(String dateString) {
        String result = "";
        if (dateString != null && !dateString.isEmpty()) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                result = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(sdf.parse(dateString));

            } catch (Exception e) {
                result = "";
            }
        }
        return result;
    }

    public static String convertToReadableTime(String time) {
        String result;
        do {
            if (time.length() >= 4)
                break;
            time = "0" + time;
        } while (time.length() <= 4);
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("HHmm", Locale.ENGLISH);
            result = new SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(sdf.parse(time));
        } catch (ParseException e) {
            result = "";
        }
        return result;
    }

    public static List<Request> convertArrayListToRequestList(List<String> favouriteList) {
        List<Request> requestsList = new ArrayList<>();
        for (String favItem : favouriteList) {
            requestsList.add(new Request("Favourite Area", favItem));
        }
        return requestsList;
    }
}
