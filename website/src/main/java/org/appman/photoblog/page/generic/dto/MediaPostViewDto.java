package org.appman.photoblog.page.generic.dto;

import lombok.Data;

import java.util.List;

/**
 * Created by Pieter on 4/24/2016.
 */
@Data
public class MediaPostViewDto {

    private String title;
    private String url;
    private String publishDate;
    private String bodyText;
    private String shortBodyText;
    private boolean hasShowMoreButton;
    private PhotoViewDto singlePhoto;


    private List<PhotoViewDto> albumPhotos;


}
