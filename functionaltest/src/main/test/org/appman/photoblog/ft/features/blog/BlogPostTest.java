package org.appman.photoblog.ft.features.blog;

import cucumber.api.CucumberOptions;
import org.appman.ftbase.core.CucumberWrapper;
import org.junit.runner.RunWith;

/**
 * Created by Pieter on 10/27/2016.
 */
@RunWith(CucumberWrapper.class)
@CucumberOptions( glue ={ "org.appman.photoblog.ft.generic", "org.appman.photoblog.ft.features.blog"  })
public class BlogPostTest {
}
