package org.appman.photoblog.page.blog.facade;

import org.appman.photoblog.core.application.Application;
import org.appman.photoblog.core.exception.ObjectModelValidationException;
import org.appman.photoblog.page.generic.dto.CommentsDto;
import org.appman.photoblog.page.generic.model.blog.BlogPostModel;
import org.appman.photoblog.page.generic.util.UrlUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

/**
 * Created by Pieter on 11/8/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class DefaultBlogPageFacadeTest extends DefaultBlogPageFacade {

    @Test
    public void test_createCommentsDto(){
        // Setup
        BlogPostModel blogPostModel;

        try {
            blogPostModel = new BlogPostModel(
                    "textBlogId1"
                    , LocalDateTime.parse("2016-05-30T10:15:30")
                    ,true
                    ,"textBlogTitle1"
                    ,"singlePhotoId1"
                    ,"textBlogBodyText1"
            );
        } catch (ObjectModelValidationException e) {
            e.printStackTrace();
            throw new RuntimeException("Error while setting up testdata");
        }

        // Act
        CommentsDto commentsDto = createCommentsDto(blogPostModel);


        // Assert
        String expectedUrl = websiteConfig.getBaseUrl() + UrlUtil.blogPostUrl(blogPostModel);
        assertEquals(expectedUrl, commentsDto.getPageUrl());
        assertEquals("textBlog.textBlogId1", commentsDto.getPageIdentifier());

    }
}
