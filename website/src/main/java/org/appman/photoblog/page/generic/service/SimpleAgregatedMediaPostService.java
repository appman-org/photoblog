package org.appman.photoblog.page.generic.service;

import org.appman.photoblog.page.generic.model.blog.AbstractMediaPostModel;
import org.appman.photoblog.page.generic.util.PublishableObjectUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Pieter on 8/29/2016.
 */
@Component
public class SimpleAgregatedMediaPostService extends AbstractSimpleMediaPostService {

    @Resource(name = "simpleAlbumPostService")
    private MediaPostService albumMediaPostService;

    @Resource(name = "simpleBlogPostService")
    private MediaPostService blogPostService;

    public void setAlbumMediaPostService(MediaPostService albumMediaPostService) {
        this.albumMediaPostService = albumMediaPostService;
    }

    public void setBlogPostService(MediaPostService blogPostService) {
        this.blogPostService = blogPostService;
    }


    @Override
    public List<AbstractMediaPostModel> getMediaPostModelList() {
        List<AbstractMediaPostModel> blogPostModels = new ArrayList<>();

        blogPostModels.addAll(albumMediaPostService.getMediaPostModelList());
        blogPostModels.addAll(blogPostService.getMediaPostModelList());

        return PublishableObjectUtil.getSortedAndPublishedModels(blogPostModels);
    }

    @Override
    public List<AbstractMediaPostModel> getMediaPostModelList(int limit){
        // Assumes the input is already sorted

        List<AbstractMediaPostModel> blogPostModels = new ArrayList<>();

        blogPostModels.addAll(albumMediaPostService.getMediaPostModelList(limit));
        blogPostModels.addAll(blogPostService.getMediaPostModelList(limit));

        return PublishableObjectUtil.getSortedAndPublishedModels(blogPostModels)
                .stream()
                .limit((long)limit)
                .collect(Collectors.toList());
    }
}
