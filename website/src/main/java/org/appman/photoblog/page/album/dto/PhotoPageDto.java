package org.appman.photoblog.page.album.dto;

import lombok.EqualsAndHashCode;
import org.appman.photoblog.page.common.dto.MasterPageViewDto;
import org.appman.photoblog.page.generic.dto.CommentsDto;
import org.appman.photoblog.page.generic.dto.PhotoViewDto;
import lombok.Data;

/**
 * Created by Pieter on 4/13/2016.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PhotoPageDto extends MasterPageViewDto {

    private String albumTitle;
    private String albumUrl;

    private boolean showPreviousLink;
    private boolean showNextLink;

    private PhotoViewDto currentPhoto;
    private PhotoViewDto previousPhoto;
    private PhotoViewDto nextPhoto;

    private CommentsDto commentsDto;

    public PhotoPageDto(){
        showPreviousLink = false;
        showNextLink = false;
    }

}
