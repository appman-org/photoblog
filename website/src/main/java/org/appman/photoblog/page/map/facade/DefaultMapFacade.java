package org.appman.photoblog.page.map.facade;

import lombok.extern.slf4j.Slf4j;
import org.appman.photoblog.core.facade.AbstractApplicationFacade;
import org.appman.photoblog.page.album.facade.AlbumFacade;
import org.appman.photoblog.page.generic.dto.PhotoViewDto;
import org.appman.photoblog.page.generic.service.AlbumService;
import org.appman.photoblog.page.map.dto.MapPageViewDto;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

import static org.appman.photoblog.geo.util.LocationJsonMapperUtil.geoLocationsJsonString;

/**
 * Created by Pieter on 1/23/2017.
 */
@Component
@Slf4j
public class DefaultMapFacade extends AbstractApplicationFacade implements MapFacade {

    @Resource(name = "defaultAlbumService")
    AlbumService albumService;

    @Resource(name = "defaultAlbumFacade")
    AlbumFacade albumFacade;

    @Override
    public Optional<MapPageViewDto> getMapPageViewDto() {
        try {
            MapPageViewDto result = new MapPageViewDto();
            List<PhotoViewDto> photoViewDtos = albumFacade.createPhotoViewDtoListFromPhotoModels(albumService.getAllPhotoModelList());

            result.setGeoLocationsJsonString(geoLocationsJsonString(photoViewDtos));
            result.setPageHeadDataDto(pageHeadDataService.createBasePageHeadDataDto("Map"));
            result.setPageType("map");
            result.setGoogleMapsApiKey(websiteConfig.getGoogleMapsApiKey());

            return Optional.of(result);
        } catch (Exception e){
            log.error("Probleem while opening Map page");
            log.error(e.toString());
        }
        return Optional.empty();
    }


}
