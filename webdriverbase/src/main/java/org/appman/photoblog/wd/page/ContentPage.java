package org.appman.photoblog.wd.page;

import static com.codeborne.selenide.Condition.visible;
import com.codeborne.selenide.SelenideElement;
import org.appman.photoblog.wd.AbstractBlogPage;
import org.appman.wdbase.page.AbstractPage;

import static org.appman.wdbase.SelenideWrapper.$c;
import static org.appman.photoblog.page.common.pageattributes.PageAttributes.*;

/**
 * Created by Pieter on 10/26/2016.
 */
public class ContentPage extends AbstractBlogPage {

    public static String url = "/about";

    SelenideElement contentPageContent = $c(CONTENT_PAGE_CONTENT);

    @Override
    public <T extends AbstractPage> T isAtThisPage(String... pageArgs) {
        contentPageContent.shouldBe(visible);
        return (T)this;
    }
}
