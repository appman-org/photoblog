package org.appman.photoblog.page.content.controller;

import org.appman.photoblog.core.controller.AbstractPageController;
import org.appman.photoblog.core.exception.PageNotFoundException;
import org.appman.photoblog.page.content.facade.ContentPageFacade;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
public class ContentPageController extends AbstractPageController {

    @Resource(name = "defaultContentPageFacade")
    protected ContentPageFacade contentPageFacade;

    @RequestMapping("/about")
    public String index(Model model) throws PageNotFoundException {
        return openMasterPage(model, contentPageFacade.getAboutPageViewDto());
    }

}
