package org.appman.photoblog.comments.service;

import org.appman.photoblog.core.service.AbstractApplicationService;
import org.appman.photoblog.page.generic.model.AlbumModel;
import org.appman.photoblog.page.generic.model.PhotoModel;
import org.appman.photoblog.page.generic.model.blog.BlogPostModel;
import org.springframework.stereotype.Component;

/**
 * Created by Pieter on 6/26/2016.
 */
@Component
public class DisqusCommentService extends AbstractApplicationService implements CommentsService{

    @Override
    public String getPhotoPageId(PhotoModel photoModel) {
        return "photo." + photoModel.getId();
    }

    @Override
    public String getAlbumPageId(AlbumModel albumModel) {
        return "album." + albumModel.getId();
    }

    @Override
    public String getBlogPageId(BlogPostModel blogPostModel) {
        return "textBlog." + blogPostModel.getId();
    }

    @Override
    public String getCommentsAccountId() {
        return websiteConfig.getDisqusAcount();
    }


}
