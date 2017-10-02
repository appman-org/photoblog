package org.appman.photoblog.upload.service;

import lombok.extern.slf4j.Slf4j;
import org.appman.photoblog.core.exception.ObjectModelValidationException;
import org.appman.photoblog.core.service.AbstractApplicationService;
import org.appman.photoblog.page.generic.model.AlbumModel;
import org.appman.photoblog.page.generic.model.PhotoModel;
import org.appman.photoblog.page.generic.service.AlbumDataManagementService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Pieter on 3/5/2017.
 */
@Slf4j
@Component
public class DefaultAlbumImportService extends AbstractApplicationService implements AlbumImportService {

    @Resource(name = "defaultPhotoPropertyReaderService")
    private PhotoPropertyReaderService photoPropertyReaderService;

    @Resource(name = "defaultAlbumService")
    private AlbumDataManagementService albumDataManagementService;

    @Override
    public void importAlbum(String albumId, String albumName, LocalDateTime localDateTime) throws ObjectModelValidationException {
        readAndSavePhotosForAlbum(albumId);
        saveAlbumProperties(albumId, albumName, localDateTime);
    }

    @Override
    public void readAndSavePhotosForAlbum(String albumId) {
        log.info("Start reading photomodels for albumId " + albumId);

        List<PhotoModel> photoModels= photoPropertyReaderService.readPhotoProperties(websiteConfig.getImageImportFolder(), albumId);

        log.info("Fininished reading photo's, now saving them");

        albumDataManagementService.insertPhotoModels(photoModels);

        log.info("Finished saving PhotoModels for albumId " + albumId);
    }

    @Override
    public void saveAlbumProperties(String albumId, String albumName, LocalDateTime publishDate) throws ObjectModelValidationException {
        log.info("Start saving Album properties for album: " + albumId + " on date " + publishDate.toString());

        AlbumModel albumModel = new AlbumModel(albumId, albumName, "images/" + albumId, publishDate, false);
        albumDataManagementService.insertAlbumModel(albumModel);

        log.info("Finished saving AlbumModel for album: " + albumId);
    }
}
