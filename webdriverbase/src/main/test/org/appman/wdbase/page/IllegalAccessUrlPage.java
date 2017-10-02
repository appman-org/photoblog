package org.appman.wdbase.page;

/**
 * Created by Pieter on 10/25/2016.
 */
public class IllegalAccessUrlPage extends AbstractPage {
    @Override
    public IllegalAccessUrlPage isAtThisPage(String... pageArgs) {
        return this;
    }

    private String url = "test";
}
