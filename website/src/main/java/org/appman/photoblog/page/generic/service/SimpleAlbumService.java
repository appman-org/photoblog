package org.appman.photoblog.page.generic.service;

import org.appman.photoblog.core.service.AbstractApplicationService;
import org.appman.photoblog.page.generic.model.AlbumModel;
import org.appman.photoblog.page.generic.model.PhotoModel;
import org.appman.photoblog.core.service.FileReaderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

import static java.lang.Integer.min;
import static java.util.stream.Collectors.*;
import static org.appman.photoblog.core.util.ModelSearchUtil.searchById;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Pieter on 4/15/2016.
 */
@Component
@Slf4j
public class SimpleAlbumService extends AbstractApplicationService implements AlbumService {

    private static final String CSV_SEPARATOR = ";";

    @Resource(name = "defaultFileReaderService")
    private FileReaderService fileReaderService;

    public void setFileReaderService(FileReaderService fileReaderService) {
        this.fileReaderService = fileReaderService;
    }

    @Override
    public Optional<AlbumModel> getAlbumModel(String albumId) {

        return searchById(getAlbumModelList(), albumId);

    }

    private List<String> getPhotoCsvLines(){
        List<String> csvLines = null;
        try {
            if(websiteConfig.isRunLocal()){
                csvLines = fileReaderService.read(getPhotoDataFileLocation());
            } else {
                csvLines = fileReaderService.read(getPhotoDataFileLocationUrl());
            }
        } catch (IOException e) {
            log.info(e.toString());
        }
        return csvLines;
    }

    @Override
    public Optional<PhotoModel> getPhotoModel(String photoId) {

        return searchById(parseCsvLinesToPhotoModelList(getPhotoCsvLines()), photoId);
        
    }

    @Override
    public List<PhotoModel> getAlbumThumbnailPhotos(String albumId, int count) {
        List<PhotoModel> source = getPhotoModelListForAlbum(albumId);
        int minimum = min(count, source.size());
        return getPhotoModelListForAlbum(albumId).subList(0,minimum);
    }

    @Override
    public List<PhotoModel> getPhotoModelListForAlbum(String albumId) {

        return findPhotosInAlbum(getAllPhotoModelList(), albumId);
    }

    @Override
    public List<PhotoModel> getAllPhotoModelList() {
        return parseCsvLinesToPhotoModelList(getPhotoCsvLines());
    }

    protected URL getPhotoDataFileLocationUrl(){
        try {
            return new URL(websiteConfig.getContent().getRemote().getPhotoList());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected String getPhotoDataFileLocation(){

        return websiteConfig.getContent().getLocal().getPhotoList();

    }

    protected URL getAlbumDataFileLocationUrl(){


        try {
            return new URL(websiteConfig.getContent().getRemote().getAlbumList());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected String getAlbumDataFileLocation(){
        return websiteConfig.getContent().getLocal().getAlbumList();

    }


    @Override
    public String getDefaultImageUrl(PhotoModel photoModel) {
        return websiteConfig.getContent().getRemote().getImageUrlPrefix() + photoModel.getUrl();
    }

    protected List<PhotoModel> findPhotosInAlbum(List<PhotoModel> input, final String albumId){
        return input.stream()
                .filter(photo -> photo.getAlbumId().equals(albumId))
                .collect(toList());
    }

    protected Optional<PhotoModel> parseCsvLineToPhotoModel(String line){

        // Example input: photoIdText;fileNameText;albumIdText
        String[] lineEntries = line.split(CSV_SEPARATOR);
        try {
            String id = lineEntries[0];
            String url = lineEntries[1];
            String albumId = lineEntries[2];
            String latitude;
            String longitude;
            if(lineEntries[3].equals("none") || lineEntries[4].equals("none")){
                latitude = null;
                longitude = null;
            } else {
                latitude = lineEntries[3];
                longitude = lineEntries[4];
            }


            return Optional.of(new PhotoModel(id, url, albumId, null, latitude, longitude));
        } catch (Exception e){
            log.error("Error while parsing PhotoModel from source: " + line);
            log.error(e.toString());
            return Optional.empty();
        }

    }

    protected List<PhotoModel> parseCsvLinesToPhotoModelList(List<String> csvLines){

        return csvLines.stream().map(csvLine -> parseCsvLineToPhotoModel(csvLine))
                .filter(Optional::isPresent).map(Optional::get)
                .collect(toList());

    }

    protected Optional<AlbumModel> parseCsvLineToAlbumModel(String line){

        // Example input: albumIdText1;folderText1;AlbumName1
        String[] lineEntries = line.split(CSV_SEPARATOR);
        try {
            String id = lineEntries[0];
            LocalDateTime publishDate = LocalDateTime.parse(lineEntries[1]);
            boolean published = Boolean.parseBoolean(lineEntries[2]);
            String folder= lineEntries[3];
            String albumName = lineEntries[4];


            return Optional.of(new AlbumModel(id, albumName, folder, publishDate, published));
        } catch (Exception e) {
            log.error("Error while parsing AlbumModel from source: " + line);
            log.error(e.toString());
            return Optional.empty();
        }
    }

    protected List<AlbumModel> parseCsvLinesToAlbumModelList(List<String> csvLines){
        return csvLines.stream().map(csvLine -> parseCsvLineToAlbumModel(csvLine))
                .filter(Optional::isPresent).map(Optional::get)
                .collect(toList());


    }

    @Override
    public List<AlbumModel> getAlbumModelList() {
        List<String> csvLines = null;
        try {
            if(websiteConfig.isRunLocal()){
                csvLines = fileReaderService.read(getAlbumDataFileLocation());
            } else {
                csvLines = fileReaderService.read(getAlbumDataFileLocationUrl());
            }
        } catch (IOException e) {
            log.info(e.toString());
        }

        return parseCsvLinesToAlbumModelList(csvLines);
    }

    @Override
    public List<AlbumModel> getAlbumModelList(Pageable pageable) {
        throw new UnsupportedOperationException("Not supported");
    }

    @Override
    public List<PhotoModel> getPhotoModelListForAlbumIds(List<String> albumIds) {
        return null;
    }
}
