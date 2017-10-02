package org.appman.photoblog.page.common.service;

import org.appman.photoblog.core.service.AbstractApplicationService;
import org.appman.photoblog.page.common.dto.PageHeadDataDto;
import org.springframework.stereotype.Component;

/**
 * Created by Pieter on 11/28/2016.
 */
@Component
public class DefaultPageHeadDataService extends AbstractApplicationService implements PageHeadDataService {

    private String getPageName(String page){
        return websiteConfig.getBaseTitle() + " - " + page;
    }

    @Override
    public PageHeadDataDto createBasePageHeadDataDto(String page){
        PageHeadDataDto result = new PageHeadDataDto();
        result.setPageTitle(getPageName(page));
        result.setAnalyticsTrackingId(websiteConfig.getAnalyticsTrackingId());
        return result;
    }
}
