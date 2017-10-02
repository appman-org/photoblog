package org.appman.ftbase.core;

import cucumber.api.junit.Cucumber;
import org.appman.ftbase.util.testdata.TestDataUtil;
import org.junit.runners.model.InitializationError;

import java.io.IOException;

/**
 * Created by Pieter on 10/25/2016.
 */
public class CucumberWrapper extends Cucumber{
    public CucumberWrapper(Class clazz) throws InitializationError, IOException {
        super(clazz);
        TestDataUtil data = TestDataUtil.getInstance();
    }
}
