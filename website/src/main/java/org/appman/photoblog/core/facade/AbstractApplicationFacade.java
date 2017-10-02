package org.appman.photoblog.core.facade;

import org.appman.photoblog.core.config.WebsiteConfig;
import org.appman.photoblog.page.common.service.PageHeadDataService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

/**
 * Created by Pieter on 11/28/2016.
 */
public class AbstractApplicationFacade {

    @Autowired
    protected WebsiteConfig websiteConfig;

    @Resource(name = "defaultPageHeadDataService")
    protected PageHeadDataService pageHeadDataService;
}
