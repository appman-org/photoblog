package org.appman.photoblog.page.album.facade.mock;

import org.appman.photoblog.page.generic.dto.PhotoViewDto;
import org.appman.photoblog.page.album.dto.AlbumPageDto;
import org.appman.photoblog.page.album.dto.PhotoPageDto;
import org.appman.photoblog.page.album.facade.AlbumFacade;
import org.appman.photoblog.page.generic.model.PhotoModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Pieter on 4/13/2016.
 */
public class AlbumFacadeMock implements AlbumFacade {

    private PhotoViewDto photoViewDto;
    private AlbumPageDto albumPageDto;
    private List<PhotoViewDto> photoViewDtoList;

    public AlbumFacadeMock(){
        photoViewDto = new PhotoViewDto();
        photoViewDto.setImageUrl("temp/picture.JPG");
        photoViewDto.setDescription("Mock Description");
        photoViewDto.setPhotoId("XXXXX");
        photoViewDto.setHasDescription(true);
        photoViewDto.setAltText("Alt image text");
        photoViewDto.setPhotoPageUrl("/photo");

        photoViewDtoList = new ArrayList<PhotoViewDto>();
        photoViewDtoList.add(photoViewDto);
        photoViewDtoList.add(photoViewDto);
        photoViewDtoList.add(photoViewDto);

        albumPageDto = new AlbumPageDto();
        albumPageDto.setPhotoViewDtoList(photoViewDtoList);
        albumPageDto.setUrl("album");
        albumPageDto.setAlbumName("Mocked Album");
    }


    @Override
    public Optional<PhotoPageDto> getPhotoPageDto(String photoId) {
        PhotoPageDto result  = new PhotoPageDto();
        result.setCurrentPhoto(photoViewDto);

        result.setNextPhoto(photoViewDto);
        result.setShowNextLink(true);

        result.setPreviousPhoto(photoViewDto);
        result.setShowPreviousLink(true);

        result.setAlbumTitle("Mock album title");
        result.setAlbumUrl("album");

        return Optional.of(result);
    }

    @Override
    public Optional<PhotoViewDto> getPhotoViewDto(String photoId) {
        return Optional.of(photoViewDto);
    }


    @Override
    public Optional<AlbumPageDto> getAlbumPageDto(String albumId) {

        return Optional.of(albumPageDto);
    }

    @Override
    public List<PhotoViewDto> createPhotoViewDtoListFromPhotoModels(List<PhotoModel> photoModels) {
        return null;
    }


    @Override
    public List<PhotoViewDto> getPhotoViewDtoListForAlbum(String albumId) {

        return photoViewDtoList;
    }


}
