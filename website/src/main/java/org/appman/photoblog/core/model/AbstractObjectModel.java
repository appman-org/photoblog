package org.appman.photoblog.core.model;

import org.appman.photoblog.core.exception.ObjectModelValidationException;
import static org.apache.commons.lang3.Validate.*;


/**
 * Created by Pieter on 4/21/2016.
 */
public class AbstractObjectModel {

    private final String id;

    public AbstractObjectModel(String id) throws ObjectModelValidationException {
        try {
            notNull(id);
            notBlank(id);
            this.id = id;
        } catch (Exception e) {
            throw new ObjectModelValidationException(e);
        }
    }
    public String getId() {
        return id;
    }

    String validatedId(String id) throws ObjectModelValidationException{
        if(id == null || id.equals("")){
            throw new ObjectModelValidationException("id can not be null or empty");
        }
        return id;
    }

}
