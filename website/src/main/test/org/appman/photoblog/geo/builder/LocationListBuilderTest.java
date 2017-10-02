package org.appman.photoblog.geo.builder;

import org.appman.photoblog.core.application.Application;
import org.appman.photoblog.geo.pojo.Location;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Pieter on 1/23/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class LocationListBuilderTest {

    @Test
    public void testFindLocationIdentical(){
        // Setup
        LocationListBuilder builder = new LocationListBuilder();
        Location inputLocation = new Location("0.1", "0.1");
        builder.locationList.add(inputLocation);

        // Act
        Location result = builder.findLocation("0.1", "0.1");

        // Assert
        assert (result == inputLocation);
    }

    @Test
    public void testFindLocationDifferent(){
        // Setup
        LocationListBuilder builder = new LocationListBuilder();
        Location inputLocation = new Location("0.1", "0.1");
        builder.locationList.add(inputLocation);

        // Act
        Location result = builder.findLocation("0.2", "0.2");

        // Assert
        assert (result == null);
    }

    @Test
    public void testAddLocation(){
        // Setup
        LocationListBuilder builder = new LocationListBuilder();

        // Act
        List<Location> locations = builder
                .add("0.1", "0.1", "pageUrl1", "photoUrl1")
                .add("0.2", "0.2", "pageUrl2", "photoUrl2")
                .add("0.1", "0.1", "pageUrl3", "photoUrl1")
                .build();

        // Assert
        assertEquals(2, locations.size());
        assertEquals("pageUrl3", locations.get(0).getLocationItems().get(1).getPageUrl());
        assertEquals("pageUrl2", locations.get(1).getLocationItems().get(0).getPageUrl());
    }

}
