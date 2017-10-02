package org.appman.photoblog.page.generic.service;

import org.appman.photoblog.page.generic.model.blog.AbstractMediaPostModel;

import java.util.List;

/**
 * Created by Pieter on 5/23/2016.
 */
public interface MediaPostService {

    public List<AbstractMediaPostModel> getMediaPostModelList();

    public List<AbstractMediaPostModel> getMediaPostModelList(int postPerPage, int pageNumber);

    public int getNumberOfPages(int postPerPage);

    public List<AbstractMediaPostModel> getMediaPostModelList(int limit);
}
