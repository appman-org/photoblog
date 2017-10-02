package org.appman.photoblog.page.medialisting.controller;

import org.appman.photoblog.page.generic.facade.MediaPostFacade;
import org.appman.photoblog.core.controller.AbstractPageController;
import org.appman.photoblog.core.exception.PageNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
public class MediaListingPageController extends AbstractPageController {

    private static final int POSTS_PER_PAGE = 6;

    @Resource(name = "defaultMediaPostFacade")
    private MediaPostFacade mediaPostFacade;

    @RequestMapping("/")
    public String index(Model model) throws PageNotFoundException {
        return openMasterPage(model, mediaPostFacade.getHomePageViewDto(POSTS_PER_PAGE,1));
    }

    @RequestMapping("/page/{page}")
    public String indexPaged(@PathVariable int page,
                             Model model) throws PageNotFoundException {
        return openMasterPage(model, mediaPostFacade.getHomePageViewDto(POSTS_PER_PAGE,page));
    }

    @RequestMapping("/blogs")
    public String blogs(Model model) throws PageNotFoundException {
        return openMasterPage(model, mediaPostFacade.getBlogListingPageViewDto(POSTS_PER_PAGE,1));
    }

    @RequestMapping("/blogs/page/{page}")
    public String blogsPaged(@PathVariable int page, Model model) throws PageNotFoundException {
        return openMasterPage(model, mediaPostFacade.getBlogListingPageViewDto(POSTS_PER_PAGE,page));
    }

    @RequestMapping("/albums")
    public String albums(Model model) throws PageNotFoundException {
        return openMasterPage(model, mediaPostFacade.getAlbumListingPageViewDto(POSTS_PER_PAGE,1));
    }

    @RequestMapping("/albums/page/{page}")
    public String albumsPaged(@PathVariable int page, Model model) throws PageNotFoundException {
        return openMasterPage(model, mediaPostFacade.getAlbumListingPageViewDto(POSTS_PER_PAGE,page));
    }
}
