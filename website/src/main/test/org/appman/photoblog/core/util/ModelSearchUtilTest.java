package org.appman.photoblog.core.util;

import org.appman.photoblog.core.application.Application;
import org.appman.photoblog.core.exception.ObjectModelValidationException;
import org.appman.photoblog.core.model.AbstractObjectModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.appman.photoblog.core.util.ModelSearchUtil.searchById;
import static org.junit.Assert.assertEquals;


/**
 * Created by Pieter on 4/14/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class ModelSearchUtilTest {

    @Test
    public void testSearchById() throws ObjectModelValidationException {
        // Preconditions
        MockObjectModel object1 = new MockObjectModel("object1id");

        MockObjectModel object2 = new MockObjectModel("object2id");


        List<MockObjectModel> objectModelList = new ArrayList<MockObjectModel>();
        objectModelList.add(object1);
        objectModelList.add(object2);

        // Actions
        Optional<MockObjectModel> result = searchById(objectModelList, "object2id");

        // Expected
        assertEquals(Optional.of(object2.getId()), result.map(AbstractObjectModel::getId));


    }





}
