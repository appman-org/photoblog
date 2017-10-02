package org.appman.photoblog.page.content.facade;

import org.appman.photoblog.core.exception.PageNotFoundException;
import org.appman.photoblog.page.content.dto.ContentPageViewDto;

/**
 * Created by Pieter on 11/29/2016.
 */
public interface ContentPageFacade {

    public ContentPageViewDto getAboutPageViewDto() throws PageNotFoundException;
}
