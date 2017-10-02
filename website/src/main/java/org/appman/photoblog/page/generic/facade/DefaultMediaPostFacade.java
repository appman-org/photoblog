package org.appman.photoblog.page.generic.facade;

import com.rometools.rome.feed.rss.Content;
import com.rometools.rome.feed.rss.Description;
import com.rometools.rome.feed.rss.Item;
import lombok.extern.slf4j.Slf4j;
import org.appman.photoblog.core.facade.AbstractApplicationFacade;
import org.appman.photoblog.page.generic.dto.MediaPostViewDto;
import org.appman.photoblog.page.generic.exception.TextParsingException;
import org.appman.photoblog.page.generic.model.blog.AbstractMediaPostModel;
import org.appman.photoblog.page.generic.model.blog.AlbumPostModel;
import org.appman.photoblog.page.generic.model.blog.BlogPostModel;
import org.appman.photoblog.core.exception.PageNotFoundException;
import org.appman.photoblog.page.album.facade.AlbumFacade;
import org.appman.photoblog.page.generic.dto.PaginationViewDto;
import org.appman.photoblog.page.medialisting.dto.MediaListingPageViewDto;
import org.appman.photoblog.page.generic.service.MediaPostService;
import org.appman.photoblog.page.generic.service.TextParserService;
import static org.appman.photoblog.page.generic.util.UrlUtil.*;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.appman.photoblog.core.util.ModelSearchUtil.searchById;

/**
 * Created by Pieter on 4/27/2016.
 */
@Component
@Slf4j
public class DefaultMediaPostFacade extends AbstractApplicationFacade implements MediaPostFacade {

    @Resource(name = "defaultAlbumFacade")
    private AlbumFacade albumFacade;

    @Resource(name = "defaultTextParserService")
    private TextParserService textParserService;

    @Resource(name = "simpleAgregatedMediaPostService")
    private MediaPostService agregatedMediaPostService;

    @Resource(name = "simpleAlbumPostService")
    private MediaPostService albumPostService;

    @Resource(name = "simpleBlogPostService")
    private MediaPostService blogPostService;

    private final static String analyticsUrlSuffix = "?utm_source=feed&utm_medium=RSS&utm_campaign=RSS%20Reader";

    public void setAgregatedMediaPostService(MediaPostService agregatedMediaPostService) {
        this.agregatedMediaPostService = agregatedMediaPostService;
    }

    public void setAlbumPostService(MediaPostService albumPostService) {
        this.albumPostService = albumPostService;
    }

    public void setBlogPostService(MediaPostService blogPostService) {
        this.blogPostService = blogPostService;
    }

    public void setAlbumFacade(AlbumFacade albumFacade) {
        this.albumFacade = albumFacade;
    }

    public void setTextParserService(TextParserService textParserService) {
        this.textParserService = textParserService;
    }

    @Override
    public List<MediaPostViewDto> getAllMediaPostViewDtos(int postPerPage, int pageNumber) {
        return getMediaPostViewDtos(postPerPage, pageNumber, agregatedMediaPostService);
    }

    protected List<MediaPostViewDto> getMediaPostViewDtos(int postPerPage, int pageNumber, MediaPostService mediaPostService) {
        return mediaPostService.getMediaPostModelList(postPerPage, pageNumber)
                .stream()
                .map(this::createMediaPostViewDto)
                .collect(Collectors.toList());
    }


    protected MediaPostViewDto createMediaPostViewDto(AbstractMediaPostModel mediaPostModel){
        MediaPostViewDto result = new MediaPostViewDto();
        result.setTitle(mediaPostModel.getTitle());
        result.setPublishDate(mediaPostModel.getPublishDate().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)));
        if (mediaPostModel instanceof AlbumPostModel) {
            return addAlbumData((AlbumPostModel) mediaPostModel, result);
        } else if (mediaPostModel instanceof BlogPostModel){
            return addTextData((BlogPostModel)mediaPostModel, result);
        }

        throw new IllegalArgumentException("Unrecognized subtype of AbstractMediaPostModel!");
    }

    protected String albumBlogPostUrl(AlbumPostModel albumBlogPostModel){
        return albumPageUrl(albumBlogPostModel.getAlbumModel());
    }

    protected MediaPostViewDto addAlbumData(AlbumPostModel albumPostModel, MediaPostViewDto result){
        result.setUrl(albumPageUrl(albumPostModel.getAlbumModel()));
        result.setAlbumPhotos(albumFacade.getPhotoViewDtoListForAlbum(albumPostModel.getAlbumModel().getId()));
        return result;
    }

    protected MediaPostViewDto addTextData(BlogPostModel blogPostModel, MediaPostViewDto result){
        try {
            result.setBodyText(textParserService.parseStringToHtml(blogPostModel.getRawBodyText()).get());
            result.setShortBodyText(textParserService.parseStringToHtml(blogPostModel.getRawBodyText(), 900).get());
            result.setHasShowMoreButton(!result.getBodyText().equals(result.getShortBodyText()));

        } catch (TextParsingException e){
            throw new RuntimeException("Unable to create BlogPostViewDto", e);
        }
        result.setUrl(blogPostUrl(blogPostModel));
        albumFacade.getPhotoViewDto(blogPostModel.getSinglePhotoId())
                .ifPresent(photoViewDto -> result.setSinglePhoto(photoViewDto));

        if ((result.getSinglePhoto() == null)&& (blogPostModel.getSinglePhotoId()!=null)){
            log.error("PhotoViewDto not found for photoId " + blogPostModel.getSinglePhotoId() +
                    " for blogpost " + blogPostModel.getId());
        }

        return result;
    }


    protected MediaListingPageViewDto getMediaListingPageViewDto(int postPerPage
            , int pageNumber
            , MediaPostService mediaPostService
            , String pageTitle
            , String urlPrefix
            , String pageAttribute) throws PageNotFoundException {

        int numberOfPages = mediaPostService.getNumberOfPages(postPerPage);
        if(pageNumber > numberOfPages || pageNumber < 1){
            throw new PageNotFoundException("Page with number " + pageNumber + "not found");
        }

        MediaListingPageViewDto result = new MediaListingPageViewDto();
        result.setMediaPostViewDtos(getMediaPostViewDtos(postPerPage, pageNumber, mediaPostService));
        if(postPerPage > 0){
            result.setHasShowMoreLink(true);
        } else {
            result.setHasShowMoreLink(false);
        }
        result.setPaginationViewDto(createPaginationViewDto(pageNumber, numberOfPages, urlPrefix));

        result.setPageHeadDataDto(pageHeadDataService.createBasePageHeadDataDto(pageTitle));
        result.setPageType("medialisting");
        result.setPageId(createPageId(pageNumber, pageAttribute));

        return result;
    }

    @Override
    public MediaListingPageViewDto getHomePageViewDto(int postPerPage, int pageNumber) throws PageNotFoundException {
        return getMediaListingPageViewDto(postPerPage, pageNumber, agregatedMediaPostService, "Home", "", "home");
    }

    @Override
    public MediaListingPageViewDto getAlbumListingPageViewDto(int postPerPage, int pageNumber) throws PageNotFoundException {
        return getMediaListingPageViewDto(postPerPage, pageNumber, albumPostService, "Albums", albumListingUrl(), "album-listing");
    }

    @Override
    public MediaListingPageViewDto getBlogListingPageViewDto(int postPerPage, int pageNumber) throws PageNotFoundException {
        return getMediaListingPageViewDto(postPerPage, pageNumber, blogPostService, "Blogs", blogListingUrl(), "blog-listing");
    }

    protected String createPageId(int pageNumber, String pageAttribute){
        return "data-st-page-" + pageAttribute + "-" + pageNumber;
    }

    @Override
    public Optional<MediaPostViewDto> getMediaPostViewDto(String blogId) {
        return searchById(agregatedMediaPostService.getMediaPostModelList(), blogId)
                .map( this::createMediaPostViewDto);
    }


    @Override
    public List<Item> getRssFeedItems(int limit) {
        return agregatedMediaPostService.getMediaPostModelList(limit)
                .stream()
                .map( this::createItem )
                .collect(Collectors.toList());
    }

    protected Item createItem(AbstractMediaPostModel mediaPostModel){
        Item item = new Item();

        item.setTitle(mediaPostModel.getTitle());
        item.setPubDate(Date.from(ZonedDateTime.of(mediaPostModel.getPublishDate(), ZoneId.systemDefault()).toInstant()));

        if(mediaPostModel instanceof AlbumPostModel){
            item.setLink(websiteConfig.getBaseUrl() + albumBlogPostUrl(((AlbumPostModel)mediaPostModel)) + analyticsUrlSuffix);
        } else if (mediaPostModel instanceof BlogPostModel) {
            item.setLink(websiteConfig.getBaseUrl() + blogPostUrl((BlogPostModel)mediaPostModel) + analyticsUrlSuffix);
            try {
                item.setDescription(createDescription(textParserService.parseStringToHtml(
                        ((BlogPostModel)mediaPostModel).getRawBodyText(), 400).get()));
            } catch (TextParsingException e){
                throw new RuntimeException("Unable to create Item", e);
            }
        }
        return item;

    }

    protected Description createDescription(String input) {
        Description description = new Description();
        description.setType(Content.HTML);
        description.setValue(input);
        return description;
    }

    protected PaginationViewDto createPaginationViewDto(int pageNumber, int pages, String urlPrefix){


        PaginationViewDto result = new PaginationViewDto();

        // The pagination always has a first entry
        result.addPaginationItemViewDto("First", urlPrefix + "/", false);

        //  For all pages from number 2 until the one previous to the last, numbered items are added
        for(int i = 2; i< pages; i++){
            result.addPaginationItemViewDto(Integer.toString(i), urlPrefix + "/page/" + (i), false);
        }

        // If there is more then one page, a last page item will be added at the end.
        if(pages > 1){
            result.addPaginationItemViewDto("Last", urlPrefix + "/page/" + pages, false);
        }

        result.getPaginationItemViewDtos().get(pageNumber - 1).setActive(true);

        return result;
    }

}
