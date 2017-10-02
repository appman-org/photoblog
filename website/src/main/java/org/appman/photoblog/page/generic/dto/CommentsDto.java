package org.appman.photoblog.page.generic.dto;

import lombok.Data;

/**
 * Created by Pieter on 6/26/2016.
 */
@Data
public class CommentsDto {
    private String pageIdentifier;
    private String pageUrl;
    private String commentsAccountId;

}
