package org.appman.photoblog.wd;

import com.codeborne.selenide.SelenideElement;
import org.appman.wdbase.page.AbstractPage;

import static org.appman.photoblog.page.common.pageattributes.PageAttributes.*;
import static org.appman.wdbase.SelenideWrapper.*;

/**
 * Created by Pieter on 10/23/2016.
 */
public abstract class AbstractBlogPage extends AbstractPage {

    public SelenideElement breadCrumbBlock = $c(BLOCK_BREADCRUMB);
    public SelenideElement commentsBlock = $c(BLOCK_COMMENTS);

}
