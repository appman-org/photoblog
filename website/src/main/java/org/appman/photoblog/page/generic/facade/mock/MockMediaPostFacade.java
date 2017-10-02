package org.appman.photoblog.page.generic.facade.mock;

import com.rometools.rome.feed.rss.Item;
import org.appman.photoblog.core.exception.PageNotFoundException;
import org.appman.photoblog.page.generic.dto.MediaPostViewDto;
import org.appman.photoblog.page.generic.dto.PhotoViewDto;
import org.appman.photoblog.page.generic.facade.MediaPostFacade;
import org.appman.photoblog.page.generic.service.MediaPostService;
import org.appman.photoblog.page.medialisting.dto.MediaListingPageViewDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Pieter on 4/24/2016.
 */
public class MockMediaPostFacade implements MediaPostFacade {
    @Override
    public List<MediaPostViewDto> getAllMediaPostViewDtos(int postPerPage, int pageNumber) {
        PhotoViewDto photoViewDto = new PhotoViewDto();
        photoViewDto.setImageUrl("temp/picture.JPG");
        photoViewDto.setAltText("Alt image text");

        List<PhotoViewDto> photoViewDtoList = new ArrayList<PhotoViewDto>();
        for(int i = 0; i<10; i++){
            photoViewDtoList.add(photoViewDto);
        }

        MediaPostViewDto mediaPostViewDto = new MediaPostViewDto();
        mediaPostViewDto.setUrl("/album/bla");
        mediaPostViewDto.setAlbumPhotos(photoViewDtoList);


        List<MediaPostViewDto> result = new ArrayList<MediaPostViewDto>();
        for(int i = 0; i<25; i++){
            result.add(mediaPostViewDto);
        }

        return result;
    }

    @Override
    public MediaListingPageViewDto getHomePageViewDto(int postPerPage, int pageNumber) {
        MediaListingPageViewDto result = new MediaListingPageViewDto();
        result.setMediaPostViewDtos(getAllMediaPostViewDtos(1, 1));
        return result;
    }

    @Override
    public MediaListingPageViewDto getAlbumListingPageViewDto(int postPerPage, int pageNumber) throws PageNotFoundException {
        return null;
    }

    @Override
    public MediaListingPageViewDto getBlogListingPageViewDto(int postPerPage, int pageNumber) throws PageNotFoundException {
        return null;
    }

    @Override
    public Optional<MediaPostViewDto> getMediaPostViewDto(String blogId) {
        return null;
    }

    @Override
    public List<Item> getRssFeedItems(int limit) {
        throw new RuntimeException("To be implemented");
    }

    public void setAgregatedMediaPostService(MediaPostService agregatedMediaPostService) {
        throw new RuntimeException("THis method does nothing, why are you calling it?");
    }
}
