package com.inguana.weatherapp.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.inguana.weatherapp.network.networkModel.response.Request;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UtilsTest {

    @Test
    public void isFavourite_shouldReturnTrue() {
        Set<String> favouriteSet = new HashSet<>();
        favouriteSet.add("test");
        String areaName = "test";

        assertTrue(Utils.isFavourite(favouriteSet,areaName));
    }

    @Test
    public void isFavourite_shouldReturnFalse() {
        Set<String> favouriteSet = new HashSet<>();
        String areaName = "test";

        assertFalse(Utils.isFavourite(favouriteSet,areaName));
    }

    @Test
    public void isFavourite_shouldReturnFalseNotSame() {
        Set<String> favouriteSet = new HashSet<>();
        favouriteSet.add("test");
        String areaName = "test2";

        assertFalse(Utils.isFavourite(favouriteSet,areaName));
    }

    @Test
    public void isFavourite_nullSet() {
        String areaName = "test";

        assertFalse(Utils.isFavourite(null ,areaName));
    }

    @Test
    public void isFavourite_nullString() {
        Set<String> favouriteSet = new HashSet<>();
        favouriteSet.add("test");

        assertFalse(Utils.isFavourite(favouriteSet, null));
    }

    @Test
    public void getDayFromDate_shouldSucceed() {
        String dateString = "2021-11-14";
        String result = "Sunday";

        assertEquals(Utils.getDayFromDate(dateString), result);
    }

    @Test
    public void getDayFromDate_null() {
        String result = "";

        assertEquals(Utils.getDayFromDate(null), result);
    }

    @Test
    public void getDayFromDate_empty() {
        String dateString = "";
        String result = "";

        assertEquals(Utils.getDayFromDate(dateString), result);
    }

    @Test
    public void getDayFromDate_invalid() {
        String dateString = "#$%RTYGHU";
        String result = "";

        assertEquals(Utils.getDayFromDate(dateString), result);
    }

    @Test
    public void convertToReadableTime_shouldSucceed() {
        String time = "0000";
        String result = "12:00 AM";
        assertEquals(Utils.convertToReadableTime(time), result);
    }

    @Test
    public void convertToReadableTime_moreThanFour() {
        String time = "0100000";
        String result = "01:00 AM";
        assertEquals(Utils.convertToReadableTime(time), result);
    }

    @Test
    public void convertToReadableTime_lessThanFour() {
        String time = "100";
        String result = "01:00 AM";
        assertEquals(Utils.convertToReadableTime(time), result);
    }

    @Test
    public void convertToReadableTime_invalid() {
        String time = "10dasdfa0";
        String result = "";
        assertEquals(Utils.convertToReadableTime(time), result);
    }

    @Test
    public void convertArrayListToRequestList_shouldSucced() {
        List<String> favouriteList = new ArrayList<>();
        favouriteList.add("test");

        List<Request> actualResultList = Utils.convertArrayListToRequestList(favouriteList);
        assertEquals(actualResultList.get(0).getQuery(), favouriteList.get(0));
    }

    @Test
    public void convertArrayListToRequestList_empty() {
        List<String> favouriteList = new ArrayList<>();

        List<Request> actualResultList = Utils.convertArrayListToRequestList(favouriteList);
        assertEquals(actualResultList.size(), favouriteList.size());
    }
}