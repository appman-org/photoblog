package org.appman.photoblog.page.content.dto;

import lombok.EqualsAndHashCode;
import org.appman.photoblog.page.common.dto.MasterPageViewDto;
import lombok.Data;

/**
 * Created by Pieter on 10/13/2016.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ContentPageViewDto extends MasterPageViewDto {

    private String title;
    private String content;

}
