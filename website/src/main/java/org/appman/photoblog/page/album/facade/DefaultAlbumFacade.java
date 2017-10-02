package org.appman.photoblog.page.album.facade;

import org.appman.photoblog.comments.service.CommentsService;
import org.appman.photoblog.core.facade.AbstractApplicationFacade;
import org.appman.photoblog.geo.builder.LocationListBuilder;
import org.appman.photoblog.page.common.dto.BreadcrumbViewDto;
import org.appman.photoblog.page.generic.dto.CommentsDto;
import org.appman.photoblog.page.generic.dto.PhotoViewDto;
import org.appman.photoblog.page.generic.model.AlbumModel;
import org.appman.photoblog.page.generic.model.PhotoModel;
import org.appman.photoblog.page.generic.service.AlbumService;
import org.appman.photoblog.page.album.dto.AlbumPageDto;
import org.appman.photoblog.page.album.dto.PhotoPageDto;

import static org.appman.photoblog.geo.util.LocationJsonMapperUtil.geoLocationsJsonString;
import static org.appman.photoblog.geo.util.LocationJsonMapperUtil.mapLocationsToJsonString;
import static org.appman.photoblog.page.common.util.CommonPageDataUtil.*;
import static org.appman.photoblog.page.generic.util.UrlUtil.*;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.Optional;
import javax.json.Json;
import javax.json.JsonObject;

/**
 * Created by Pieter on 4/13/2016.
 */
@Component
public class DefaultAlbumFacade extends AbstractApplicationFacade implements AlbumFacade{

    @Resource(name = "defaultAlbumService")
    AlbumService albumService;

    @Resource(name = "disqusCommentService")
    CommentsService commentsService;

    public void setAlbumService(AlbumService albumService) {
        this.albumService = albumService;
    }

    public void setCommentsService(CommentsService commentsService) {
        this.commentsService = commentsService;
    }

    @Override
    public Optional<PhotoPageDto> getPhotoPageDto(String photoId) {
        return albumService.getPhotoModel(photoId).flatMap(photoModel ->
            albumService.getAlbumModel(photoModel.getAlbumId()).map(albumModel -> {
                PhotoPageDto result = new PhotoPageDto();
                addAlbumData(result, albumModel);
                addPhotoData(result, photoId, albumModel.getId());
                setCommentsDto(result, albumService.getPhotoModel(photoId).get());
                result.setPageHeadDataDto(pageHeadDataService.createBasePageHeadDataDto(albumModel.getAlbumName()
                + " - photo " + photoId));
                result.setBreadcrumbViewDto(breadcrumbForPhotoPage(photoModel));
                result.setPageType("photo");
                return result;
            }));

    }

    protected void setCommentsDto(PhotoPageDto photoPageDto, PhotoModel photoModel){
        CommentsDto commentsDto = new CommentsDto();
        commentsDto.setCommentsAccountId(commentsService.getCommentsAccountId());
        commentsDto.setPageUrl(websiteConfig.getBaseUrl() + photoPageDto.getCurrentPhoto().getPhotoPageUrl());
        commentsDto.setPageIdentifier(commentsService.getPhotoPageId(photoModel));
        photoPageDto.setCommentsDto(commentsDto);

    }

    protected void setCommentsDto(AlbumPageDto albumPageDto, AlbumModel albumModel){
        CommentsDto commentsDto = new CommentsDto();
        commentsDto.setCommentsAccountId(commentsService.getCommentsAccountId());
        commentsDto.setPageUrl(websiteConfig.getBaseUrl() + albumPageUrl(albumModel));
        commentsDto.setPageIdentifier(commentsService.getAlbumPageId(albumModel));
        albumPageDto.setCommentsDto(commentsDto);

    }


    @Override
    public Optional<PhotoViewDto> getPhotoViewDto(String photoId) {
        return albumService.getPhotoModel(photoId)
                .map(this::createPhotoViewDto);
    }


    private void addPhotoData(PhotoPageDto photoPageDto, String photoId, String albumId) {
        List<PhotoModel> photoModelList = albumService.getPhotoModelListForAlbum(albumId);

        int indexOfCurrentPhoto = getIndexOfCurrentPhoto(photoId, photoModelList);

        photoPageDto.setCurrentPhoto(createPhotoViewDto(photoModelList.get(indexOfCurrentPhoto)));

        if(indexOfCurrentPhoto>0){
            photoPageDto.setShowPreviousLink(true);
            photoPageDto.setPreviousPhoto(createPhotoViewDto(photoModelList.get(indexOfCurrentPhoto - 1)));
        }

        if(indexOfCurrentPhoto<photoModelList.size()-1){
            photoPageDto.setShowNextLink(true);
            photoPageDto.setNextPhoto(createPhotoViewDto(photoModelList.get(indexOfCurrentPhoto + 1)));
        }

    }


    public PhotoViewDto createPhotoViewDto(PhotoModel photoModel){
        PhotoViewDto result = new PhotoViewDto();
        result.setPhotoId(photoModel.getId());
        result.setAltText(photoModel.getId());
        result.setPhotoPageUrl(photoPageUrl(photoModel));
        result.setImageUrl(albumService.getDefaultImageUrl(photoModel));
        result.setLatitude(photoModel.getLatitude());
        result.setLongitude(photoModel.getLongitude());
        return result;
    }

    private int getIndexOfCurrentPhoto(String photoId, List<PhotoModel> photoModelList){
        for(int i=0; i< photoModelList.size(); i++  ){
            if( photoModelList.get(i).getId().equals(photoId)){

                return i;
            }
        }

        return -1;
    }


    private void addAlbumData(PhotoPageDto photoPageDto, AlbumModel albumModel){

        photoPageDto.setAlbumUrl(albumPageUrl(albumModel));
        photoPageDto.setAlbumTitle(albumModel.getAlbumName());
    }

    private void addAlbumData(AlbumPageDto albumPageDto, AlbumModel albumModel){
        albumPageDto.setAlbumName(albumModel.getAlbumName());

    }

    @Override
    public Optional<AlbumPageDto> getAlbumPageDto(String albumId) {
        return albumService.getAlbumModel(albumId).map(albumModel -> {
            AlbumPageDto result = new AlbumPageDto();

            addAlbumData(result, albumModel);

            List<PhotoViewDto> photoViewDtos = createPhotoViewDtoListFromPhotoModels(albumService.getPhotoModelListForAlbum(albumId));

            result.setGeoLocationsJsonString(geoLocationsJsonString(photoViewDtos));
            result.setPhotoViewDtoList(photoViewDtos);
            setCommentsDto(result, albumService.getAlbumModel(albumId).get());
            result.setBreadcrumbViewDto(breadcrumbForAlbumPage(albumModel));
            result.setPageHeadDataDto(pageHeadDataService.createBasePageHeadDataDto(albumModel.getAlbumName()));
            result.setPageType("album");
            result.setGoogleMapsApiKey(websiteConfig.getGoogleMapsApiKey());
            return result;
        });
    }



    private JsonObject photoViewDtoToJson(PhotoViewDto photoViewDto){
        return Json.createObjectBuilder()
                .add("pageUrl", photoViewDto.getPhotoPageUrl())
                .add("imageUrl", photoViewDto.getImageUrl())
                .add("latitude", photoViewDto.getLatitude())
                .add("longitude", photoViewDto.getLongitude())
                .build();
    }

    @Override
    public List<PhotoViewDto> createPhotoViewDtoListFromPhotoModels(List<PhotoModel> photoModels){
        return photoModels
                .stream()
                .map(this::createPhotoViewDto)
                .collect(toList());
    }

    public List<PhotoViewDto> getPhotoViewDtoListForAlbum(String albumId){
        return createPhotoViewDtoListFromPhotoModels(albumService.getPhotoModelListForAlbum(albumId));
    }

    public BreadcrumbViewDto breadcrumbForAlbumOverviewPage(){
        BreadcrumbViewDto result = getBaseBreadCrumbViewDto();
        result.addBreadcrumbItem("Albums", albumListingUrl());
        return result;
    }

    public BreadcrumbViewDto breadcrumbForAlbumPage(AlbumModel albumModel){
        BreadcrumbViewDto result = breadcrumbForAlbumOverviewPage();
        result.addBreadcrumbItem(albumModel.getAlbumName(), albumPageUrl(albumModel));
        return result;
    }

    public BreadcrumbViewDto breadcrumbForPhotoPage(PhotoModel photoModel){
        BreadcrumbViewDto result =
                breadcrumbForAlbumPage(albumService.getAlbumModel(photoModel.getAlbumId()).get());
        result.addBreadcrumbItem(photoModel.getId(), albumService.getDefaultImageUrl(photoModel));
        return result;
    }

}
