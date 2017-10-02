package org.appman.photoblog.page.blog.facade;

import lombok.extern.slf4j.Slf4j;
import org.appman.photoblog.comments.service.CommentsService;
import org.appman.photoblog.core.facade.AbstractApplicationFacade;
import org.appman.photoblog.page.album.facade.AlbumFacade;
import org.appman.photoblog.page.blog.dto.BlogPostPageViewDto;
import org.appman.photoblog.page.generic.dto.CommentsDto;
import org.appman.photoblog.page.generic.exception.TextParsingException;
import org.appman.photoblog.page.generic.model.blog.BlogPostModel;
import org.appman.photoblog.page.generic.service.MediaPostService;
import org.appman.photoblog.page.generic.service.TextParserService;
import static org.appman.photoblog.page.generic.util.UrlUtil.*;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Optional;

import static org.appman.photoblog.core.util.ModelSearchUtil.searchById;

/**
 * Created by Pieter on 11/6/2016.
 */
@Component
@Slf4j
public class DefaultBlogPageFacade extends AbstractApplicationFacade implements BlogPageFacade {

    @Resource(name = "simpleBlogPostService")
    private MediaPostService blogPostService;

    @Resource(name = "defaultTextParserService")
    private TextParserService textParserService;

    @Resource(name = "defaultAlbumFacade")
    private AlbumFacade albumFacade;

    @Resource(name = "disqusCommentService")
    private CommentsService commentsService;

    public void setBlogPostService(MediaPostService blogPostService) {
        this.blogPostService = blogPostService;
    }

    public void setTextParserService(TextParserService textParserService) {
        this.textParserService = textParserService;
    }

    public void setAlbumFacade(AlbumFacade albumFacade) {
        this.albumFacade = albumFacade;
    }

    public void setCommentsService(CommentsService commentsService) {
        this.commentsService = commentsService;
    }

    @Override
    public Optional<BlogPostPageViewDto> getBlogPostPageViewDto(String blogId) {
        return searchById(blogPostService.getMediaPostModelList(), blogId)
                .map(mediaPostModel -> {
                   return createBlogPostPageViewDto((BlogPostModel)mediaPostModel);
                });
    }

    protected BlogPostPageViewDto createBlogPostPageViewDto(BlogPostModel blogPostModel){
        BlogPostPageViewDto result = new BlogPostPageViewDto();

        albumFacade.getPhotoViewDto(blogPostModel.getSinglePhotoId())
                .ifPresent(photoViewDto -> result.setSinglePhoto(photoViewDto));

        if ((result.getSinglePhoto() == null) && (blogPostModel.getSinglePhotoId() != null)) {
            log.error("PhotoViewDto not found for photoId " + blogPostModel.getSinglePhotoId() +
            " for blogpost " + blogPostModel.getId());
        }

        try {
            result.setBodyText(textParserService.parseStringToHtml(blogPostModel.getRawBodyText()).get());
        } catch (TextParsingException e) {
            e.printStackTrace();
        }
        result.setTitle(blogPostModel.getTitle());
        result.setPageHeadDataDto(pageHeadDataService.createBasePageHeadDataDto(blogPostModel.getTitle()));
        result.setPageType("textblog");
        result.setCommentsDto(createCommentsDto(blogPostModel));
        return result;
    }


    protected CommentsDto createCommentsDto(BlogPostModel blogPostModel){
        CommentsDto commentsDto = new CommentsDto();
        commentsDto.setCommentsAccountId(commentsService.getCommentsAccountId());
        commentsDto.setPageUrl(websiteConfig.getBaseUrl() + blogPostUrl(blogPostModel));
        commentsDto.setPageIdentifier(commentsService.getBlogPageId(blogPostModel));
        return commentsDto;
    }

}
