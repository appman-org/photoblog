package org.appman.photoblog.page.generic.model.blog;

import org.appman.photoblog.core.exception.ObjectModelValidationException;
import org.appman.photoblog.core.model.AbstractPublishableObjectModel;

import java.time.LocalDateTime;

import static org.apache.commons.lang3.Validate.notBlank;
import static org.apache.commons.lang3.Validate.notNull;

/**
 * Created by Pieter on 5/23/2016.
 */
public abstract class AbstractMediaPostModel extends AbstractPublishableObjectModel {

    AbstractMediaPostModel(String id, LocalDateTime publishDate, boolean visible) throws ObjectModelValidationException {
        super(id, publishDate, visible);
    }

    public abstract String getTitle();

}
