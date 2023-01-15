package com.doubleclick.maproutebetweenmarkers;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created By Eslam Ghazy on 1/15/2023
 */
public class DirectionResponses {
    @SerializedName("routes")
    private List<RoutesItem> routes;

    @SerializedName("geocoded_waypoints")
    private List<GeocodedWaypointsItem> geocodedWaypoints;

    @SerializedName("status")
    private String status;

    public void setRoutes(List<RoutesItem> routes){
        this.routes = routes;
    }

    public List<RoutesItem> getRoutes(){
        return routes;
    }

    public void setGeocodedWaypoints(List<GeocodedWaypointsItem> geocodedWaypoints){
        this.geocodedWaypoints = geocodedWaypoints;
    }

    public List<GeocodedWaypointsItem> getGeocodedWaypoints(){
        return geocodedWaypoints;
    }

    public void setStatus(String status){
        this.status = status;
    }

    public String getStatus(){
        return status;
    }

    @Override
    public String toString() {
        return "DirectionResponses{" +
                "routes=" + routes +
                ", geocodedWaypoints=" + geocodedWaypoints +
                ", status='" + status + '\'' +
                '}';
    }
}
