package org.appman.photoblog.page.map.facade;

import lombok.extern.slf4j.Slf4j;
import org.appman.photoblog.core.facade.AbstractApplicationFacade;
import org.appman.photoblog.page.album.facade.AlbumFacade;
import org.appman.photoblog.page.common.dto.BreadcrumbViewDto;
import org.appman.photoblog.page.generic.dto.PaginationViewDto;
import org.appman.photoblog.page.generic.dto.PhotoViewDto;
import org.appman.photoblog.page.generic.model.AlbumModel;
import org.appman.photoblog.page.generic.service.AlbumService;
import org.appman.photoblog.page.map.dto.MapPageViewDto;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;
import static org.appman.photoblog.geo.util.LocationJsonMapperUtil.geoLocationsJsonString;
import static org.appman.photoblog.page.common.util.CommonPageDataUtil.getBaseBreadCrumbViewDto;
import static org.appman.photoblog.page.generic.util.PublishableObjectUtil.getPublishedModels;
import static org.appman.photoblog.page.generic.util.PublishableObjectUtil.getPublishedModelsInYear;
import static org.appman.photoblog.page.generic.util.PublishableObjectUtil.getYearsWithPublishedModels;
import static org.appman.photoblog.page.generic.util.UrlUtil.mapForYearPageUrl;
import static org.appman.photoblog.page.generic.util.UrlUtil.mapPageUrl;

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
    public Optional<MapPageViewDto> getMapPageViewDto(Integer year){
        try {
            MapPageViewDto result = new MapPageViewDto();
            List<AlbumModel> albumsOnMap;
            List<AlbumModel> allAlbumModels = albumService.getAlbumModelList();
            List<AlbumModel> allPublishedAlbumModels = getPublishedModels(allAlbumModels);
            result.setPaginationViewDto(yearsPaginationViewDto(allPublishedAlbumModels));
            if (year == null){
                result.setBreadcrumbViewDto(breadcrumbMapPageBase());
                albumsOnMap = allPublishedAlbumModels;
            } else {
                result.setBreadcrumbViewDto(breadCrumbMapForYearPage(year));
                albumsOnMap = getPublishedModelsInYear(allAlbumModels, year);
            }
            List<PhotoViewDto> photoViewDtos = albumFacade.createPhotoViewDtoListFromPhotoModels(
                    albumService.getPhotoModelListForAlbumIds(
                            albumsOnMap.stream().map(AlbumModel::getId).collect(toList())
                    )
            );

            result.setGeoLocationsJsonString(geoLocationsJsonString(photoViewDtos));
            result.setPageHeadDataDto(pageHeadDataService.createBasePageHeadDataDto("Map"));
            result.setPageType("map");
            result.setGoogleMapsApiKey(websiteConfig.getGoogleMapsApiKey());

            return Optional.of(result);
        } catch (Exception e){
            log.error("Problem while opening Map page");
            log.error(e.toString());
        }
        return Optional.empty();
    }

    private BreadcrumbViewDto breadcrumbMapPageBase(){
        BreadcrumbViewDto result = getBaseBreadCrumbViewDto();
        result.addBreadcrumbItem("Map", mapPageUrl());
        return result;
    }

    private BreadcrumbViewDto breadCrumbMapForYearPage(int year){
        BreadcrumbViewDto result = breadcrumbMapPageBase();
        result.addBreadcrumbItem(Integer.toString(year), mapForYearPageUrl(year));
        return result;
    }

    private PaginationViewDto yearsPaginationViewDto(List<AlbumModel> albumModels){

        PaginationViewDto result = new PaginationViewDto();

        for (int year: getYearsWithPublishedModels(albumModels)){
            result.addPaginationItemViewDto(Integer.toString(year), mapForYearPageUrl(year), false);
        }
        return result;
    }

}
