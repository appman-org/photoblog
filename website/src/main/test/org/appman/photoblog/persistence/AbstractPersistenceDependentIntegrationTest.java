package org.appman.photoblog.persistence;

import lombok.extern.slf4j.Slf4j;
import org.appman.photoblog.AbstractPhotoBlogIntegrationTest;
import org.appman.photoblog.persistence.repository.AlbumRepository;
import org.appman.photoblog.persistence.repository.PhotoRepository;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Pieter on 3/7/2017.
 */
@Slf4j
public abstract class AbstractPersistenceDependentIntegrationTest extends AbstractPhotoBlogIntegrationTest{

//    Clears the testdata in all repository's, so all tests in subclass can assume empty tables.

    @Autowired
    protected PhotoRepository photoRepository;

    @Autowired
    protected AlbumRepository albumRepository;

    @Before
    public void clearTables() throws Exception {
        log.info("Deleting all elements in table Photo");
        photoRepository.deleteAll();
        log.info("Deleting all elements in table Album");
        albumRepository.deleteAll();
        log.info("Delete done");
    }
}
