package org.appman.photoblog.page.generic.service;

import org.appman.photoblog.page.generic.exception.TextParsingException;

import java.util.Optional;

/**
 * Created by Pieter on 6/23/2016.
 */
public interface TextParserService {

    public Optional<String> parseStringToHtml(String input) throws TextParsingException;

    public Optional<String> parseStringToHtml(String input, int limit) throws TextParsingException;


}
