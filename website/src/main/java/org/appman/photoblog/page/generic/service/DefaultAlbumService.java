package org.appman.photoblog.page.generic.service;

import lombok.extern.slf4j.Slf4j;
import org.appman.photoblog.core.service.AbstractApplicationService;
import org.appman.photoblog.page.generic.model.AlbumModel;
import org.appman.photoblog.page.generic.model.PhotoModel;
import org.appman.photoblog.persistence.repository.AlbumRepository;
import org.appman.photoblog.persistence.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;
import static org.appman.photoblog.persistence.util.DomainConverterUtil.*;

/**
 * Created by Pieter on 2/28/2017.
 */
@Component
@Slf4j
public class DefaultAlbumService extends AbstractApplicationService implements AlbumService, AlbumDataManagementService {

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private PhotoRepository photoRepository;

    @Override
    public Optional<AlbumModel> getAlbumModel(String albumId) {
        try {
            return persistentToDomain(albumRepository.findOne(albumId));
        } catch (RuntimeException e) {
            log.error("Error while looking up AlbumModel with id " + albumId);
            log.error(e.toString());
        }
        return Optional.empty();
    }

    @Override
    public Optional<PhotoModel> getPhotoModel(String photoId) {
        try {
            return persistentToDomain(photoRepository.findOne(photoId));
        } catch (RuntimeException e) {
            log.error("Error while looking up PhotoModel with id " + photoId);
            log.error(e.toString());
        }
        return Optional.empty();
    }

    @Override
    public List<PhotoModel> getAlbumThumbnailPhotos(String albumId, int count) {
        try {
            return photoRepository.findByAlbumId(albumId, new PageRequest(0, count))
                    .getContent()
                    .stream()
                    .map(photo -> persistentToDomain(photo))
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .sorted() // Keep in mind that we already get a paged result from unsorted source.
                    .collect(toList());
        } catch (RuntimeException e){
            log.error("Error while looking up thumbnail photos for albumId " + albumId);
            log.error(e.toString());
        }
        return new ArrayList<PhotoModel>();
    }

    @Override
    public List<PhotoModel> getPhotoModelListForAlbum(String albumId) {
        try {
            return photoRepository
                    .findByAlbumId(albumId)
                    .stream()
                    .map(photo -> persistentToDomain(photo))
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .sorted()
                    .collect(toList());
        } catch (RuntimeException e){
            log.error("Error getting list of PhotoModels for albumId " + albumId);
            log.error(e.toString());
        }
        return new ArrayList<PhotoModel>();
    }


    @Override
    public List<PhotoModel> getAllPhotoModelList() {
        try {
            return persistentToDomain(
                    StreamSupport.stream(photoRepository.findAll().spliterator(), true).collect(toList())
                );
        } catch (RuntimeException e){
            log.error("Error getting list of all PhotoModels");
            log.error(e.toString());
        }
        return new ArrayList<PhotoModel>();
    }

    @Override
    public String getDefaultImageUrl(PhotoModel photoModel) {
        return websiteConfig.getContent().getRemote().getImageUrlPrefix() + photoModel.getUrl();

    }

    @Override
    public List<AlbumModel> getAlbumModelList() {
        try {
            return StreamSupport.stream(albumRepository.findAll().spliterator(), true)
                    .map(album -> persistentToDomain(album))
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .collect(toList());
        } catch (RuntimeException e){
            log.error("Error while getting AlbumModel list");
            log.error(e.toString());
        }
        return new ArrayList<AlbumModel>();
    }

    @Override
    public List<AlbumModel> getAlbumModelList(Pageable pageable) { // not guaranteed to work yet, as no sort key in dynamodb
        try {
            return albumRepository.findAll(pageable)
                    .getContent()
                    .stream()
                    .map(album -> persistentToDomain(album))
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .sorted()
                    .collect(toList());
        } catch (RuntimeException e){
            log.error("Error while getting paged AlbumModel list");
            log.error(e.toString());
        }
        return new ArrayList<AlbumModel>();
    }

    @Override
    public void insertPhotoModels(List<PhotoModel> photoModels) {
        photoRepository.save(domainToPersistent(photoModels));
    }

    @Override
    public void insertAlbumModel(AlbumModel albumModel) {
        albumRepository.save(domainToPersistent(albumModel));
    }


}
