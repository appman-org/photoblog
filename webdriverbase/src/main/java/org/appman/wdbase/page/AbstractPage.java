package org.appman.wdbase.page;

import org.appman.wdbase.exception.WebdriverTestRunTimeException;
import org.appman.wdbase.exception.NoUrlDefinedException;

/**
 * Created by Pieter on 10/18/2016.
 */
public abstract class AbstractPage {

    public abstract <T extends AbstractPage> T isAtThisPage(String... pageArgs);

    public String getPageUrl(String... pageArgs){
        String result;
        try {
            result  = (String)this.getClass().getDeclaredField("url").get(this);

        } catch (NoSuchFieldException e) {
            throw new NoUrlDefinedException("No url defined for page " + this.getClass().getSimpleName(), e);
        } catch (IllegalAccessException e) {
            throw new WebdriverTestRunTimeException("Error while accessing url property for page" + this.getClass().getSimpleName(), e);
        } catch ( ClassCastException e){
            throw new WebdriverTestRunTimeException("Illegal object with name url defined in class " + this.getClass().getSimpleName()
                    + "url can be a String only", e);
        }
        return result;

    }


}
