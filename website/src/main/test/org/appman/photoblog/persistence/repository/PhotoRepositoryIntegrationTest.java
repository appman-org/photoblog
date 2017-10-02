package org.appman.photoblog.persistence.repository;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.model.*;
import lombok.extern.slf4j.Slf4j;
import org.appman.photoblog.AbstractPhotoBlogIntegrationTest;
import org.appman.photoblog.core.EnvironmentType;
import org.appman.photoblog.core.application.Application;
import org.appman.photoblog.core.config.WebsiteConfig;
import org.appman.photoblog.persistence.AbstractPersistenceDependentIntegrationTest;
import org.appman.photoblog.persistence.model.Photo;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static java.time.LocalTime.now;
import static org.junit.Assert.assertEquals;

/**
 * Created by Pieter on 3/6/2017.
 */
@Slf4j
public class PhotoRepositoryIntegrationTest extends AbstractPersistenceDependentIntegrationTest {

    @Test
    public void testAddPhotoData(){
        // Given
        Photo photo1 = new Photo();
        photo1.setId("photoId1");
        photo1.setAlbumId("albumId1");
        photo1.setUrl("photoUrl1");
        photoRepository.save(photo1);

        Photo photo2 = new Photo();
        photo2.setId("photoId2");
        photo2.setAlbumId("albumId2");
        photo2.setUrl("photoUrl2");
        photoRepository.save(photo2);

        Photo photo3 = new Photo();
        photo3.setId("photoId3");
        photo3.setAlbumId("albumId1");
        photo3.setUrl("photoUrl3");
        photoRepository.save(photo3);

        // When
        List<Photo> result = photoRepository.findByAlbumId("albumId1");

        // Then
        assertEquals(2, result.size());
        assert result.stream()
                .map(photo -> photo.getId())
                .collect(Collectors.toList())
                .containsAll(Arrays.asList("photoId1", "photoId3"));
        assertEquals(3, photoRepository.count());


    }


}
