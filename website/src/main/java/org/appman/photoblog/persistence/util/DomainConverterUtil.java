package org.appman.photoblog.persistence.util;

import lombok.extern.slf4j.Slf4j;
import org.appman.photoblog.core.exception.ObjectModelValidationException;
import org.appman.photoblog.page.generic.model.AlbumModel;
import org.appman.photoblog.page.generic.model.PhotoModel;
import org.appman.photoblog.persistence.model.Album;
import org.appman.photoblog.persistence.model.Photo;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

/**
 * Created by Pieter on 3/5/2017.
 */
@Slf4j
public class DomainConverterUtil {

    public static List<PhotoModel> persistentToDomain(List<Photo> photos){
        return photos
                .stream()
                .map(photo -> persistentToDomain(photo))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(toList());
    }

    public static Optional<PhotoModel> persistentToDomain(Photo photo){
        if (photo == null){
            return Optional.empty();
        }
        try {
            return Optional.of(new PhotoModel(
                    photo.getId(),
                    photo.getUrl(),
                    photo.getAlbumId(),
                    photo.getDescription(),
                    photo.getLatitude(),
                    photo.getLongitude()
            ));
        } catch (ObjectModelValidationException e){
            log.error("Error converting Photo to PhotoModel with id " + photo.getId());
            log.error(e.toString());
            return Optional.empty();
        }
    }

    public static List<Photo> domainToPersistent(List<PhotoModel> photoModels){
        return photoModels
                .stream()
                .map(photoModel -> domainToPersistent(photoModel))
                .collect(toList());
    }

    public static Photo domainToPersistent(PhotoModel photoModel){
        Photo result = new Photo();
        result.setId(photoModel.getId());
        result.setUrl(photoModel.getUrl());
        result.setAlbumId(photoModel.getAlbumId());
        result.setDescription(photoModel.getDescription());
        result.setLatitude(photoModel.getLatitude());
        result.setLongitude(photoModel.getLongitude());
        return result;
    }

    public static Optional<AlbumModel> persistentToDomain(Album album){
        if(album == null){
            return Optional.empty();
        }
        try{
            return Optional.of(new AlbumModel(
                    album.getId(),
                    album.getAlbumName(),
                    album.getFolder(),
                    album.getPublishDate(),
                    album.isVisible()
            ));
        } catch (ObjectModelValidationException e){
            log.error("Error converting Album to AlbumModel with id " + album.getId());
            log.error(e.toString());
            return Optional.empty();
        }
    }

    public static Album domainToPersistent(AlbumModel albumModel){
        Album result = new Album();
        result.setId(albumModel.getId());
        result.setAlbumName(albumModel.getAlbumName());
        result.setFolder(albumModel.getFolder());
        result.setPublishDate(albumModel.getPublishDate());
        result.setVisible(albumModel.isVisible());
        return result;
    }
    
}
