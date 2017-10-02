package org.appman.photoblog.wd.page;

import static com.codeborne.selenide.Condition.*;
import com.codeborne.selenide.SelenideElement;
import org.appman.photoblog.wd.AbstractMediaListingPage;

import static com.codeborne.selenide.Selenide.$;
import static org.appman.wdbase.SelenideWrapper.$c;
import static org.appman.photoblog.page.common.pageattributes.PageAttributes.*;

/**
 * Created by Pieter on 10/20/2016.
 */
public class HomePage extends AbstractMediaListingPage {


    public SelenideElement postTitle = $c(BLOG_OVERVIEW_POST_TITLE);

    public SelenideElement homePageId(int pageNumber){
        return $c("data-st-page-home-" + pageNumber);
    }

    public void checkBlogPostIsShown(){
        postTitle.shouldBe(visible);
    }



    public HomePage isAtThisPage(String... pageArgs) {
        homePageId(Integer.parseInt(pageArgs[0])).shouldBe(visible);
        return this;
    }

    @Override
    protected String getPageBaseUrl() {
        return "";
    }
}
