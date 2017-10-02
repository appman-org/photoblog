package org.appman.photoblog.page.album.facade;

import org.appman.photoblog.page.generic.dto.PhotoViewDto;
import org.appman.photoblog.page.album.dto.AlbumPageDto;
import org.appman.photoblog.page.album.dto.PhotoPageDto;
import org.appman.photoblog.page.generic.model.PhotoModel;

import java.util.List;
import java.util.Optional;

/**
 * Created by Pieter on 4/13/2016.
 */
public interface AlbumFacade {



    Optional<PhotoPageDto> getPhotoPageDto(String photoId );

    Optional<PhotoViewDto> getPhotoViewDto(String photoId);

    Optional<AlbumPageDto> getAlbumPageDto(String albumId );

    List<PhotoViewDto> createPhotoViewDtoListFromPhotoModels(List<PhotoModel> photoModels);

    List<PhotoViewDto> getPhotoViewDtoListForAlbum(String albumId);

}
