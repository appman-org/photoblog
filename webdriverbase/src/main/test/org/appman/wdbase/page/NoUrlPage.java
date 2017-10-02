package org.appman.wdbase.page;

/**
 * Created by Pieter on 10/25/2016.
 */
public class NoUrlPage extends AbstractPage{
    @Override
    public <T extends AbstractPage> T isAtThisPage(String... pageArgs) {
        return (T)this;
    }
}
