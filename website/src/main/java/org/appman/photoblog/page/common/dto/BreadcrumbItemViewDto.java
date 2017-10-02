package org.appman.photoblog.page.common.dto;

import lombok.Data;

/**
 * Created by Pieter on 4/24/2016.
 */
@Data
public class BreadcrumbItemViewDto {

    private String title;
    private String url;

    public BreadcrumbItemViewDto(String title, String url){
        setTitle(title);
        setUrl(url);
    }

    private BreadcrumbItemViewDto(){}

}
