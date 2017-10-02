package org.appman.photoblog.ft.features.medialisting;

import cucumber.api.CucumberOptions;
import org.appman.ftbase.core.AbstractCucumberTest;
import org.appman.ftbase.core.CucumberWrapper;
import org.junit.runner.RunWith;

@RunWith(CucumberWrapper.class)
@CucumberOptions( glue ={ "org.appman.photoblog.ft.generic", "org.appman.photoblog.ft.features.medialisting"  })
public class MediaListingTest extends AbstractCucumberTest {

}
