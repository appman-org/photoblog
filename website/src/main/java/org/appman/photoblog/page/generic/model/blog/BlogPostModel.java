package org.appman.photoblog.page.generic.model.blog;

import org.appman.photoblog.core.exception.ObjectModelValidationException;

import java.time.LocalDateTime;

import static org.apache.commons.lang3.Validate.notEmpty;
import static org.apache.commons.lang3.Validate.notNull;

/**
 * Created by Pieter on 5/23/2016.
 */
public class BlogPostModel extends AbstractMediaPostModel {

    private final String title;
    private final String rawBodyText;
    private final String singlePhotoId;

    public BlogPostModel(String id, LocalDateTime publishDate, boolean visible, String title, String singlePhotoId, String rawBodyText) throws ObjectModelValidationException {
        super(id, publishDate, visible);
        try {
            notNull(title);
            notEmpty(title);
            this.title = title;

            notNull(singlePhotoId);
            notEmpty(singlePhotoId);
            this.singlePhotoId = singlePhotoId;

            notNull(rawBodyText);
            notEmpty(rawBodyText);
            this.rawBodyText = rawBodyText;
        } catch (Exception e){
            throw new ObjectModelValidationException(e);
        }
    }

    @Override
    public String getTitle() {
        return title;
    }

    public String getRawBodyText() {
        return rawBodyText;
    }

    public String getSinglePhotoId() {
        return singlePhotoId;
    }
}
