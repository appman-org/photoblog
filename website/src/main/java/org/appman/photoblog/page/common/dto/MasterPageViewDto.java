package org.appman.photoblog.page.common.dto;

import lombok.*;

/**
 * Created by Pieter on 10/10/2016.
 */
@Data
public class MasterPageViewDto {

    private BreadcrumbViewDto breadcrumbViewDto;

    private PageHeadDataDto pageHeadDataDto;

    private String pageType;

    private PageHeaderViewDto pageHeaderViewDto;

}
