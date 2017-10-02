package org.appman.photoblog.core.service;

import org.appman.photoblog.core.application.Application;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.IOException;
import java.util.List;



/**
 * Created by Pieter on 4/14/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@Ignore
public class DefaultFileReaderServiceTest extends DefaultFileReaderService{

    @Test
    public void testReadFile() throws IOException{
        List<String> result = read("C://dev//code//temp//test.json.txt");

//        JSONObject result =         parseFileToJSON("C:/dev/code/temp/test.json.txt");
//        System.out.print(result.toString());
    }

    @Test
    public void testReadFileNames(){

        List<String> result = getFilenames("C://dev//code//temp//testalbum");


    }




}
