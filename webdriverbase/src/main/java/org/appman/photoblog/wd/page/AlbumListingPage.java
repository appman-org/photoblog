package org.appman.photoblog.wd.page;

import static com.codeborne.selenide.Condition.*;
import com.codeborne.selenide.SelenideElement;
import org.appman.photoblog.wd.AbstractMediaListingPage;

import static org.appman.wdbase.SelenideWrapper.$c;

/**
 * Created by Pieter on 10/26/2016.
 */
public class AlbumListingPage extends AbstractMediaListingPage {

    public SelenideElement albumListingPageId(int pageNumber){
        return $c("data-st-page-album-listing-" + pageNumber);
    }


    @Override
    protected String getPageBaseUrl() {
        return "/albums";
    }

    @Override
    public AlbumListingPage isAtThisPage(String... pageArgs) {
        albumListingPageId(Integer.parseInt(pageArgs[0])).shouldBe(visible);
        return this;
    }
}
