package org.appman.photoblog.page.generic.service;

import org.appman.photoblog.page.generic.model.AlbumModel;
import org.appman.photoblog.page.generic.model.PhotoModel;
import org.appman.photoblog.core.application.Application;
import org.appman.photoblog.core.exception.ObjectModelValidationException;
import org.appman.photoblog.core.model.AbstractObjectModel;
import org.appman.photoblog.core.service.FileReaderService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/**
 * Created by Pieter on 4/14/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class SimpleAlbumServiceTest extends SimpleAlbumService{

    FileReaderService mockFileReaderService;
    FileReaderService mockAlbumFileReaderService;

    @Before
    public void setup(){
        // Preconditions
        // anonymous inner class
        mockFileReaderService = new FileReaderService() {
            @Override
            public List<String> read(String location) throws IOException {
                List<String> textLines = new ArrayList<String>();
                textLines.add("photoIdText1;fileNameText1;albumIdText1;none;none");
                textLines.add("photoIdText2;fileNameText2;albumIdText2;none;none");
                return textLines;
            }

            @Override
            public List<String> read(URL url) throws IOException {
                return read("");
            }

            @Override
            public List<String> getFilenames(String location) {
                return null;
            }

            @Override
            public String readAsSingleString(String location) throws IOException {
                return null;
            }

            @Override
            public String readAsSingleString(URL url) throws IOException {
                return null;
            }
        };

        mockAlbumFileReaderService = new FileReaderService() {
            @Override
            public List<String> read(String location) throws IOException {
                List<String> textLines = new ArrayList<String>();
                textLines.add("albumIdText1;2007-12-03T10:15:30;true;images;Mock Album 1");
                textLines.add("albumIdText2;2007-12-03T10:15:30;true;images;Mock Album 2");
                return textLines;
            }

            @Override
            public List<String> read(URL url) throws IOException {
                return read("");
            }

            @Override
            public List<String> getFilenames(String location) {
                return null;
            }

            @Override
            public String readAsSingleString(String location) throws IOException {
                return null;
            }

            @Override
            public String readAsSingleString(URL url) throws IOException {
                return null;
            }
        };



    }

    @Test
    public void testParseCsvLineToPhotoModelNoGeoData(){
        String inputLine = "photoIdText;fileNameText;albumIdText;none;none";

        PhotoModel result = parseCsvLineToPhotoModel(inputLine).get();

        assertEquals("photoIdText", result.getId());
        assertEquals("fileNameText", result.getUrl());
        assertEquals("albumIdText", result.getAlbumId());
        assertEquals(null, result.getLatitude());
    }

    @Test
    public void testParseCsvLineToPhotoModelWithGeoData(){
        String inputLine = "photoIdText;fileNameText;albumIdText;1.23;-4.5";

        PhotoModel result = parseCsvLineToPhotoModel(inputLine).get();

        assertEquals("1.23", result.getLatitude());
        assertEquals("-4.5", result.getLongitude());
    }

    @Test
    public void testParseCsvLinesToPhotoModelList(){

        List<String> input = new ArrayList<String>();
        input.add("photoIdText1;fileNameText1;albumIdText1;none;none");
        input.add("photoIdText2;fileNameText2;albumIdText2;none;none");

        List<PhotoModel> result = parseCsvLinesToPhotoModelList(input);

        assert( result.get(0).getId().equals("photoIdText1"));
        assert( result.get(1).getAlbumId().equals("albumIdText2"));

    }

    @Test
    public void testParseCsvLinesToPhotoModelListMissingItem(){

        List<String> input = new ArrayList<String>();
        input.add("photoIdText1;fileNameText1;albumIdText1;none;none");
        input.add("bladiebla");
        input.add("photoIdText2;fileNameText2;albumIdText2;none;none");

        List<PhotoModel> result = parseCsvLinesToPhotoModelList(input);

        assert( result.get(0).getId().equals("photoIdText1"));
        assert( result.get(1).getAlbumId().equals("albumIdText2"));

    }

    @Test
    public void testFindPhotosInAlbum() throws ObjectModelValidationException {
        List<PhotoModel> input = new ArrayList<PhotoModel>();
        PhotoModel modelInAlbumA = new PhotoModel("blaPhotoId", "blaUrl", "AlbumA", null, null, null);
        input.add(modelInAlbumA);

        PhotoModel modelInAlbumB = new PhotoModel("blaPhotoId", "blaUrl", "AlbumB", null, null, null);
        input.add(modelInAlbumB);

        List<PhotoModel> result = findPhotosInAlbum(input, "AlbumA");
        assert(result.size() == 1);
        assert(result.get(0).equals(modelInAlbumA));

    }


    @Test
    public void testGetPhotoModelListForAlbum(){


        setFileReaderService(mockFileReaderService);

        // Actions
        List<PhotoModel> result = getPhotoModelListForAlbum("albumIdText1");

        // Assert
        assert(result.size() == 1);
        assert(result.get(0).getId().equals("photoIdText1"));

    }

    @Test
    public void testGetAlbumModelById(){
        setFileReaderService(mockAlbumFileReaderService);
        Optional<AlbumModel> result = getAlbumModel("albumIdText2");
        assertEquals(Optional.of("albumIdText2"), result.map(AbstractObjectModel::getId));
    }



    @Test
    public void testGetPhotoModelByPhotoId(){
        // Preconditions
        setFileReaderService(mockFileReaderService);

        Optional<PhotoModel> result = getPhotoModel("photoIdText1");

        assertEquals(Optional.of("fileNameText1"), result.map(PhotoModel::getUrl) );

    }


    @Test
    @Ignore
    public void testGetDefaultImageUrl(){
        assert(false);
    }

    @Test
    @Ignore
    public void testGetPhotoDataFileLocation(){
        assert(false);
    }

    @Test
    public void testParseCsvLineToAlbumModel(){
        String inputLine = "albumIdText1;2007-12-03T10:15:30;true;folderText1;AlbumName1";

        AlbumModel result = parseCsvLineToAlbumModel(inputLine).get();

        assertEquals("albumIdText1", result.getId());
        assertEquals("folderText1", result.getFolder());
        assertEquals("AlbumName1", result.getAlbumName());
        assertEquals("2007-12-03T10:15:30", result.getPublishDate().toString());
        assertEquals(true, result.isVisible());

    }


    @Test
    public void testParseCsvLinesToAlbumModelList(){
        List<String> input = new ArrayList<String>();
        input.add("albumIdText1;2007-12-03T10:15:30;true;folderText1;AlbumName1");
        input.add("albumIdText2;2008-12-03T10:15:30;false;folderText2;AlbumName2");

        List<AlbumModel> result = parseCsvLinesToAlbumModelList(input);

        assertEquals("AlbumName1", result.get(0).getAlbumName());
        assertEquals("albumIdText2", result.get(1).getId());

    }

}
