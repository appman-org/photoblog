package org.appman.photoblog.page.map.controller;

import org.appman.photoblog.core.controller.AbstractPageController;
import org.appman.photoblog.core.exception.PageNotFoundException;
import org.appman.photoblog.page.album.dto.AlbumPageDto;
import org.appman.photoblog.page.album.facade.AlbumFacade;
import org.appman.photoblog.page.content.facade.ContentPageFacade;
import org.appman.photoblog.page.map.dto.MapPageViewDto;
import org.appman.photoblog.page.map.facade.MapFacade;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
public class MapPageController extends AbstractPageController {

    @Resource(name="defaultMapFacade")
    MapFacade mapFacade;

    @RequestMapping("/map")
    public String index(Model model) throws PageNotFoundException {
        MapPageViewDto mapPageViewDto= mapFacade.getMapPageViewDto(null)
                .orElseThrow(() -> new PageNotFoundException("Error while opening Map page"));
        return openMasterPage(model, mapPageViewDto);
    }

    @RequestMapping("/map/{year}")
    public String yearMap(
            @PathVariable int year
            ,Model model) throws PageNotFoundException {
        MapPageViewDto mapPageViewDto= mapFacade.getMapPageViewDto(year)
                .orElseThrow(() -> new PageNotFoundException("Error while opening Map page"));
        return openMasterPage(model, mapPageViewDto);
    }

}
