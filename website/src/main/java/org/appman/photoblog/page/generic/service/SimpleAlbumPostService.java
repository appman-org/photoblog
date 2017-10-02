package org.appman.photoblog.page.generic.service;

import org.appman.photoblog.page.generic.model.blog.AbstractMediaPostModel;
import org.appman.photoblog.page.generic.model.blog.AlbumPostModel;
import org.appman.photoblog.core.exception.ObjectModelValidationException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Created by Pieter on 5/23/2016.
 */
@Component
public class SimpleAlbumPostService extends AbstractSimpleMediaPostService {

    @Resource(name = "defaultAlbumService")
    private AlbumService albumService;

    public void setAlbumService(AlbumService albumService) {
        this.albumService = albumService;
    }

    @Override
    public List<AbstractMediaPostModel> getMediaPostModelList() {
        return albumService.getAlbumModelList()
                .stream()
                .map(albumModel -> {
                    try {
                        return new AlbumPostModel(albumModel);
                    } catch (ObjectModelValidationException e) {
                        e.printStackTrace();
                    }
                    return null;
                })
                .filter(albumPostModel -> albumPostModel != null)
                .sorted((e1, e2) -> -e1.compareTo(e2))
                .collect(toList());
    }


    @Override
    public List<AbstractMediaPostModel> getMediaPostModelList(int limit){
        return getMediaPostModelList(limit, 1);
    }
}
