package org.appman.photoblog.upload.service;

import org.appman.photoblog.core.exception.ObjectModelValidationException;

import java.time.LocalDateTime;

/**
 * Created by Pieter on 3/5/2017.
 */
public interface AlbumImportService {

    public void importAlbum(String albumId, String albumName, LocalDateTime localDateTime) throws ObjectModelValidationException;

    public void readAndSavePhotosForAlbum(String albumId);

    public void saveAlbumProperties(String albumId, String albumName, LocalDateTime localDateTime) throws ObjectModelValidationException;
}
