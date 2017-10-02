package org.appman.ftbase.util.testdata;

import org.appman.wdbase.exception.WebdriverTestRunTimeException;

/**
 * Created by Pieter on 10/25/2016.
 */
public class TestDataUtil {

    private static TestDataUtil instance = null;

    private EnvironmentType environmentType;

    private TestDataUtil(){
        determineEnvironmentType();
    }

    public static TestDataUtil getInstance(){
        if(instance == null){
            instance = new TestDataUtil();
        }
        return instance;
    }

    private void determineEnvironmentType(){
        final String input = System.getProperty("envType");
        if(input == null){
            environmentType = EnvironmentType.LOCAL;
            return;
        }
        try {
            environmentType = EnvironmentType.valueOf(input);
        }catch (IllegalArgumentException e) {
            throw new WebdriverTestRunTimeException("Illegal System Property: no environmentType exist with name: " + input, e);
        }
        System.out.println("Set environmentType to value: " + environmentType.toString());
    }


}
