package org.appman.photoblog.geo.util;


import org.appman.photoblog.geo.pojo.Location;

import java.lang.*;

public class DistanceCalculatorUtil
{

    public static double distance(Location location1, Location location2){
        return distance(
                Double.parseDouble(location1.getLatitude()),
                Double.parseDouble(location1.getLongitude()),
                Double.parseDouble(location2.getLatitude()),
                Double.parseDouble(location2.getLongitude())
        );
    }

    private static double distance(double latitude1, double longitude1, double latitude2, double longitude2) {
        double theta = longitude1 - longitude2;
        double dist = Math.sin(degreeToRadial(latitude1)) * Math.sin(degreeToRadial(latitude2))
                + Math.cos(degreeToRadial(latitude1)) * Math.cos(degreeToRadial(latitude2)) * Math.cos(degreeToRadial(theta));
        dist = Math.acos(dist);
        dist = radialToDegree(dist);
        dist = dist * 60 * 1.1515;

        return (dist);
    }


    private static double degreeToRadial(double deg) {
        return (deg * Math.PI / 180.0);
    }


    private static double radialToDegree(double rad) {
        return (rad * 180 / Math.PI);
    }



}