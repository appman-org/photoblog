package org.appman.photoblog.page.common.pageattributes;

/**
 * Created by Pieter on 10/18/2016.
 */
public class PageAttributes {


    // see
    // http://stackoverflow.com/questions/3732608/how-to-reference-constants-in-el

    private final static String prefix = "data-st-";
    private static String addPrefix(String input){ return prefix + input;}

    // Generic
    public static final String BLOCK_BREADCRUMB = addPrefix("block-breadcrumb");
    public static final String BLOCK_COMMENTS = addPrefix("block-comments");

    // Home Page
    public static final String MEDIALISTING_PAGE_CONTENT = addPrefix("medialisting-page-content");
    public static final String BLOG_OVERVIEW_POST_TITLE = addPrefix("blog-overview-post-title");
    public static final String PAGINATION_BLOCK = addPrefix("pagination-block");
    public static final String PAGINATION_ITEM = addPrefix("pagination-item");

    // Album page
    public static final String ALBUM_CONTENT = addPrefix("album-content");
    public static final String ALBUM_THUMBNAIL = addPrefix("album-thumbnail");

    // Photo page
    public static final String PHOTO_CONTENT = addPrefix("photo-content");
    public static final String PHOTO_FULL_IMAGE = addPrefix("full-image");

    // Text blog post page
    public static final String TEXT_POST_CONTENT = addPrefix("text-blog-post-content");
    public static final String TEXT_POST_FULL_TEXT = addPrefix("text-post-full-text");

    // Content page
    public static final String CONTENT_PAGE_CONTENT = addPrefix("content-page-content");

    // Map page
    public static final String MAP_PAGE_CONTENT = addPrefix("map-page-content");

}
