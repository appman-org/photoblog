package org.appman.photoblog.page.generic.model.blog;

import org.appman.photoblog.page.generic.model.AlbumModel;
import org.appman.photoblog.core.exception.ObjectModelValidationException;

import static org.apache.commons.lang3.Validate.notNull;

/**
 * Created by Pieter on 5/23/2016.
 */
public class AlbumPostModel extends AbstractMediaPostModel {

    private AlbumModel albumModel;

    public AlbumPostModel(AlbumModel albumModel) throws ObjectModelValidationException {
        super(albumModel.getId(), albumModel.getPublishDate(), albumModel.isVisible());
        try {
            notNull(albumModel);
            this.albumModel = albumModel;

        } catch (Exception e){
            throw new ObjectModelValidationException(e);
        }
    }

    public AlbumModel getAlbumModel() {
        return albumModel;
    }

    @Override
    public String getTitle() {
        return albumModel.getAlbumName();
    }


}
