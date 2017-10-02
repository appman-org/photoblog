package org.appman.photoblog.persistence.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.Getter;
import lombok.Setter;
import org.appman.photoblog.persistence.converter.LocalDateTimeFormat;

import java.time.LocalDateTime;

/**
 * Created by Pieter on 2/28/2017.
 */
@Getter
@Setter
@DynamoDBTable(tableName = "Album")
public class Album {

    @DynamoDBHashKey
    private String id;

    @DynamoDBAttribute
    private String albumName;

    @DynamoDBAttribute
    private String folder;

    @DynamoDBAttribute
    private boolean visible;

    private LocalDateTime publishDate;

    @LocalDateTimeFormat
    public LocalDateTime getPublishDate() {
        return publishDate;
    }

    @LocalDateTimeFormat
    public void setPublishDate(LocalDateTime publishDate) {
        this.publishDate = publishDate;
    }
}
