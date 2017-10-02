package org.appman.photoblog.core.model;

import org.appman.photoblog.core.exception.ObjectModelValidationException;

import java.time.LocalDateTime;

import static org.apache.commons.lang3.Validate.notBlank;
import static org.apache.commons.lang3.Validate.notNull;

/**
 * Created by Pieter on 5/28/2016.
 */
public abstract class AbstractPublishableObjectModel extends AbstractObjectModel implements Comparable<AbstractPublishableObjectModel>{

    private final LocalDateTime publishDate;
    private final boolean visible;

    public AbstractPublishableObjectModel(String id, LocalDateTime publishDate, boolean visible) throws ObjectModelValidationException {
        super(id);

        try {
            notNull(publishDate);
            this.publishDate = publishDate;

            this.visible = visible;

        } catch (Exception e){
            throw new ObjectModelValidationException(e);
        }
    }

    public LocalDateTime getPublishDate() {
        return publishDate;
    }

    public boolean isVisible() {
        return visible;
    }

    public int compareTo(AbstractPublishableObjectModel o) {
        return getPublishDate().compareTo(o.getPublishDate());
    }
}
