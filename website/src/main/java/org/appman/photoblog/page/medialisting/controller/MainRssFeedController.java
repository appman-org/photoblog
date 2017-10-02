package org.appman.photoblog.page.medialisting.controller;

import org.appman.photoblog.core.controller.AbstractPageController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Pieter on 6/23/2016.
 */
@Controller
public class MainRssFeedController extends AbstractPageController {


    @RequestMapping(value = "/feed", produces = "application/*")
    public String getFeed(Model model) {


        return "mainRssFeedView";
    }

}
