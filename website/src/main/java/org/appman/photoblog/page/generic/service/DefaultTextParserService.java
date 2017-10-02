package org.appman.photoblog.page.generic.service;

import org.appman.photoblog.core.service.AbstractApplicationService;
import org.appman.photoblog.page.generic.exception.TextParsingException;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Created by Pieter on 6/23/2016.
 */
@Component
public class DefaultTextParserService extends AbstractApplicationService implements TextParserService {

    @Override
    public Optional<String> parseStringToHtml(String input) throws TextParsingException {
        try {
            String result = input.replace("\n", "<br>");
            return Optional.of(result);
        } catch (RuntimeException e){
            throw new TextParsingException("Error while parsing String to HTML with value: "
            + input, e);
        }


    }

    @Override
    public Optional<String> parseStringToHtml(String input, int limit) throws TextParsingException {
        try {
            if(limit == 0 || limit > input.length() ){
                return parseStringToHtml(input);
            } else {
                return parseStringToHtml(input.substring(0, limit) + "...");
            }
        } catch (RuntimeException e) {
            throw new TextParsingException("Error while parsing String to HTML with value: "
                    + input, e);
        }
    }
}
