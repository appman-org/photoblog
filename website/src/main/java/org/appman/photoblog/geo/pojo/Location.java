package org.appman.photoblog.geo.pojo;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pieter on 1/22/2017.
 */
public class Location {

    @Getter
    private final String latitude;

    @Getter
    private final String longitude;

    @Getter
    private List<LocationItem> locationItems;

    public Location(String latitude, String longitude){
        this.latitude = latitude;
        this.longitude = longitude;
        this.locationItems = new ArrayList<>();
    }

}
