package org.appman.photoblog.wd.flow;

import org.appman.photoblog.wd.AbstractBlogPage;
import org.appman.wdbase.flow.AbstractFlow;

/**
 * Created by Pieter on 11/5/2016.
 */
public abstract class AbstractBlogFlow extends AbstractFlow {

    public static <T extends AbstractBlogPage> T getCurrentBlogPage(){
        return (T)getCurrentPage();
    }

}
