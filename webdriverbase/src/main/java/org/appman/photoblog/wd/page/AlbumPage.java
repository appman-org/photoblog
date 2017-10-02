package org.appman.photoblog.wd.page;

import static com.codeborne.selenide.Condition.*;
import com.codeborne.selenide.SelenideElement;
import org.appman.photoblog.wd.AbstractBlogPage;
import org.appman.wdbase.page.AbstractPage;

import static org.appman.wdbase.SelenideWrapper.*;
import static org.appman.photoblog.page.common.pageattributes.PageAttributes.*;

/**
 * Created by Pieter on 10/26/2016.
 */
public class AlbumPage extends AbstractBlogPage {

    public static String url = "/album/testalbum0001";

    private SelenideElement albumContent = $c(ALBUM_CONTENT);
    private SelenideElement albumThumbnail = $c(ALBUM_THUMBNAIL);

    @Override
    public <T extends AbstractPage> T isAtThisPage(String... pageArgs) {
        albumContent.shouldBe(visible);
        return (T)this;
    }

    public AlbumPage checkPictureThumbnailIsVisible() {
        albumThumbnail.shouldBe(visible);
        return this;
    }
}
