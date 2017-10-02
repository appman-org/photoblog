package org.appman.photoblog.core.util;

import org.appman.photoblog.core.model.AbstractObjectModel;

import java.util.List;
import java.util.Optional;

/**
 * Created by Pieter on 4/21/2016.
 */
public final class ModelSearchUtil {

    private ModelSearchUtil(){}

    public static <T extends AbstractObjectModel> Optional<T> searchById(List<T> objectModelList, String searchId){
        return objectModelList.stream()
                .filter(objectModel -> objectModel.getId().equals(searchId))
                .findFirst();
    }

}
