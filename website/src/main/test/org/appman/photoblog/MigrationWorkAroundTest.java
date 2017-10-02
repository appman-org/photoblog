package org.appman.photoblog;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.model.*;
import lombok.extern.slf4j.Slf4j;
import org.appman.photoblog.core.application.Application;
import org.appman.photoblog.core.exception.ObjectModelValidationException;
import org.appman.photoblog.page.generic.model.AlbumModel;
import org.appman.photoblog.page.generic.model.PhotoModel;
import org.appman.photoblog.page.generic.service.AlbumService;
import org.appman.photoblog.persistence.model.Album;
import org.appman.photoblog.persistence.model.Photo;
import org.appman.photoblog.persistence.model.User;
import org.appman.photoblog.persistence.repository.AlbumRepository;
import org.appman.photoblog.persistence.repository.PhotoRepository;
import org.appman.photoblog.persistence.repository.UserRepository;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pieter on 2/26/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = {
//        PropertyPlaceholderAutoConfiguration.class, DynamoDBConfig.class})
@SpringApplicationConfiguration(classes = Application.class)
//@WebAppConfiguration
@IntegrationTest
@ActiveProfiles("local")
@Slf4j
@Ignore
public class MigrationWorkAroundTest {

    private static final String KEY_NAME = "id";
    private static final Long READ_CAPACITY_UNITS = 5L;
    private static final Long WRITE_CAPACITY_UNITS = 5L;

    @Autowired
    UserRepository repository;

    @Autowired
    PhotoRepository photoRepository;

    @Autowired
    AlbumRepository albumRepository;

    @Resource(name = "simpleAlbumService")
    AlbumService albumService;

    @Autowired
    private AmazonDynamoDB amazonDynamoDB;

//    @Before
    public void init() throws Exception {

        ListTablesResult listTablesResult = amazonDynamoDB.listTables();

        listTablesResult.getTableNames().stream().
                filter(tableName -> tableName.equals("User")).forEach(tableName -> {
            amazonDynamoDB.deleteTable(tableName);
        });

        List<AttributeDefinition> attributeDefinitions = new ArrayList<AttributeDefinition>();
        attributeDefinitions.add(new AttributeDefinition().withAttributeName(KEY_NAME).withAttributeType("S"));

        List<KeySchemaElement> keySchemaElements = new ArrayList<KeySchemaElement>();
        keySchemaElements.add(new KeySchemaElement().withAttributeName(KEY_NAME).withKeyType(KeyType.HASH));

        CreateTableRequest request = new CreateTableRequest()
                .withTableName("User")
                .withKeySchema(keySchemaElements)
                .withAttributeDefinitions(attributeDefinitions)
                .withProvisionedThroughput(new ProvisionedThroughput().withReadCapacityUnits(READ_CAPACITY_UNITS)
                        .withWriteCapacityUnits(WRITE_CAPACITY_UNITS));

        amazonDynamoDB.createTable(request);

    }

    public Pageable whatEver(){
        return new Pageable() {
            @Override
            public int getPageNumber() {
                return 0;
            }

            @Override
            public int getPageSize() {
                return 0;
            }

            @Override
            public int getOffset() {
                return 0;
            }

            @Override
            public Sort getSort() {
                return null;
            }

            @Override
            public Pageable next() {
                return null;
            }

            @Override
            public Pageable previousOrFirst() {
                return null;
            }

            @Override
            public Pageable first() {
                return null;
            }

            @Override
            public boolean hasPrevious() {
                return false;
            }
        };
    }

    @Test
    public void sampleTestCase() {
        User dave = new User();
        dave.setFirstName("Dave");
        dave.setLastName("Matthews");
        dave.setId("1");
        repository.save(dave);

        User carter = new User();
        carter.setFirstName("Carter");
        carter.setLastName("Beauford");
        carter.setId("2");
        repository.save(carter);

        Pageable pageable = whatEver();
        Page result = repository.findByLastName("Matthews", pageable);
//        Assert.assertEquals(result.size(), 1);
//        Assert.assertThat(result.size(), is(1));
//        Assert.assertThat(result, hasItem(dave));
    }

    @Test
    public void photoTest() throws ObjectModelValidationException {
//        PhotoModel photoModel = new PhotoModel("photoId", "blaUrl", "XXXX", "blaDescription", null, null);

        Photo photo = new Photo();
        photo.setId("someid3");
        photo.setDescription("Some description");

        photoRepository.save(photo);
        Pageable pageable = whatEver();
//        Page<Photo> result = photoRepository.findAll(pageable);

        Photo result = photoRepository.findOne("someid3");
    }

    private Photo convert(PhotoModel photoModel){
        Photo result = new Photo();
        result.setId(photoModel.getId());
        result.setUrl(photoModel.getUrl());
        result.setAlbumId(photoModel.getAlbumId());
        result.setDescription(photoModel.getDescription());
        result.setLatitude(photoModel.getLatitude());
        result.setLongitude(photoModel.getLongitude());
        return result;
    }

    private Album convert(AlbumModel albumModel){
        Album result = new Album();
        result.setId(albumModel.getId());
        result.setAlbumName(albumModel.getAlbumName());
        result.setFolder(albumModel.getFolder());
        result.setPublishDate(albumModel.getPublishDate());
        result.setVisible(albumModel.isVisible());
        return result;
    }

    @Test
    public void migration(){
        List<PhotoModel> photoModels = albumService.getAllPhotoModelList();
        int size = photoModels.size();
        log.info("Number of items: " + size);

        int count = 0;

        for (PhotoModel photoModel : photoModels ) {
            log.info("Saving picture (" + count + "/" + size + "), id: " + photoModel.getId());
            photoRepository.save(convert(photoModel));
            count += 1;
        }

//
//        photoModels.stream().forEach(photoModel -> {
//            log.info("Saving picture (" + count + "");
//            photoRepository.save(convert(photoModel));
//            count += 1;
//        });
    }

    @Test
    public void migrationAlbum(){
        albumService.getAlbumModelList().stream().forEach( albumModel ->
                albumRepository.save(convert(albumModel)));
    }

    @Test
    public void testPageable(){
        Pageable pageable = new PageRequest(0, 4);
        Slice<Photo> photoPage = photoRepository.findByAlbumId("testalbum0001", pageable);
        List<Photo> photos = photoPage.getContent();
        System.out.println("Size: " + photos.size());

    }
}
