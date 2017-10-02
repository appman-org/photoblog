package org.appman.photoblog.core.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pieter on 4/15/2016.
 */
@Component
@Slf4j
public class DefaultFileReaderService extends AbstractApplicationService implements FileReaderService {

    @Override
    public List<String> read(String fileName) throws IOException {

        return readLinesFromReader(new FileReader(fileName));

    }

    @Override
    public List<String> read(URL url) throws IOException {

        return readLinesFromReader(new InputStreamReader(url.openStream()));
    }

    private String readCompleteFileFromReader(Reader reader) throws IOException{
        StringBuilder builder = new StringBuilder();
        BufferedReader br = new BufferedReader(reader);

        String line;
        while ((line = br.readLine()) != null) {
            builder.append(line + "\n");
        }

        return builder.toString();
    }

    private List<String> readLinesFromReader(Reader reader) throws IOException{
        List<String> result = new ArrayList<String>();
        BufferedReader br = new BufferedReader(reader);

        String line;
        while ((line = br.readLine()) != null) {
            result.add(line);
        }

        return result;
    }

    @Override
    public List<String> getFilenames(String location) {
        File folder = new File(location);
        File[] listOfFiles = folder.listFiles();

        List<String> result = new ArrayList<String>();

        for (File file : listOfFiles) {
            if (file.isFile()) {
                String fileName = file.getName();
                result.add(fileName);


            } else if (file.isDirectory()) {
                log.info("Skipping directory " + file.getName());
            }
        }

        return result;
    }

    @Override
    public String readAsSingleString(String fileName) throws IOException {
        return readCompleteFileFromReader(new FileReader(fileName));
    }

    @Override
    public String readAsSingleString(URL url) throws IOException {
        return readCompleteFileFromReader(new InputStreamReader(url.openStream()));
    }
}
