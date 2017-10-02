package org.appman.photoblog.page.content.model;

import org.appman.photoblog.core.exception.ObjectModelValidationException;
import org.appman.photoblog.core.model.AbstractObjectModel;

/**
 * Created by Pieter on 11/29/2016.
 */
public class ContentModel extends AbstractObjectModel {

    private String title;
    private String rawBodyText;

    public ContentModel(String id) throws ObjectModelValidationException {
        super(id);
    }

    public ContentModel(String id, String title, String rawBodyText) throws ObjectModelValidationException {
        super(id);
        this.title = title;
        this.rawBodyText = rawBodyText;
    }

    public String getTitle() {
        return title;
    }

    public String getRawBodyText() {
        return rawBodyText;
    }
}
