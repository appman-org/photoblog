package org.appman.photoblog.page.album.controller;

import org.appman.photoblog.core.controller.AbstractPageController;
import org.appman.photoblog.core.exception.PageNotFoundException;
import org.appman.photoblog.page.album.dto.AlbumPageDto;
import org.appman.photoblog.page.album.facade.AlbumFacade;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
public class AlbumPageController extends AbstractPageController {

    @Resource(name="defaultAlbumFacade")
    AlbumFacade albumFacade;

    @RequestMapping("/album/{albumId}")
    public String index(@PathVariable String albumId, Model model) throws PageNotFoundException {
        AlbumPageDto albumPageDto = albumFacade.getAlbumPageDto(albumId)
                .orElseThrow(() -> new PageNotFoundException("Cannot find album with id: "+ albumId +"."));
        return openMasterPage(model, albumPageDto);
    }

}
