package com.doubleclick.maproutebetweenmarkers;

import com.google.gson.annotations.SerializedName;

/**
 * Created By Eslam Ghazy on 1/15/2023
 */
public class OverviewPolyline {
    @SerializedName("points")
    private String points;

    public void setPoints(String points){
        this.points = points;
    }

    public String getPoints(){
        return points;
    }

    @Override
    public String toString() {
        return "OverviewPolyline{" +
                "points='" + points + '\'' +
                '}';
    }
}
