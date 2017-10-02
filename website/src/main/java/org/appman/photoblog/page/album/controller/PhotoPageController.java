package org.appman.photoblog.page.album.controller;

import org.appman.photoblog.core.controller.AbstractPageController;
import org.appman.photoblog.core.exception.PageNotFoundException;
import org.appman.photoblog.page.album.dto.PhotoPageDto;
import org.appman.photoblog.page.album.facade.AlbumFacade;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
public class PhotoPageController extends AbstractPageController {

    @Resource(name="defaultAlbumFacade")
    AlbumFacade albumFacade;

    @RequestMapping("/photo/{photoId}")
    public String index(@PathVariable String photoId, Model model) throws PageNotFoundException {

        PhotoPageDto photoPageDto = albumFacade.getPhotoPageDto(photoId)
                .orElseThrow(() -> new PageNotFoundException("Cannot find photo with id "+ photoId +"."));
        return openMasterPage(model, photoPageDto);
    }

}
