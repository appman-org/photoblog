package org.appman.photoblog.page.medialisting.dto;

import lombok.EqualsAndHashCode;
import org.appman.photoblog.page.common.dto.MasterPageViewDto;
import org.appman.photoblog.page.generic.dto.MediaPostViewDto;
import org.appman.photoblog.page.generic.dto.PaginationViewDto;
import lombok.Data;

import java.util.List;

/**
 * Created by Pieter on 4/24/2016.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class MediaListingPageViewDto extends MasterPageViewDto {


    private List<MediaPostViewDto> mediaPostViewDtos;

    private PaginationViewDto paginationViewDto;

    private boolean hasShowMoreLink;

    private String pageId;

}
