package org.appman.photoblog.core.service;

import org.appman.photoblog.core.config.WebsiteConfig;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Pieter on 11/28/2016.
 */
public abstract class AbstractApplicationService {

    @Autowired
    protected WebsiteConfig websiteConfig;
}
