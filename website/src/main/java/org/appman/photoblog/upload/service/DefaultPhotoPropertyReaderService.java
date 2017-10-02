package org.appman.photoblog.upload.service;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.lang.GeoLocation;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.*;
import lombok.extern.slf4j.Slf4j;
import org.appman.photoblog.core.exception.ObjectModelValidationException;
import org.appman.photoblog.page.generic.model.PhotoModel;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Pieter on 6/12/2016.
 */
@Slf4j
@Component
public class DefaultPhotoPropertyReaderService implements PhotoPropertyReaderService{

    @Override //    Old, to be removed after migration to DynamoDB
    public String getPhotoProperties(String location) {
        File folder = new File(location);
        File[] listOfFiles = folder.listFiles();

        StringBuilder data = new StringBuilder();

        for (File file : listOfFiles) {
            if (file.isDirectory()) {
                data.append(getPhotoPropertiesForDirectory(file, file.getName()));

            }
        }

        return data.toString();
    }

    @Override
    public List<PhotoModel> readPhotoProperties(String location, String albumId) {
        String albumLocation = location + File.separator + albumId;
        log.info("Location to readPhotoProperties: " + albumLocation);
        File folder = new File(albumLocation);

        return Arrays.asList(folder.listFiles())
                .stream()
                .map(photo -> createPhotoModelFromFile(photo, albumId))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    protected Optional<PhotoModel> createPhotoModelFromFile(File photo, String albumId){
        if (photo.isFile()) {
            String id = generatePhotoIdWithDate(getDateFromFile(photo));
            String url = albumId + "/" + photo.getName();
            String latitude = null;
            String longitude = null;
            Optional<GeoLocation> geoLocation = getGeoLocationFromFile(photo);
            if(geoLocation.isPresent()){
                latitude = Double.toString(geoLocation.get().getLatitude());
                longitude = Double.toString(geoLocation.get().getLongitude());
            }
            try {
                return Optional.of(new PhotoModel(id, url, albumId, null, latitude, longitude));
            } catch (ObjectModelValidationException e) {
                log.error("Error creating new PhotoModel from file name: " + photo.getName());
            }
        }
        return Optional.empty();
    }

    protected String getPhotoPropertiesForDirectory(File folder, String albumId){
        StringBuilder data = new StringBuilder();
        for (File photo : folder.listFiles()) {
            if (photo.isFile()) {
                data.append(generatePhotoIdWithDate(getDateFromFile(photo)) + ";" );
                data.append(albumId + "/" + photo.getName() + ";");
                data.append(albumId + ";");
                data.append(convertGeoLocationToString(getGeoLocationFromFile(photo)));
                data.append("\n");
            }
        }

        return data.toString();
    }

    protected String convertGeoLocationToString(Optional<GeoLocation> geoLocation){
        if(geoLocation.isPresent()){
            return geoLocation.get().getLatitude()
                    + ";" + geoLocation.get().getLongitude();
        }
            return "none;none";

    }

    protected String generatePhotoIdWithDate(Optional<LocalDateTime> date){
        if(date.isPresent()){
            return date.get()
                    .toString()
                    .substring(2)
                    .replace("-", "")
                    .replace("T", "")
                    .replace(":", "");

        }
        throw new RuntimeException("Problem while reading photo data");

    }

    protected Optional<LocalDateTime> getDateFromFile(File file){
        try {
            Metadata metadata = ImageMetadataReader.readMetadata(file);

            Directory directory = metadata.getFirstDirectoryOfType( ExifDirectoryBase.class );
            if( directory != null ){
                Date date = directory.getDate( ExifDirectoryBase.TAG_DATETIME );
                log.info("filename: " + file.getName());
                log.info("date: " + date.toString());
                return Optional.of( LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()));

                // http://stackoverflow.com/questions/19431234/converting-between-java-time-localdatetime-and-java-util-date
            }
        } catch (ImageProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    protected Optional<GeoLocation> getGeoLocationFromFile(File file){

        try {

            GeoLocation geoLocation = ImageMetadataReader
                    .readMetadata(file)
                    .getFirstDirectoryOfType(GpsDirectory.class)
                    .getGeoLocation();

            if(geoLocation != null) {
                return Optional.of(geoLocation);
            }

        } catch (ImageProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

}
