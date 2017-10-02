package org.appman.photoblog.page.blog.facade;

import org.appman.photoblog.page.blog.dto.BlogPostPageViewDto;

import java.util.Optional;

/**
 * Created by Pieter on 11/6/2016.
 */
public interface BlogPageFacade {

    public Optional<BlogPostPageViewDto> getBlogPostPageViewDto(String blogId);

}
