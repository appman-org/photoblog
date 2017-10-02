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
public class TextBlogPostPage extends AbstractBlogPage {

    public static String url = "/blog/2016/1/12/blogpostid1";

    private SelenideElement textBlogPostContent = $c(TEXT_POST_CONTENT);
    private SelenideElement text = $c(TEXT_POST_FULL_TEXT);

    @Override
    public <T extends AbstractPage> T isAtThisPage(String... pageArgs) {
        textBlogPostContent.shouldBe(visible);
        return (T)this;
    }

    public TextBlogPostPage checkTextContentIsPresent(){
        text.shouldBe(visible);
        return this;
    }

}
