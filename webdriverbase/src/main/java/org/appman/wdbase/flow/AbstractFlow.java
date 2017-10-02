package org.appman.wdbase.flow;

import com.codeborne.selenide.Configuration;
import org.appman.wdbase.exception.WebdriverTestRunTimeException;
import org.appman.wdbase.exception.NoUrlDefinedException;
import org.appman.wdbase.page.AbstractPage;

import java.util.Optional;

import static com.codeborne.selenide.Selenide.open;

/**
 * Created by Pieter on 10/21/2016.
 */
public abstract class AbstractFlow {

    private static Optional<AbstractPage> currentPage = Optional.empty();

    public static <T extends AbstractPage> T getCurrentPage(){
        return (T)currentPage
                .orElseThrow(() -> new WebdriverTestRunTimeException("Current Page is undefined"));
    }

    private static <T extends AbstractPage> T initiatePage(Class<T> tPage){
        try {
            T result = (T) (tPage.newInstance());
            currentPage = Optional.of(result);
            return result;
        } catch (InstantiationException e) {
            throw new WebdriverTestRunTimeException("Error while calling at check", e);

        } catch (IllegalAccessException e) {
            throw new WebdriverTestRunTimeException("Error while calling at check", e);
        }
    }

    public static <T extends AbstractPage > T at(Class<T> tPage, String... pageArgs){
        return (T)initiatePage(tPage).isAtThisPage(pageArgs);
    }

    public static <T extends AbstractPage > T goAt(Class<T> tPage, String... pageArgs) {
        go(tPage, pageArgs);
        return at(tPage, pageArgs);
    }

    public static <T extends AbstractPage> T go(Class<T> tPage, String... pageArgs){
        String url;
        T page = initiatePage(tPage);
        url = page.getPageUrl(pageArgs);

        if(url == null){
            throw new NoUrlDefinedException("No url defined for page " + tPage.getSimpleName());
        }

        String destinationUrl = Configuration.baseUrl + url;
        System.out.println("baseUrl: " + Configuration.baseUrl);
        System.out.println("Opening url: " + destinationUrl);
        open(destinationUrl);

        return page;

    }

}
