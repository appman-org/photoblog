package org.appman.photoblog.page.content.facade;

import org.appman.photoblog.core.exception.PageNotFoundException;
import org.appman.photoblog.core.facade.AbstractApplicationFacade;
import org.appman.photoblog.core.service.FileReaderService;
import org.appman.photoblog.page.content.dto.ContentPageViewDto;
import org.appman.photoblog.page.content.model.ContentModel;
import org.appman.photoblog.page.generic.exception.TextParsingException;
import org.appman.photoblog.page.generic.service.TextParserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Pieter on 11/29/2016.
 */
@Component
@Slf4j
public class DefaultContentPageFacade extends AbstractApplicationFacade implements ContentPageFacade {

    @Resource(name = "defaultTextParserService")
    TextParserService textParserService;

    @Resource(name = "defaultFileReaderService")
    private FileReaderService fileReaderService;

    @Override
    public ContentPageViewDto getAboutPageViewDto() throws PageNotFoundException {

        try {
            String rawBodyText;
            if(websiteConfig.isRunLocal()) {
                rawBodyText = fileReaderService.readAsSingleString(getContentFileLocation());
            } else {
                rawBodyText = fileReaderService.readAsSingleString(getAlbumDataFileLocationUrl());
            }
            ContentModel contentModel = new ContentModel("about", "About", rawBodyText);
            return createContentPageViewDto(contentModel);
        } catch (Exception e) {
            log.error("error when creating ContentModel", e.toString());
            throw new PageNotFoundException("error when creating ContentModel", e);
        }

    }

    protected String getContentFileLocation(){
        return websiteConfig.getContent().getLocal().getAboutPage();
    }

    protected URL getAlbumDataFileLocationUrl() throws MalformedURLException {
        return new URL(websiteConfig.getContent().getRemote().getAboutPage());
    }

    protected ContentPageViewDto createContentPageViewDto(ContentModel contentModel) throws TextParsingException {
        ContentPageViewDto result = new ContentPageViewDto();

        result.setTitle(contentModel.getTitle());
        result.setPageHeadDataDto(pageHeadDataService.createBasePageHeadDataDto(contentModel.getTitle()));

        try {
            result.setContent(textParserService.parseStringToHtml(contentModel.getRawBodyText()).get());
        } catch (TextParsingException e) {
            log.error("Error while parsing rawBodyText for ContentModel with id " + contentModel.getId(), e.toString());
        }

        result.setPageType("content");
        return result;
    }
}
