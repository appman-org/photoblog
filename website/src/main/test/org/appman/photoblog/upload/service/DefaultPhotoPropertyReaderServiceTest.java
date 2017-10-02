package org.appman.photoblog.upload.service;

import lombok.extern.slf4j.Slf4j;
import org.appman.photoblog.AbstractPhotoBlogIntegrationTest;
import org.appman.photoblog.core.application.Application;
import org.appman.photoblog.core.config.WebsiteConfig;
import org.appman.photoblog.page.generic.model.PhotoModel;
import org.appman.photoblog.upload.service.DefaultPhotoPropertyReaderService;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Pieter on 6/12/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@Slf4j
public class DefaultPhotoPropertyReaderServiceTest extends AbstractPhotoBlogIntegrationTest {

    @Autowired
    WebsiteConfig websiteConfig;

    @Resource(name = "defaultPhotoPropertyReaderService")
    PhotoPropertyReaderService photoPropertyReaderService;

    @Test
    @Ignore
    public void testGetPhotoProperties(){
        assert false;
        System.out.print(photoPropertyReaderService.getPhotoProperties(websiteConfig.getImageImportFolder()));
    }

    @Test
    public void testReadPhotosForAlbum(){
        List<PhotoModel> photoModels =
                photoPropertyReaderService.readPhotoProperties(websiteConfig.getImageImportFolder(), "testalbum0001");

        assertEquals("testalbum0001", photoModels.get(0).getAlbumId());
    }






}
