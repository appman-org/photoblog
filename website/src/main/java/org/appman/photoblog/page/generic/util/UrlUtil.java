package org.appman.photoblog.page.generic.util;

import org.appman.photoblog.page.generic.model.AlbumModel;
import org.appman.photoblog.page.generic.model.PhotoModel;
import org.appman.photoblog.page.generic.model.blog.BlogPostModel;

/**
 * Created by Pieter on 11/8/2016.
 */
final public class UrlUtil {

    private UrlUtil(){}

    public static String blogPostUrl(BlogPostModel blogPostModel) {
        return "/blog/"
                + blogPostModel.getPublishDate().getYear() + "/"
                + blogPostModel.getPublishDate().getMonthValue() + "/"
                + blogPostModel.getPublishDate().getDayOfMonth() + "/"
                + blogPostModel.getId();
    }

    public static String albumPageUrl(AlbumModel albumModel){
        return "/album/" + albumModel.getId();
    }

    public static String photoPageUrl(PhotoModel photoModel){
        return "/photo/" + photoModel.getId();
    }

    public static String albumListingUrl(){
        return "/albums";
    }

    public static String blogListingUrl(){
        return "/blogs";
    }

    public static String rssFeedUrl() { return "/feed"; }
}
