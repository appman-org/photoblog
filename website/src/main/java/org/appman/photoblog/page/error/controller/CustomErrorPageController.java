package org.appman.photoblog.page.error.controller;

import org.appman.photoblog.page.error.dto.ErrorPageViewDto;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by Pieter on 4/29/2016.
 */
@Controller
public class CustomErrorPageController implements ErrorController {

    private static final String PATH = "error";

    @RequestMapping(value = PATH)
    public String error(HttpServletResponse response, Model model) {
        ErrorPageViewDto errorPageViewDto = new ErrorPageViewDto();
        errorPageViewDto.setStatusCode(Integer.toString(response.getStatus()));
        model.addAttribute("errorPageViewDto", errorPageViewDto);
        return "errorPage";
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }
}
