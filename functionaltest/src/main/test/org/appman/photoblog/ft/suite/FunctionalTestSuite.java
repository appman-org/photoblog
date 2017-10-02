package org.appman.photoblog.ft.suite;

import org.appman.photoblog.ft.features.medialisting.MediaListingTest;
import org.appman.photoblog.ft.features.album.AlbumTest;
import org.appman.photoblog.ft.features.content.ContentTest;
import org.appman.photoblog.ft.features.blog.BlogPostTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by Pieter on 10/25/2016.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        MediaListingTest.class
        ,AlbumTest.class
        ,BlogPostTest.class
        ,ContentTest.class
})
public class FunctionalTestSuite {
}
