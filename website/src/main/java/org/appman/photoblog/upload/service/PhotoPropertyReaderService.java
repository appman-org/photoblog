package org.appman.photoblog.upload.service;

import org.appman.photoblog.page.generic.model.PhotoModel;

import java.util.List;

/**
 * Created by Pieter on 6/12/2016.
 */
public interface PhotoPropertyReaderService {

    //    Old, to be removed after migration to DynamoDB
    public String getPhotoProperties(String location);

    public List<PhotoModel> readPhotoProperties(String location, String albumId);

}
