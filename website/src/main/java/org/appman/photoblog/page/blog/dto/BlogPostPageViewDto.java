package org.appman.photoblog.page.blog.dto;

import lombok.EqualsAndHashCode;
import org.appman.photoblog.page.generic.dto.CommentsDto;
import org.appman.photoblog.page.common.dto.MasterPageViewDto;
import org.appman.photoblog.page.generic.dto.PhotoViewDto;
import lombok.Data;

/**
 * Created by Pieter on 11/4/2016.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class BlogPostPageViewDto extends MasterPageViewDto {

    private String title;
    private String bodyText;

    private PhotoViewDto singlePhoto;

    private CommentsDto commentsDto;

}
