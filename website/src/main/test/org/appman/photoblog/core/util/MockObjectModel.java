package org.appman.photoblog.core.util;

import org.appman.photoblog.core.exception.ObjectModelValidationException;
import org.appman.photoblog.core.model.AbstractObjectModel;

/**
 * Created by Pieter on 4/21/2016.
 */
public class MockObjectModel extends AbstractObjectModel{

    MockObjectModel(String id) throws ObjectModelValidationException {
        super(id);
    }

}
