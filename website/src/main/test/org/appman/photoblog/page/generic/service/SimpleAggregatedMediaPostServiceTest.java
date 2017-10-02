package org.appman.photoblog.page.generic.service;

import org.appman.photoblog.page.generic.model.AlbumModel;
import org.appman.photoblog.page.generic.model.blog.AbstractMediaPostModel;
import org.appman.photoblog.page.generic.model.blog.AlbumPostModel;
import org.appman.photoblog.page.generic.model.blog.BlogPostModel;
import org.appman.photoblog.core.application.Application;
import org.appman.photoblog.core.exception.ObjectModelValidationException;
import org.junit.Before;
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
 * Created by Pieter on 8/30/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class SimpleAggregatedMediaPostServiceTest extends SimpleAgregatedMediaPostService {

    @Before
    public void setup(){

        setAlbumMediaPostService(new SimpleAlbumPostService(){
            @Override
            public List<AbstractMediaPostModel> getMediaPostModelList() {
                List<AbstractMediaPostModel> mediaPostModels = new ArrayList<>();
                try {
                    mediaPostModels.add(new AlbumPostModel(
                            new AlbumModel("albumId1", "albumName1", "albumFolder1", LocalDateTime.parse("2016-05-28T10:15:30"), true))
                    );
                    mediaPostModels.add(new AlbumPostModel(
                            new AlbumModel("albumId2", "albumName2", "albumFolder2", LocalDateTime.parse("2016-05-29T10:15:30"), true))
                    );
                } catch (ObjectModelValidationException e) {
                    throw new RuntimeException("Error with testdata");
                }
                return mediaPostModels;

            }
        });
        setBlogPostService(new SimpleBlogPostService(){
            @Override
            public List<AbstractMediaPostModel> getMediaPostModelList() {
                List<AbstractMediaPostModel> mediaPostModels = new ArrayList<>();
                try {
                    mediaPostModels.add(new BlogPostModel(
                            "textBlogId1"
                            ,LocalDateTime.parse("2016-05-30T10:15:30")
                            ,true
                            ,"textBlogTitle1"
                            ,"singlePhotoId1"
                            ,"textBlogBodyText1"
                    ));
                    mediaPostModels.add(new BlogPostModel(
                            "textBlogId2"
                            ,LocalDateTime.parse("2016-05-27T10:15:30")
                            ,true
                            ,"textBlogTitle2"
                            ,"singlePhotoId2"
                            ,"textBlogBodyText2"
                    ));
                } catch (ObjectModelValidationException e){
                    throw new RuntimeException("Error with testdata");
                }
                return mediaPostModels;
            }
        });

    }

    @Test
    public void testGetBlogPostModelList(){
        List<AbstractMediaPostModel> result = getMediaPostModelList();

        // Expected
        assertEquals(4, result.size());
        assertEquals("textBlogTitle1", result.get(0).getTitle());
        assertEquals("albumName2", result.get(1).getTitle());
        assertEquals("albumName1", result.get(2).getTitle());
        assertEquals("textBlogTitle2", result.get(3).getTitle());
    }

}
