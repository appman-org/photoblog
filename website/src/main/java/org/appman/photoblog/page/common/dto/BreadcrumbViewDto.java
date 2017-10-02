package org.appman.photoblog.page.common.dto;

import lombok.AccessLevel;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pieter on 4/24/2016.
 */
public class BreadcrumbViewDto {

    @Getter(AccessLevel.PUBLIC)
    private List<BreadcrumbItemViewDto> breadcrumbItemViewDtos;

    public BreadcrumbViewDto(){
        breadcrumbItemViewDtos = new ArrayList<BreadcrumbItemViewDto>();
    }

    public void addBreadcrumbItem(String title, String url){
        breadcrumbItemViewDtos.add(new BreadcrumbItemViewDto(title, url));
    }
}
