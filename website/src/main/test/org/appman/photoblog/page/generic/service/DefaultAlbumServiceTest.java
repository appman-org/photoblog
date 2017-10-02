package org.appman.photoblog.page.generic.service;

import lombok.extern.slf4j.Slf4j;
import org.appman.photoblog.AbstractPhotoBlogIntegrationTest;
import org.appman.photoblog.core.exception.ObjectModelValidationException;
import org.appman.photoblog.page.generic.model.AlbumModel;
import org.appman.photoblog.page.generic.model.PhotoModel;
import org.appman.photoblog.persistence.AbstractPersistenceDependentIntegrationTest;
import org.appman.photoblog.persistence.model.Album;
import org.appman.photoblog.persistence.model.Photo;
import org.appman.photoblog.persistence.repository.AlbumRepository;
import org.appman.photoblog.persistence.repository.PhotoRepository;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

/**
 * Created by Pieter on 3/6/2017.
 */
@Slf4j
public class DefaultAlbumServiceTest extends AbstractPersistenceDependentIntegrationTest {

    /*
     * Tests both the AlbumService and AlbumDataManagementService methods including
     * the integration with AlbumRepository, PhotoRepository and the DomainConverterUtil.
     * No separate tests are needed for the repo's and DomainConverterUtil.
     */

    @Resource(name = "defaultAlbumService")
    private AlbumService albumService;

    @Resource(name = "defaultAlbumService")
    private AlbumDataManagementService albumDataManagementService;

    @Autowired
    AlbumRepository albumRepository;

    @Autowired
    PhotoRepository photoRepository;

    private Album album1;
    private Album album2;
    private Album invalidAlbum;
    private Photo photo1;
    private Photo photo2;
    private Photo photoExtra;
    private Photo invalidPhoto;

    @Before
    public void initializeTestDataObjects(){
        log.info("Inserting test data objects in repo.");
        album1 = new Album();
        album1.setId("album1id");
        album1.setAlbumName("Album 1");
        album1.setPublishDate(LocalDateTime.parse("2007-12-03T10:15:30"));
        album1.setVisible(true);
        album1.setFolder("folder1");

        album2 = new Album();
        album2.setId("album2id");
        album2.setAlbumName("Album 2");
        album2.setPublishDate(LocalDateTime.parse("2007-12-03T10:15:30"));
        album2.setVisible(true);
        album2.setFolder("folder2");

        invalidAlbum = new Album();
        invalidAlbum.setId("invalidAlbumId");

        photo1 = new Photo();
        photo1.setId("photo1id");
        photo1.setUrl("photo1url");
        photo1.setAlbumId("album1id");

        photo2 = new Photo();
        photo2.setId("photo2id");
        photo2.setUrl("photo2url");
        photo2.setAlbumId("album1id");

        photoExtra = new Photo();
        photoExtra.setId("photoExtraId");
        photoExtra.setUrl("photoExtraUrl");
        photoExtra.setAlbumId("album2id");

        invalidPhoto = new Photo();
        invalidPhoto.setId("invalidPhotoId");

    }


    @Test
    public void testGetAlbumModelWithResult(){
        // Given:
        albumRepository.save(album1);

        // When:
        Optional<AlbumModel> result = albumService.getAlbumModel("album1id");

        // Then:
        assertEquals(true, result.isPresent());
        assertEquals("album1id", result.get().getId());
    }

    @Test
    public void testGetAlbumModelNoResult(){
        // Given:
        albumRepository.save(album1);

        // When:
        Optional<AlbumModel> result = albumService.getAlbumModel("non_existing_id");

        // Then:
        assertEquals(false, result.isPresent());
    }

    @Test
    public void testGetAlbumModelValidationError(){
        // Given:
        albumRepository.save(invalidAlbum);

        // When:
        Optional<AlbumModel> result = albumService.getAlbumModel("invalidAlbumId");

        // Then
        assertEquals(false, result.isPresent());
    }

    @Test
    public void testGetAlbumModelNullInput(){
        // When:
        Optional<AlbumModel> result = albumService.getAlbumModel(null);

        // Then
        assertEquals(false, result.isPresent());
    }

    @Test
    public void testGetPhotoModelWithResult(){
        // Given:
        photoRepository.save(photo1);

        // When:
        Optional<PhotoModel> result = albumService.getPhotoModel("photo1id");

        // Then:
        assertEquals(true, result.isPresent());
        assertEquals("photo1id", result.get().getId());
    }

    @Test
    public void testGetPhotoModelNoResult(){
        // Given: no photo's in db
        // When:
        Optional<PhotoModel> result = albumService.getPhotoModel("photo1id");

        // Then:
        assertEquals(false, result.isPresent());
    }

    @Test
    public void testGetPhotoModelValidationError(){
        // Given:
        photoRepository.save(invalidPhoto);

        // When:
        Optional<PhotoModel> result = albumService.getPhotoModel("invalidPhotoId");

        // Then:
        assertEquals(false, result.isPresent());
    }

    @Test
    public void testGetPhotoModelNullInput(){
        // When:
        Optional<PhotoModel> result = albumService.getPhotoModel(null);

        // Then:
        assertEquals(false, result.isPresent());
    }

    @Test
    public void testGetAlbumThumbnailPhotos(){
        // Given:
        photoRepository.save(photo2);
        photoRepository.save(photo1);

        // When:
        List<PhotoModel> result = albumService.getAlbumThumbnailPhotos("album1id", 1);

        // Then:
        assertEquals(1, result.size());
        // Uncertain which of the two we get back.
    }


    @Test
    public void testGetPhotoModelListForAlbum(){
        // Given:
        photoRepository.save(photo1);
        photoRepository.save(photo2);
        photoRepository.save(photoExtra);

        // When:
        List<PhotoModel> result = albumService.getPhotoModelListForAlbum("album1id");

        // Then:
        assertEquals(2, result.size());
    }

    @Test
    public void testGetAllPhotoModelList(){
        // Given:
        photoRepository.save(photo1);
        photoRepository.save(photo2);
        photoRepository.save(photoExtra);

        // When:
        List<PhotoModel> result = albumService.getAllPhotoModelList();

        // Then:
        assertEquals(3, result.size());
    }

    @Test
    public void testGetAlbumModelList(){
        // Given:
        albumRepository.save(album1);
        albumRepository.save(album2);

        // When:
        List<AlbumModel> result = albumService.getAlbumModelList();

        // Then:
        assertEquals(2, result.size());
    }

    @Test
    @Ignore
    public void testGetAlbumModelListPageable(){
        // method is not used yet, test to be implemented when this will be done.
    }

    @Test
    public void testInsertPhotoModels() throws ObjectModelValidationException {
        // Given
        String id = "photoId1";
        String url = "temp/picture1.JPG";
        String albumId = "albumId1";
        String description = "Photo 1 Description";
        String latitude = "1.1";
        String longitude = "2.2";

        List<PhotoModel> photoModels = new ArrayList<>();
        photoModels.add(new PhotoModel(id, url, albumId, description, latitude, longitude));
        photoModels.add(new PhotoModel("photoId2", "temp/picture2.JPG", "albumId1", "Photo 2 Description", null, null));
        photoModels.add(new PhotoModel("photoId3", "temp/picture3.JPG", "albumId2", "Photo 3 Description", null, null));

        // When
        albumDataManagementService.insertPhotoModels(photoModels);
        List<PhotoModel> resultAll = albumService.getAllPhotoModelList();
        List<PhotoModel> resultOfAlbum1 = albumService.getPhotoModelListForAlbum("albumId1");
        PhotoModel resultPhotoModel = albumService.getPhotoModel(id).get();

        // Then
        assertEquals(3, resultAll.size());
        assert resultAll.stream()
                .map(PhotoModel::getId)
                .collect(Collectors.toList())
                .containsAll(Arrays.asList("photoId1", "photoId2", "photoId3"));
        assertEquals(2, resultOfAlbum1.size());
        assertEquals(url, resultPhotoModel.getUrl());
        assertEquals(albumId, resultPhotoModel.getAlbumId());
        assertEquals(description, resultPhotoModel.getDescription());
        assertEquals(latitude, resultPhotoModel.getLatitude());
        assertEquals(longitude, resultPhotoModel.getLongitude());
    }
    @Test
    public void testInsertAlbumModel() throws ObjectModelValidationException {
        // Given
        String albumId = "albumId1";
        String date = "2016-05-28T10:15:30";
        String albumName = "albumName1";
        String albumFolder = "albumFolder1";
        AlbumModel albumModel1 = new AlbumModel(albumId, albumName, albumFolder, LocalDateTime.parse(date), true);

        String date2 = "2016-05-29T10:15:30";
        AlbumModel albumModel2 = new AlbumModel("albumId2", "albumName2", "albumFolder2", LocalDateTime.parse(date2), true);

        // When
        albumDataManagementService.insertAlbumModel(albumModel1);
        albumDataManagementService.insertAlbumModel(albumModel2);
        List<AlbumModel> resultList = albumService.getAlbumModelList();
        AlbumModel resultAlbumModel = albumService.getAlbumModel("albumId1").get();

        // Then
        assertEquals(2, resultList.size());
        assertEquals(date, resultAlbumModel.getPublishDate().toString());
        assertEquals(albumName, resultAlbumModel.getAlbumName());
        assertEquals(albumFolder, resultAlbumModel.getFolder());
    }

}
