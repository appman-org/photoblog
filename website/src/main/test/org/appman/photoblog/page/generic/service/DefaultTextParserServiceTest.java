package org.appman.photoblog.page.generic.service;

import org.appman.photoblog.page.generic.exception.TextParsingException;
import org.appman.photoblog.core.application.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

/**
 * Created by Pieter on 6/23/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class DefaultTextParserServiceTest extends DefaultTextParserService {

    @Test
    public void testParseStringToHtmlPositive() throws TextParsingException {
        // Setup
        String input = "rule one\nrule two";

        // Act
        Optional<String> result = parseStringToHtml(input);

        // Assert
        assertEquals(true, result.isPresent());
        assertEquals("rule one<br>rule two", result.get());

    }

    @Test (expected = TextParsingException.class)
    public void testParseStringToHtmlNegative() throws TextParsingException {
        // Act
        Optional<String> result = parseStringToHtml(null);

    }

}
