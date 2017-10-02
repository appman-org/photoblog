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
@DynamoDBTable(tableName = "Photo")
public class Photo {

    @DynamoDBHashKey
    private String id;

    @DynamoDBAttribute
    private String albumId;

    @DynamoDBAttribute
    private String description;

    @DynamoDBAttribute
    private String latitude;

    @DynamoDBAttribute
    private String longitude;

    @DynamoDBAttribute
    private String url;

}
