package org.appman.photoblog.page.generic.service;

import org.appman.photoblog.page.generic.model.blog.AbstractMediaPostModel;
import org.appman.photoblog.page.generic.model.blog.BlogPostModel;
import org.appman.photoblog.core.service.FileReaderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

/**
 * Created by Pieter on 5/28/2016.
 */
@Component
@Slf4j
public class SimpleBlogPostService extends AbstractSimpleMediaPostService {

    private static final String TEXT_BLOG_SEPARATOR = "#break" + "\n";

    @Resource(name = "defaultFileReaderService")
    private FileReaderService fileReaderService;

    public void setFileReaderService(FileReaderService fileReaderService) {
        this.fileReaderService = fileReaderService;
    }

    @Override
    public List<AbstractMediaPostModel> getMediaPostModelList() {


        return getBlogPostsFromInputString(readCompleteBlogFile());
    }


    protected List<AbstractMediaPostModel> getBlogPostsFromInputString(String input){
        return splitToSeparateBlogData(input)
                .stream()
                .map( this::parseStringToBlogPostModel)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .sorted((e1, e2) -> -e1.compareTo(e2))
                .collect(toList());
    }

    protected Optional<BlogPostModel> parseStringToBlogPostModel(String data){
        try {
//            String dataWithRemovedBreak = data.substring(0, data.indexOf("#break"));
            String[] lines = data.split("\\r?\\n");
            // line[0] is header
            LocalDateTime dateTime = LocalDateTime.parse(lines[0]);
            boolean visible = Boolean.parseBoolean(lines[1]);
            String id = lines[2];
            String title = lines[3];
            String singlePhotoId = lines[4];
            StringBuilder rawBodyTextBuilder = new StringBuilder();
            for(int i = 5; i< lines.length; i++){
                rawBodyTextBuilder.append( (lines[i] + "\n") );
            }
            return Optional.of(new BlogPostModel(id, dateTime, visible, title, singlePhotoId, rawBodyTextBuilder.toString()));
        } catch (Exception e) {
            log.error(e.toString());
            return Optional.empty();
        }
    }

    protected String readCompleteBlogFile(){
        String completeFile = null;
        try {
            if(websiteConfig.isRunLocal()){
                completeFile = fileReaderService.readAsSingleString(getBlogFileLocation());
            } else {
                completeFile = fileReaderService.readAsSingleString(getBlogDataFileLocationUrl());
            }
        } catch (IOException e) {
            log.info(e.toString());
        }
        return completeFile;
    }

    protected List<String> splitToSeparateBlogData(String input){
        List<String> result = new ArrayList<>();
        String[] textData = input.split(TEXT_BLOG_SEPARATOR);
        for( String item: textData ){
            result.add(item);
        }

        return result;
    }


    protected URL getBlogDataFileLocationUrl(){
        try {
            return new URL(websiteConfig.getContent().getRemote().getBlogList());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected String getBlogFileLocation(){
        return websiteConfig.getContent().getLocal().getBlogList();

    }

    @Override
    public List<AbstractMediaPostModel> getMediaPostModelList(int limit){
        return getMediaPostModelList(limit, 1);
    }

}
