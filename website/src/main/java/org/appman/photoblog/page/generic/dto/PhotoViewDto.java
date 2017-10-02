package org.appman.photoblog.page.generic.dto;

import lombok.Data;

/**
 * Created by Pieter on 4/13/2016.
 */
@Data
public class PhotoViewDto {
    private String photoId;
    private String photoPageUrl;
    private String imageUrl;
    private String description;
    private boolean hasDescription;

    private String latitude;
    private String longitude;

    private String altText;

}
