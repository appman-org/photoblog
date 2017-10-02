package org.appman.photoblog;

import lombok.extern.slf4j.Slf4j;
import org.appman.photoblog.core.EnvironmentType;
import org.appman.photoblog.core.application.Application;
import org.appman.photoblog.core.config.WebsiteConfig;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Pieter on 3/6/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@IntegrationTest
@ActiveProfiles("dev")
@Slf4j
abstract public class AbstractPhotoBlogIntegrationTest {
//    Serves to select the 'dev' profile and confirm it with checkEnvironmentType, so no production data is affected

    @Autowired
    private WebsiteConfig websiteConfig;

    @Before // Is run before all @Before methods in subclass
    public final void checkEnvironmentType(){
        assert websiteConfig.getEnvironmentType().equals(EnvironmentType.DEV);
    }
}
