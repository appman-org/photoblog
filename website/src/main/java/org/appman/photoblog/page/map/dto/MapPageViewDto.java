package org.appman.photoblog.page.map.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.appman.photoblog.page.common.dto.MasterPageViewDto;

/**
 * Created by Pieter on 1/23/2017.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class MapPageViewDto extends MasterPageViewDto {

    private String googleMapsApiKey;
    private String geoLocationsJsonString;

}
