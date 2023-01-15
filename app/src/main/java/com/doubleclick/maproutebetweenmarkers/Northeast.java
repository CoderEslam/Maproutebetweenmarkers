package com.doubleclick.maproutebetweenmarkers;

import com.google.gson.annotations.SerializedName;

/**
 * Created By Eslam Ghazy on 1/15/2023
 */
public class Northeast {
    @SerializedName("lng")
    private double lng;

    @SerializedName("lat")
    private double lat;

    public void setLng(double lng){
        this.lng = lng;
    }

    public double getLng(){
        return lng;
    }

    public void setLat(double lat){
        this.lat = lat;
    }

    public double getLat(){
        return lat;
    }

    @Override
    public String toString() {
        return "Northeast{" +
                "lng=" + lng +
                ", lat=" + lat +
                '}';
    }
}
