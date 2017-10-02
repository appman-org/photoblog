package org.appman.photoblog.page.generic.dto;

import lombok.Data;

/**
 * Created by Pieter on 8/31/2016.
 */
@Data
public class PaginationItemViewDto {
    private String title;
    private String url;
    private boolean active;

    public PaginationItemViewDto(String title, String url, boolean active){
        this.title = title;
        this.url = url;
        this.active = active;
    }

    private PaginationItemViewDto(){}

}
