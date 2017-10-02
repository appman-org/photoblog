package org.appman.photoblog.page.generic.dto;

import lombok.AccessLevel;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pieter on 8/31/2016.
 */
public class PaginationViewDto {

    @Getter(AccessLevel.PUBLIC)
    private List<PaginationItemViewDto> paginationItemViewDtos;

    public PaginationViewDto() {
        this.paginationItemViewDtos = new ArrayList<>();
    }

    public void addPaginationItemViewDto(String title, String url, boolean active){
        paginationItemViewDtos.add(new PaginationItemViewDto(title, url, active));
    }
}
