package org.appman.photoblog.page.generic.facade;

import com.rometools.rome.feed.rss.Item;
import org.appman.photoblog.page.generic.dto.MediaPostViewDto;
import org.appman.photoblog.page.generic.service.MediaPostService;
import org.appman.photoblog.core.exception.PageNotFoundException;
import org.appman.photoblog.page.medialisting.dto.MediaListingPageViewDto;

import java.util.List;
import java.util.Optional;

/**
 * Created by Pieter on 4/24/2016.
 */
public interface MediaPostFacade {

    public List<MediaPostViewDto> getAllMediaPostViewDtos(int postPerPage, int pageNumber);

    public MediaListingPageViewDto getHomePageViewDto(int postPerPage, int pageNumber) throws PageNotFoundException;

    public MediaListingPageViewDto getAlbumListingPageViewDto(int postPerPage, int pageNumber) throws PageNotFoundException;

    public MediaListingPageViewDto getBlogListingPageViewDto(int postPerPage, int pageNumber) throws PageNotFoundException;

    public Optional<MediaPostViewDto> getMediaPostViewDto(String blogId);

    public List<Item> getRssFeedItems(int limit);

    public void setAgregatedMediaPostService(MediaPostService agregatedMediaPostService);
}
