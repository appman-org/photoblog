package org.appman.photoblog.page.generic.util;

import org.appman.photoblog.core.model.AbstractPublishableObjectModel;
import org.appman.photoblog.page.generic.model.blog.AbstractMediaPostModel;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * Created by Pieter on 8/30/2016.
 */
final public class PublishableObjectUtil {

    private PublishableObjectUtil(){}

    public static <T extends AbstractPublishableObjectModel> int lastPage(List<T> publishableModels, int itemPerPage){
        return roundUp(publishableModels.size(), itemPerPage);
    }

    public static <T extends AbstractPublishableObjectModel> List<T> getPagedPublishableModels(List<T> publishableModels, int postPerPage, int pageNumber){

        if ( pageNumber  > lastPage(publishableModels, postPerPage) || pageNumber < 1){
            return new ArrayList<>();
        }

        int fromIndex = postPerPage * (pageNumber - 1);

        int toIndex = Math.max(0, Math.min(fromIndex + postPerPage, publishableModels.size()));

        return publishableModels.subList(fromIndex, toIndex) ;
    }

    private static int roundUp(int num, int divisor) {
        return (num + divisor - 1) / divisor;
    }

    public static <T extends AbstractPublishableObjectModel> List<T> getSortedAndPublishedModels(List<T> publishableModels){
        return publishableModels
                .stream()
                .sorted((e1, e2) -> -e1.compareTo(e2))
                .filter(AbstractPublishableObjectModel :: isVisible)
                .filter(mediaPostModel -> mediaPostModel.getPublishDate().compareTo(LocalDateTime.now()) < 0 )
                .collect(Collectors.toList());
    }

    public static <T extends AbstractPublishableObjectModel> List<Integer> getYearsWithPublishedModels(List<T> publishableModels){
        return publishableModels
                .stream()
                .filter(AbstractPublishableObjectModel::isVisible)
                .map(AbstractPublishableObjectModel::getPublishDate)
                .map(LocalDateTime::getYear)
                .distinct()
                .sorted()
                .collect(toList());
    }

    public static <T extends AbstractPublishableObjectModel> List<T> getPublishedModelsInYear(List<T> publishableModels, int year){
        return publishableModels
                .stream()
                .filter(publishableModel -> publishableModel.getPublishDate().getYear() == year)
                .filter(AbstractPublishableObjectModel::isVisible)
                .collect(toList());
    }

    public static <T extends AbstractPublishableObjectModel> List<T> getPublishedModels(List<T> publishableModels){
        return publishableModels
                .stream()
                .filter(AbstractPublishableObjectModel::isVisible)
                .collect(toList());
    }

}
