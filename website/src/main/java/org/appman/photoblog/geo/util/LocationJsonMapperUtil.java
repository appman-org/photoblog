package org.appman.photoblog.geo.util;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.appman.photoblog.geo.builder.LocationListBuilder;
import org.appman.photoblog.geo.pojo.Location;
import org.appman.photoblog.page.generic.dto.PhotoViewDto;

import java.io.IOException;
import java.util.List;

/**
 * Created by Pieter on 1/22/2017.
 */
public class LocationJsonMapperUtil {

    public static String mapLocationsToJsonString(List<Location> locationList){
        ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(locationList);

        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }

    public static String geoLocationsJsonString(List<PhotoViewDto> photoViewDtos){
        LocationListBuilder builder = new LocationListBuilder();
        for (PhotoViewDto photoViewDto : photoViewDtos){
            if(photoViewDto.getLatitude() != null ) {
                builder.add(
                        photoViewDto.getLatitude(),
                        photoViewDto.getLongitude(),
                        photoViewDto.getPhotoPageUrl(),
                        photoViewDto.getImageUrl()
                );
            }
        }
        return mapLocationsToJsonString(builder.build());
    }
}
