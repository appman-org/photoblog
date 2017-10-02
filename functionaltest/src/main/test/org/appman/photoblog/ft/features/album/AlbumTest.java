package org.appman.photoblog.ft.features.album;

import cucumber.api.CucumberOptions;
import org.appman.ftbase.core.AbstractCucumberTest;
import org.appman.ftbase.core.CucumberWrapper;
import org.junit.runner.RunWith;

/**
 * Created by Pieter on 10/26/2016.
 */
@RunWith(CucumberWrapper.class)
@CucumberOptions( glue ={ "org.appman.photoblog.ft.generic", "org.appman.photoblog.ft.features.album"  })
public class AlbumTest extends AbstractCucumberTest {
}
