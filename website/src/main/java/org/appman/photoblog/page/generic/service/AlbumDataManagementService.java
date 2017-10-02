package org.appman.photoblog.page.generic.service;

import org.appman.photoblog.page.generic.model.AlbumModel;
import org.appman.photoblog.page.generic.model.PhotoModel;

import java.util.List;

/**
 * Created by Pieter on 3/5/2017.
 */
public interface AlbumDataManagementService {

    public void insertPhotoModels(List<PhotoModel> photoModels);

    public void insertAlbumModel(AlbumModel albumModel);

}
