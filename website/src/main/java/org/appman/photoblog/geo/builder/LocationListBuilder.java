package org.appman.photoblog.geo.builder;

import org.appman.photoblog.geo.pojo.Location;
import org.appman.photoblog.geo.pojo.LocationItem;

import java.util.ArrayList;
import java.util.List;

import org.appman.photoblog.geo.util.DistanceCalculatorUtil;

/**
 * Created by Pieter on 1/22/2017.
 */
public class LocationListBuilder {

    protected List<Location> locationList;

    public LocationListBuilder(){
        locationList = new ArrayList<>();
    }

    public LocationListBuilder add(String latitude, String longitude, String pageUrl, String photoUrl){
        Location location = findLocation(latitude, longitude);
        if( location == null){
            location = new Location(latitude, longitude);
            locationList.add(location);
        }
        location.getLocationItems().add(new LocationItem(pageUrl, photoUrl));
        return this;
    }

    public List<Location> build(){
        return locationList;
    }

    protected Location findLocation(String latitude, String longitude){
        Location searchLocation = new Location(latitude, longitude);
        for(Location location : locationList){
            if (DistanceCalculatorUtil.distance(location, searchLocation) < 0.007){
                return location;
            }
        }
        return null;
    }

}
