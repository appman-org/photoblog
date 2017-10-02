package org.appman.photoblog.page.generic.util;

import org.appman.photoblog.page.generic.model.blog.AbstractMediaPostModel;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Pieter on 8/30/2016.
 */
final public class MediaPostListUtil {

    private MediaPostListUtil(){}

    public static int lastPage(List<AbstractMediaPostModel> mediaPostModels, int blogPerPage){
        return roundUp(mediaPostModels.size(), blogPerPage);
    }

    public static List<AbstractMediaPostModel> getPagedMediaPostModels(List<AbstractMediaPostModel> mediaPostModels, int postPerPage, int pageNumber){

        if ( pageNumber  > lastPage(mediaPostModels, postPerPage) || pageNumber < 1){
            return new ArrayList<AbstractMediaPostModel>();
        }

        int fromIndex = postPerPage * (pageNumber - 1);

        int toIndex = Math.max(0, Math.min(fromIndex + postPerPage, mediaPostModels.size()));

        List<AbstractMediaPostModel> sublist = mediaPostModels.subList(fromIndex, toIndex) ;
        return sublist;
    }

    private static int roundUp(int num, int divisor) {
        return (num + divisor - 1) / divisor;
    }

    public static List<AbstractMediaPostModel> getSortedAndPublishedMediaPostModels(List<AbstractMediaPostModel> mediaPostModels){
        return mediaPostModels
                .stream()
                .sorted((e1, e2) -> -e1.compareTo(e2))
                .filter(mediaPostModel -> mediaPostModel.isVisible())
                .filter(mediaPostModel -> mediaPostModel.getPublishDate().compareTo(LocalDateTime.now()) < 0 )
                .collect(Collectors.toList());
    }

}
