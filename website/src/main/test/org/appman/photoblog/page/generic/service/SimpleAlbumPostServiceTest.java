package org.appman.photoblog.page.generic.service;

import org.appman.photoblog.page.generic.model.AlbumModel;
import org.appman.photoblog.page.generic.model.blog.AbstractMediaPostModel;
import org.appman.photoblog.core.application.Application;
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
 * Created by Pieter on 5/28/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class SimpleAlbumPostServiceTest extends SimpleAlbumPostService {

    @Test
    public void testGetBlogPostModelList(){
        // setup
        setAlbumService(new SimpleAlbumService(){
            @Override
            public List<AlbumModel> getAlbumModelList(){
                try {
                    List<AlbumModel> albumModels = new ArrayList<AlbumModel>();
                    albumModels.add(new AlbumModel("album1", "albumName1", "albumFolder1", LocalDateTime.parse("2016-05-28T10:15:30"), true));
                    albumModels.add(new AlbumModel("album2", "albumName2", "albumFolder2", LocalDateTime.parse("2016-05-29T10:15:30"), true));
                    albumModels.add(new AlbumModel("album3", "albumName3", "albumFolder3", LocalDateTime.parse("2016-05-27T10:15:30"), true));
                    return albumModels;
                } catch (Exception e){
                    throw new RuntimeException("Error with initializing testdata");
                }
            }
        });

        // act
        List<AbstractMediaPostModel> result = getMediaPostModelList();

        // assert
        assertEquals(3, result.size());
        assertEquals("album2", result.get(0).getId());
        assertEquals("album1", result.get(1).getId());
        assertEquals("album3", result.get(2).getId());

    }


}
