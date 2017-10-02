package org.appman.photoblog.core.controller;

import org.appman.photoblog.core.config.WebsiteConfig;
import org.appman.photoblog.page.common.dto.MasterPageViewDto;
import org.appman.photoblog.page.common.dto.PageHeaderViewDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
abstract public class AbstractPageController {

    @Autowired
    private WebsiteConfig websiteConfig;

    protected static final String MASTER_PAGE = "masterPage";

    protected <T extends MasterPageViewDto> String openMasterPage(Model model, T pageViewDto){
        PageHeaderViewDto pageHeaderViewDto = new PageHeaderViewDto();
        pageHeaderViewDto.setWebsiteName(websiteConfig.getBaseTitle());
        pageViewDto.setPageHeaderViewDto(pageHeaderViewDto);
        model.addAttribute("pageViewDto", pageViewDto);
        return MASTER_PAGE;
    }

}
