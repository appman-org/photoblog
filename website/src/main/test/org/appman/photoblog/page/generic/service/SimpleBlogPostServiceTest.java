package org.appman.photoblog.page.generic.service;

import org.appman.photoblog.page.generic.model.blog.AbstractMediaPostModel;
import org.appman.photoblog.page.generic.model.blog.BlogPostModel;
import org.appman.photoblog.core.application.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

/**
 * Created by Pieter on 5/28/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class SimpleBlogPostServiceTest extends SimpleBlogPostService {

    @Test
    public void testParseStringToBlogPostModel(){
        // Setup
        String input =
                "2016-05-28T10:15:30\n" +
                "true\n" +
                "blogPostId1\n" +
                "Blog post title 1\n" +
                "singlePhotoId\n" +
                "Text content of blog post 1\n" +
                "Content 2nd rule\n";

        // Act
        Optional<BlogPostModel> result = parseStringToBlogPostModel(input);

        // Assert
        assertEquals(true, result.isPresent());
        BlogPostModel blogPostModel = result.get();
        assertEquals(LocalDateTime.parse("2016-05-28T10:15:30"), blogPostModel.getPublishDate());
        assertEquals(true, blogPostModel.isVisible());
        assertEquals("blogPostId1", blogPostModel.getId());
        assertEquals("Blog post title 1", blogPostModel.getTitle());
        assertEquals("singlePhotoId", blogPostModel.getSinglePhotoId());
        assertEquals("Text content of blog post 1\n" +
                "Content 2nd rule\n", blogPostModel.getRawBodyText());

    }

    @Test
    public void testGetBlogPostsFromInputString(){
        // Setup
        String input =
                "2016-05-28T10:15:30\n" +
                "true\n" +
                "blogPostId1\n" +
                "Blog post titel 1\n" +
                "singlePhotoId1\n" +
                "Text inhoud van blog post 1\n" +
                "#break\n" +

                "2016-05-29T10:15:30\n" +
                "true\n" +
                "blogPostId2\n" +
                "Blog post titel 2\n" +
                "singlePhotoId2\n" +
                "Text inhoud van blog post 2a\n";

        // Act
        List<AbstractMediaPostModel> blogPostModels = getBlogPostsFromInputString(input);

        // Assert
        assertEquals(2, blogPostModels.size());
        assertEquals(true, blogPostModels.get(0) instanceof BlogPostModel);
        assertEquals("blogPostId2", blogPostModels.get(0).getId());

    }


    @Test
    public void testReadCompleteTextBlogFile(){
        String result = readCompleteBlogFile();
        System.out.print(result);
    }
}
