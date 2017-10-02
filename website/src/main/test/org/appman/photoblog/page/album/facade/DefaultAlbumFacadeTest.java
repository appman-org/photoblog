package org.appman.photoblog.page.album.facade;

import org.appman.photoblog.page.common.dto.BreadcrumbViewDto;
import org.appman.photoblog.page.generic.model.AlbumModel;
import org.appman.photoblog.page.generic.model.PhotoModel;
import org.appman.photoblog.page.generic.service.mock.AlbumServiceMock;
import org.appman.photoblog.core.application.Application;
import org.appman.photoblog.core.exception.ObjectModelValidationException;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;


/**
 * Created by Pieter on 4/24/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class DefaultAlbumFacadeTest extends DefaultAlbumFacade{


    @Test
    public void testBreadcrumbForAlbumOverviewPage(){
        // breadcrumbForAlbumOverviewPage

        BreadcrumbViewDto result = breadcrumbForAlbumOverviewPage();
        assertEquals(result.getBreadcrumbItemViewDtos().get(0).getTitle(), "Home");
        assertEquals(result.getBreadcrumbItemViewDtos().get(1).getUrl(), "/albums");

    }

    @Test
    public void testBreadcrumbForAlbumPage() throws ObjectModelValidationException {
        // Setup
        AlbumModel input = new AlbumModel("mockId", "mockName", "bla", LocalDateTime.parse("2007-12-03T10:15:30"), true);

        // Act
        BreadcrumbViewDto result = breadcrumbForAlbumPage(input);

        // Assert
        assertEquals("Albums", result.getBreadcrumbItemViewDtos().get(1).getTitle());
        assertEquals("mockName",result.getBreadcrumbItemViewDtos().get(2).getTitle());
        assertEquals("/album/mockId" ,result.getBreadcrumbItemViewDtos().get(2).getUrl());
    }

    @Test
    public void testBreadcrumbForPhotoPage() throws ObjectModelValidationException {
        // Setup
        setAlbumService(new AlbumServiceMock());
        PhotoModel input = new PhotoModel("photoId", "blaUrl", "XXXX", "blaDescription", null, null);
        // AlbumId XXXX from AlbumServiceMock

        // Act
        BreadcrumbViewDto result = breadcrumbForPhotoPage(input);

        // Assert
        assertEquals("Mocked Album Name!", result.getBreadcrumbItemViewDtos().get(2).getTitle());
        assertEquals("photoId", result.getBreadcrumbItemViewDtos().get(3).getTitle());
    }

}
