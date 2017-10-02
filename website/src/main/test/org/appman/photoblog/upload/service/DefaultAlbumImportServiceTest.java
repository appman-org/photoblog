package org.appman.photoblog.upload.service;

import org.appman.photoblog.AbstractPhotoBlogIntegrationTest;
import org.appman.photoblog.core.application.Application;
import org.appman.photoblog.core.exception.ObjectModelValidationException;
import org.appman.photoblog.page.generic.model.PhotoModel;
import org.appman.photoblog.page.generic.service.AlbumService;
import org.appman.photoblog.persistence.AbstractPersistenceDependentIntegrationTest;
import org.appman.photoblog.persistence.model.Photo;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Pieter on 3/5/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
//@Ignore
public class DefaultAlbumImportServiceTest extends AbstractPersistenceDependentIntegrationTest {

    @Resource(name = "defaultAlbumImportService")
    private AlbumImportService albumImportService;

    @Resource(name = "defaultAlbumService")
    private AlbumService albumService;

    @Test
    public void testReadAndSavePhotosForAlbum(){
        // Given
        String albumId = "testalbum0001";

        // When
        albumImportService.readAndSavePhotosForAlbum(albumId);

        List<PhotoModel> resultAll = albumService.getAllPhotoModelList();
        List<PhotoModel> resultAlbumOnly = albumService.getPhotoModelListForAlbum(albumId);

        // Then
        assertEquals(resultAll.size(), resultAlbumOnly.size());

    }

    @Test
    public void testSaveAlbumProperties() throws ObjectModelValidationException {
        // When
        albumImportService.saveAlbumProperties("testalbum0001", "Test Album 1", LocalDateTime.parse("2007-12-03T10:15:30"));
    }

    @Test
    public void testImportAlbum() throws ObjectModelValidationException {
        // When
        albumImportService.importAlbum("testalbum0001", "Test Album 1", LocalDateTime.parse("2007-12-03T10:15:30"));
    }
}
