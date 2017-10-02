package org.appman.photoblog.page.generic.model;

import org.appman.photoblog.core.exception.ObjectModelValidationException;
import org.appman.photoblog.core.model.AbstractPublishableObjectModel;

import java.time.LocalDateTime;

import static org.apache.commons.lang3.Validate.*;

/**
 * Created by Pieter on 4/13/2016.
 */
public class AlbumModel extends AbstractPublishableObjectModel {

    private final String albumName;
    private final String folder; // Doesn't seem to have usage in production code


    public AlbumModel(String id, String albumName, String folder, LocalDateTime publishDate, boolean visible) throws ObjectModelValidationException {
        super(id, publishDate, visible);
        try {
            notNull(albumName);
            notBlank(albumName);
            this.albumName = albumName;

            notNull(folder);
            notBlank(folder);
            this.folder = folder;

        } catch (Exception e){
            throw new ObjectModelValidationException(e);
        }
    }

    public String getAlbumName() {
        return albumName;
    }

    public String getFolder() {
        return folder;
    }
}
