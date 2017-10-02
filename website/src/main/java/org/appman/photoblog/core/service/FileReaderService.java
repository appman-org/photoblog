package org.appman.photoblog.core.service;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * Created by Pieter on 4/15/2016.
 */
public interface FileReaderService {

    public List<String> read(String location) throws IOException;

    public List<String> read(URL url) throws IOException;

    public List<String> getFilenames(String location);

    public String readAsSingleString(String location) throws IOException;

    public String readAsSingleString(URL url) throws IOException;

}
