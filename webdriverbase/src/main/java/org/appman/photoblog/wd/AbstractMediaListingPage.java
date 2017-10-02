package org.appman.photoblog.wd;

import com.codeborne.selenide.SelenideElement;
import org.appman.wdbase.exception.WebdriverTestRunTimeException;

import static org.junit.Assert.*;
import static org.appman.wdbase.SelenideWrapper.*;
import static org.appman.photoblog.page.common.pageattributes.PageAttributes.*;

/**
 * Created by Pieter on 11/8/2016.
 */
abstract public class AbstractMediaListingPage extends AbstractBlogPage{

    public SelenideElement pagination = $c(PAGINATION_BLOCK);

    abstract protected String getPageBaseUrl();

    @Override
    public String getPageUrl(String... pageArgs){
        if( pageArgs.length > 1){
            throw new WebdriverTestRunTimeException("Url of home page can only have one argument");
        } else if (pageArgs[0].equals("1")){
            return getPageBaseUrl();
        }

        return getPageBaseUrl() + "/page/" + pageArgs[0];
    }


    public SelenideElement paginationItem(int i){
        return $$c(PAGINATION_ITEM).get(i-1);
    }

    public void checkPaginationItemIsActive(int i, boolean active){
        SelenideElement element = paginationItem(i);
        boolean toBeChecked = element.getAttribute("class").contains("active");
        assertEquals(active, toBeChecked );
    }

}
