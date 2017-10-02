package org.appman.wdbase.page;

import org.junit.Test;
import org.appman.wdbase.exception.NoUrlDefinedException;
import org.appman.wdbase.exception.WebdriverTestRunTimeException;

/**
 * Created by Pieter on 10/25/2016.
 */
public class AbstractPageTest {

    @Test ( expected = WebdriverTestRunTimeException.class)
    public void test_getPageUrl_illegalObjectType(){
        IllegalObjectTypeUrlPage illegalObjectTypeUrlPage = new IllegalObjectTypeUrlPage();
        illegalObjectTypeUrlPage.getPageUrl();

    }

    @Test ( expected = NoUrlDefinedException.class)
    public void test_getPageUrl_noUrl(){
        NoUrlPage noUrlPage = new NoUrlPage();
        noUrlPage.getPageUrl();

    }


    @Test ( expected = WebdriverTestRunTimeException.class)
    public void test_getPageUrl_illegalAccess(){
        IllegalAccessUrlPage illegalAccessUrlPage = new IllegalAccessUrlPage();
        illegalAccessUrlPage.getPageUrl();

    }
}
