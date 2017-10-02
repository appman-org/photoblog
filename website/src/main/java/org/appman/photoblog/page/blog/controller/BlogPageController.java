package org.appman.photoblog.page.blog.controller;

import org.appman.photoblog.page.blog.facade.BlogPageFacade;
import org.appman.photoblog.core.controller.AbstractPageController;
import org.appman.photoblog.core.exception.PageNotFoundException;
import org.appman.photoblog.page.blog.dto.BlogPostPageViewDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * Created by Pieter on 5/31/2016.
 */
@Controller
public class BlogPageController extends AbstractPageController {

    @Resource(name = "defaultBlogPageFacade")
    BlogPageFacade blogPageFacade;

    @RequestMapping("/blog/{year}/{month}/{day}/{blogId}")
    public String blog(
            @PathVariable String year
            ,@PathVariable String month
            ,@PathVariable String day
            ,@PathVariable String blogId
            , Model model) throws PageNotFoundException {

        BlogPostPageViewDto blogPostPageViewDto = blogPageFacade.getBlogPostPageViewDto(blogId)
                .orElseThrow(() -> new PageNotFoundException("Cannot find blog post with id "+ blogId +"."));
        return openMasterPage(model, blogPostPageViewDto);
    }
}
