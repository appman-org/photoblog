package org.appman.photoblog.wd.page;

import com.codeborne.selenide.SelenideElement;
import org.appman.photoblog.wd.AbstractMediaListingPage;

import static com.codeborne.selenide.Condition.visible;
import static org.appman.wdbase.SelenideWrapper.$c;

/**
 * Created by Pieter on 11/10/2016.
 */
public class BlogListingPage extends AbstractMediaListingPage {

    public SelenideElement blogListingPageId(int pageNumber){
        return $c("data-st-page-blog-listing-" + pageNumber);
    }

    @Override
    protected String getPageBaseUrl() {
        return "/blogs";
    }

    @Override
    public BlogListingPage isAtThisPage(String... pageArgs) {
        blogListingPageId(Integer.parseInt(pageArgs[0])).shouldBe(visible);
        return this;
    }
}
