package org.appman.photoblog.page.generic.facade;

import org.appman.photoblog.core.exception.ObjectModelValidationException;
import org.appman.photoblog.page.generic.dto.MediaPostViewDto;
import org.appman.photoblog.page.generic.dto.PaginationViewDto;
import org.appman.photoblog.page.generic.model.AlbumModel;
import org.appman.photoblog.page.generic.model.blog.AlbumPostModel;
import org.appman.photoblog.page.generic.service.AbstractSimpleMediaPostService;
import org.appman.photoblog.core.application.Application;
import org.appman.photoblog.core.exception.PageNotFoundException;
import org.appman.photoblog.page.medialisting.dto.MediaListingPageViewDto;
import org.appman.photoblog.page.generic.model.blog.AbstractMediaPostModel;
import org.appman.photoblog.page.generic.model.blog.BlogPostModel;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;



/**
 * Created by Pieter on 4/29/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class DefaultMediaPostFacadeTest extends DefaultMediaPostFacade {


    @Before
    public void setup(){
        setAgregatedMediaPostService(
                new AbstractSimpleMediaPostService() {
                    @Override
                    public List<AbstractMediaPostModel> getMediaPostModelList() {
                        List<AbstractMediaPostModel> mediaPostModelList = new ArrayList<>();
                        try {
                            mediaPostModelList.add(new AlbumPostModel(
                                    new AlbumModel("albumId1", "albumName1", "albumFolder1", LocalDateTime.parse("2016-05-28T10:15:30"), true))
                            );
                            mediaPostModelList.add(new AlbumPostModel(
                                    new AlbumModel("albumId2", "albumName2", "albumFolder2", LocalDateTime.parse("2016-05-29T10:15:30"), true))
                            );
                            mediaPostModelList.add(new BlogPostModel(
                                    "textBlogId1"
                                    ,LocalDateTime.parse("2016-05-30T10:15:30")
                                    ,true
                                    ,"textBlogTitle1"
                                    ,"singlePhotoId1"
                                    ,"textBlogBodyText1"
                            ));
                            mediaPostModelList.add(new BlogPostModel(
                                    "textBlogId2"
                                    ,LocalDateTime.parse("2016-05-27T10:15:30")
                                    ,true
                                    ,"textBlogTitle2"
                                    ,"singlePhotoId2"
                                    ,"textBlogBodyText2"
                            ));
                        } catch (ObjectModelValidationException e) {
                            throw new RuntimeException("Error with testdata");
                        }
                        return mediaPostModelList;
                    }

                    @Override
                    public List<AbstractMediaPostModel> getMediaPostModelList(int postPerPage, int pageNumber) {
                        return getMediaPostModelList();
                    }

                    @Override
                    public List<AbstractMediaPostModel> getMediaPostModelList(int limit) {
                        return null;
                    }
                }
        );


    }

    private List<AbstractMediaPostModel> generateBlogPostModelList(int length){
        List<AbstractMediaPostModel> blogPostModels = new ArrayList<>();
        for( int i = 1; i < length+1; i++){
            try {
                blogPostModels.add( new BlogPostModel("post" + i, LocalDateTime.parse("2016-05-28T10:15:30"), true, "title" + i, "photoid", "text" + i) );
            } catch (ObjectModelValidationException e ){
                System.out.println("error while creating test data!");
                System.out.print(e);
            }
        }
        return blogPostModels;
    }

    @Test
    @Ignore
    public void testGetBlogPostViewDtos(){
        // Action
        List<MediaPostViewDto> result = getAllMediaPostViewDtos(0,1);

//        throw new RuntimeException("This test should be updated!");

        // Expected
        assertEquals(4, result.size());
        assertEquals("textBlogTitle1", result.get(0).getTitle());
        assertEquals("albumName2", result.get(1).getTitle());
        assertEquals("albumName1", result.get(2).getTitle());
        assertEquals("textBlogTitle2", result.get(3).getTitle());
    }

    @Test
    public void testGetHomePageViewDto() throws PageNotFoundException {
        // Action
        MediaListingPageViewDto result = getHomePageViewDto(4,1);

        // Expected
        assertEquals("albumName2", result.getMediaPostViewDtos().get(1).getTitle());
    }

    @Test
    public void testCreateBlogPostViewDtoDateFormat() throws ObjectModelValidationException {
        // Setup
        AlbumPostModel input = new AlbumPostModel(new AlbumModel("albumId", "albumName", "albumFolder", LocalDateTime.parse("2016-05-28T10:15:30"), true));

        // Act
        MediaPostViewDto result = createMediaPostViewDto(input);

        // Assert
        assertEquals("May 28, 2016", result.getPublishDate());
    }


    @Test
    public void testCreatePaginationViewDtoOnePage(){
        // Action
        PaginationViewDto result = createPaginationViewDto(1, 1, "");

        // Assert
        assertEquals("First", result.getPaginationItemViewDtos().get(0).getTitle());
        assertEquals(true, result.getPaginationItemViewDtos().get(0).isActive());
        assertEquals(1, result.getPaginationItemViewDtos().size());
    }

    @Test
    public void testCreatePaginationViewDtoFirstPageActive(){
        // Action
        PaginationViewDto result = createPaginationViewDto(1, 3, "");

        // Assert
        assertEquals("First", result.getPaginationItemViewDtos().get(0).getTitle());
        assertEquals(true, result.getPaginationItemViewDtos().get(0).isActive());

        assertEquals("2", result.getPaginationItemViewDtos().get(1).getTitle());
        assertEquals(false, result.getPaginationItemViewDtos().get(1).isActive());
        assertEquals("/page/2", result.getPaginationItemViewDtos().get(1).getUrl());

        assertEquals("Last", result.getPaginationItemViewDtos().get(2).getTitle());
        assertEquals(false, result.getPaginationItemViewDtos().get(2).isActive());
        assertEquals("/page/3", result.getPaginationItemViewDtos().get(2).getUrl());
    }

    @Test
    public void testCreatePaginationViewDtoMiddlePageActive(){
        // Action
        PaginationViewDto result = createPaginationViewDto(2, 3, "");

        // Assert
        assertEquals("First", result.getPaginationItemViewDtos().get(0).getTitle());
        assertEquals(false, result.getPaginationItemViewDtos().get(0).isActive());
        assertEquals("/", result.getPaginationItemViewDtos().get(0).getUrl());

        assertEquals("2", result.getPaginationItemViewDtos().get(1).getTitle());
        assertEquals(true, result.getPaginationItemViewDtos().get(1).isActive());

        assertEquals("Last", result.getPaginationItemViewDtos().get(2).getTitle());
        assertEquals(false, result.getPaginationItemViewDtos().get(2).isActive());
        assertEquals("/page/3", result.getPaginationItemViewDtos().get(2).getUrl());
    }

    @Test
    public void testCreatePaginationViewDtoLastPageActive(){
        // Action
        PaginationViewDto result = createPaginationViewDto(3, 3, "");

        // Assert
        assertEquals("2", result.getPaginationItemViewDtos().get(1).getTitle());
        assertEquals(false, result.getPaginationItemViewDtos().get(1).isActive());
        assertEquals("/page/2", result.getPaginationItemViewDtos().get(1).getUrl());

        assertEquals("Last", result.getPaginationItemViewDtos().get(2).getTitle());
        assertEquals(true, result.getPaginationItemViewDtos().get(2).isActive());
    }

}
