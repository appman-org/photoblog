package org.appman.photoblog.ft.features.content;

import cucumber.api.CucumberOptions;
import org.appman.ftbase.core.AbstractCucumberTest;
import org.appman.ftbase.core.CucumberWrapper;
import org.junit.runner.RunWith;

/**
 * Created by Pieter on 10/27/2016.
 */
@RunWith(CucumberWrapper.class)
@CucumberOptions( glue ={ "org.appman.photoblog.ft.generic", "org.appman.photoblog.ft.features.content"  })
public class ContentTest extends AbstractCucumberTest {
}
