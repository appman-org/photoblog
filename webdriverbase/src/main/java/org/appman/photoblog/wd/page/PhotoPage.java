package org.appman.photoblog.wd.page;

import static com.codeborne.selenide.Condition.*;
import com.codeborne.selenide.SelenideElement;
import org.appman.photoblog.wd.AbstractBlogPage;
import org.appman.wdbase.page.AbstractPage;

import static org.appman.wdbase.SelenideWrapper.$c;
import static org.appman.photoblog.page.common.pageattributes.PageAttributes.*;

/**
 * Created by Pieter on 10/26/2016.
 */
public class PhotoPage extends AbstractBlogPage {

    public static String url = "/photo/10001";

    private SelenideElement photoContent = $c(PHOTO_CONTENT);
    private SelenideElement fullImage = $c(PHOTO_FULL_IMAGE);

    @Override
    public PhotoPage isAtThisPage(String... pageArgs) {
        photoContent.shouldBe(visible);
        return this;
    }

    public PhotoPage checkFullImageShown(){
        fullImage.shouldBe(visible);
        return this;
    }
}
