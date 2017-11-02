package org.appman.photoblog.page.generic.service;

import org.appman.photoblog.page.generic.model.AlbumModel;
import org.appman.photoblog.page.generic.model.PhotoModel;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Created by Pieter on 4/13/2016.
 */
public interface AlbumService {

    public Optional<AlbumModel> getAlbumModel(String albumId);

    public Optional<PhotoModel> getPhotoModel(String photoId);

    public List<PhotoModel> getAlbumThumbnailPhotos(String albumId, int count);

    public List<PhotoModel> getPhotoModelListForAlbum(String albumId);

    public List<PhotoModel> getAllPhotoModelList();

    // DefaultImage, because later an image can have alternate versions (small, big, thumbnail, etc)
    public String getDefaultImageUrl(PhotoModel photoModel);

    public List<AlbumModel> getAlbumModelList();

    public List<AlbumModel> getAlbumModelList(Pageable pageable); // not used yet

    public List<PhotoModel> getPhotoModelListForAlbumIds(List<String> albumIds);

}
