package org.appman.photoblog.comments.service;

import org.appman.photoblog.page.generic.model.AlbumModel;
import org.appman.photoblog.page.generic.model.PhotoModel;
import org.appman.photoblog.page.generic.model.blog.BlogPostModel;

/**
 * Created by Pieter on 6/26/2016.
 */
public interface CommentsService {

    public String getPhotoPageId(PhotoModel photoModel);

    public String getAlbumPageId(AlbumModel albumModel);

    public String getBlogPageId(BlogPostModel blogPostModel);

    public String getCommentsAccountId();

}
