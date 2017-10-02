package org.appman.photoblog.page.album.dto;

import lombok.EqualsAndHashCode;
import org.appman.photoblog.page.common.dto.MasterPageViewDto;
import org.appman.photoblog.page.common.dto.BreadcrumbViewDto;
import org.appman.photoblog.page.generic.dto.CommentsDto;
import org.appman.photoblog.page.generic.dto.PhotoViewDto;
import lombok.Data;

import java.util.List;

/**
 * Created by Pieter on 4/14/2016.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AlbumPageDto extends MasterPageViewDto {
    private String albumName;
    private String url;

    private List<PhotoViewDto> photoViewDtoList;
    private BreadcrumbViewDto breadcrumbViewDto;

    private CommentsDto commentsDto;

    private String googleMapsApiKey;

    private String geoLocationsJsonString;

}
