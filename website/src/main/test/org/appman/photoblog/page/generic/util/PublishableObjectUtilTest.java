package org.appman.photoblog.page.generic.util;

import org.appman.photoblog.page.generic.model.blog.AbstractMediaPostModel;
import org.appman.photoblog.page.generic.model.blog.BlogPostModel;
import org.appman.photoblog.core.application.Application;
import org.appman.photoblog.core.exception.ObjectModelValidationException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.appman.photoblog.page.generic.util.PublishableObjectUtil.getPagedPublishableModels;
import static org.junit.Assert.assertEquals;

/**
 * Created by Pieter on 8/30/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class PublishableObjectUtilTest {

    private List<AbstractMediaPostModel> generateMediaPostModelList(int length){
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
    public void testGetPagedPublishableModelsEmptyList(){
        // Setup
        List<AbstractMediaPostModel> blogPostModels = generateMediaPostModelList(0);

        // Action
        List<AbstractMediaPostModel> result = getPagedPublishableModels(blogPostModels, 1, 1);

        // Assert
        assertEquals(0, result.size());
    }

    @Test
    public void testGetPagedPublishableModelsLessThenBlogPerPage(){
        // Setup
        List<AbstractMediaPostModel> blogPostModels = generateMediaPostModelList(50);

        // Action
        List<AbstractMediaPostModel> result1 = getPagedPublishableModels(blogPostModels, 100, 1);
        List<AbstractMediaPostModel> result2 = getPagedPublishableModels(blogPostModels, 100, 2);

        // Assert
        assertEquals(50, result1.size());
        assertEquals(0, result2.size());

    }

    @Test
    public void testGetPagedPublishableModelsEqualToBlogPerPage(){
        // Setup
        List<AbstractMediaPostModel> blogPostModels = generateMediaPostModelList(3);

        // Action
        List<AbstractMediaPostModel> result1 = getPagedPublishableModels(blogPostModels, 3, 1);
        List<AbstractMediaPostModel> result2 = getPagedPublishableModels(blogPostModels, 3, 2);


        // Assert
        assertEquals(3, result1.size());
        assertEquals(0, result2.size());

    }

    @Test
    public void testGetPagedPublishableModelsMoreToBlogPerPage(){
        // Setup
        List<AbstractMediaPostModel> blogPostModels = generateMediaPostModelList(8);

        // Action
        List<AbstractMediaPostModel> result1 = getPagedPublishableModels(blogPostModels, 3, 1);
        List<AbstractMediaPostModel> result2 = getPagedPublishableModels(blogPostModels, 3, 3);

        // Assert
        assertEquals(3, result1.size());
        assertEquals("post1", result1.get(0).getId());
        assertEquals(2, result2.size());
        assertEquals("post7", result2.get(0).getId());

    }


}
