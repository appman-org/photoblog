package org.appman.photoblog.page.map.facade;

import org.appman.photoblog.page.map.dto.MapPageViewDto;

import java.util.Optional;

/**
 * Created by Pieter on 1/23/2017.
 */
public interface MapFacade {

    Optional<MapPageViewDto> getMapPageViewDto();
}
