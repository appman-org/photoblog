package org.appman.wdbase.page;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pieter on 10/25/2016.
 */
public class IllegalObjectTypeUrlPage extends AbstractPage {
    @Override
    public <T extends AbstractPage> T isAtThisPage(String... pageArgs) {
        return (T)this;
    }

    List<String> url = new ArrayList<>();
}
