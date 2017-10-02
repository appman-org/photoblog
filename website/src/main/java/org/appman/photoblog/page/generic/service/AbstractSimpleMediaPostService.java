package org.appman.photoblog.page.generic.service;

import org.appman.photoblog.core.service.AbstractApplicationService;
import org.appman.photoblog.page.generic.model.blog.AbstractMediaPostModel;

import java.util.List;

import static org.appman.photoblog.page.generic.util.MediaPostListUtil.getPagedMediaPostModels;
import static org.appman.photoblog.page.generic.util.MediaPostListUtil.getSortedAndPublishedMediaPostModels;
import static org.appman.photoblog.page.generic.util.MediaPostListUtil.lastPage;

/**
 * Created by Pieter on 8/31/2016.
 */
abstract public class AbstractSimpleMediaPostService extends AbstractApplicationService implements MediaPostService {

    @Override
    public List<AbstractMediaPostModel> getMediaPostModelList(int postPerPage, int pageNumber) {
        return getPagedMediaPostModels(getSortedAndPublishedMediaPostModels(getMediaPostModelList()), postPerPage,pageNumber);
    }

    @Override
    public int getNumberOfPages(int postPerPage) {
        return lastPage(getMediaPostModelList(), postPerPage);
    }


}
