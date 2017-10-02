package org.appman.photoblog.page.generic.service.mock;

import org.appman.photoblog.page.generic.model.AlbumModel;
import org.appman.photoblog.page.generic.model.PhotoModel;
import org.appman.photoblog.page.generic.service.AlbumService;
import org.appman.photoblog.core.exception.ObjectModelValidationException;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Pieter on 4/13/2016.
 */
public class AlbumServiceMock implements AlbumService{
    AlbumModel albumModel;
    PhotoModel photoModel1;
    PhotoModel photoModel2;
    PhotoModel photoModel3;

    public AlbumServiceMock(){
        try {
            albumModel = new AlbumModel("XXXX", "Mocked Album Name!", "pictures", LocalDateTime.parse("2007-12-03T10:15:30"), true);

            photoModel1 = new PhotoModel("photo1", "temp/picture.JPG", "XXXX", "Photo 1 Description", null, null);
            photoModel2 = new PhotoModel("photo2", "temp/picture.JPG", "XXXX", "Photo 2 Description", null, null);
            photoModel3 = new PhotoModel("photo3", "temp/picture.JPG", "XXXX", "Photo 3 Description", null, null);
        } catch (ObjectModelValidationException e) {
            throw new RuntimeException("Error while initialising testdata");
        }

    }

    @Override
    public Optional<AlbumModel> getAlbumModel(String albumId) {
        return Optional.of(albumModel);
    }

    @Override
    public Optional<PhotoModel> getPhotoModel(String photoId) {
        return Optional.of(photoModel1);
    }

    @Override
    public List<PhotoModel> getAlbumThumbnailPhotos(String albumId, int count) {
        List<PhotoModel> result = new ArrayList<PhotoModel>();
        for(int i=0; i< count; i++){
            result.add(photoModel1);
        }
        return result;
    }

    @Override
    public List<PhotoModel> getPhotoModelListForAlbum(String albumId) {
        List<PhotoModel> result = new ArrayList<PhotoModel>();
        result.add(photoModel1);
        result.add(photoModel2);
        result.add(photoModel3);
        return result;
    }

    @Override
    public List<PhotoModel> getAllPhotoModelList() {
        return null;
    }

    @Override
    public String getDefaultImageUrl(PhotoModel photoModel) {
        return photoModel.getUrl();
    }

    @Override
    public List<AlbumModel> getAlbumModelList() {
        List<AlbumModel> result = new ArrayList<AlbumModel>();
        result.add(albumModel);
        return result;
    }

    @Override
    public List<AlbumModel> getAlbumModelList(Pageable pageable) {
        return null;
    }


}
