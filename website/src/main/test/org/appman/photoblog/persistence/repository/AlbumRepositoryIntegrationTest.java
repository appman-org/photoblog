package org.appman.photoblog.persistence.repository;

import lombok.extern.slf4j.Slf4j;
import org.appman.photoblog.AbstractPhotoBlogIntegrationTest;
import org.appman.photoblog.core.EnvironmentType;
import org.appman.photoblog.core.application.Application;
import org.appman.photoblog.core.config.WebsiteConfig;
import org.appman.photoblog.persistence.AbstractPersistenceDependentIntegrationTest;
import org.appman.photoblog.persistence.model.Album;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.StreamSupport;

import static org.junit.Assert.assertEquals;

/**
 * Created by Pieter on 3/6/2017.
 */
@Slf4j
public class AlbumRepositoryIntegrationTest extends AbstractPersistenceDependentIntegrationTest {

    @Test
    public void addAlbumData(){
        // Given
        Album album1 = new Album();
        album1.setId("albumId1");
        album1.setPublishDate(LocalDateTime.parse("2007-12-03T10:15:30"));
        albumRepository.save(album1);

        Album album2 = new Album();
        album2.setId("albumId2");
        album2.setPublishDate(LocalDateTime.parse("2017-12-03T10:15:30"));
        albumRepository.save(album2);

        // When
        Album result1 = albumRepository.findOne("albumId1");
        Album result2 = albumRepository.findOne("albumId2");

        // Then
        assertEquals(2,albumRepository.count());
        log.info("Publish date? " + result2.getPublishDate().toString());
        assertEquals(LocalDateTime.parse("2017-12-03T10:15:30"), result2.getPublishDate());
    }
}
