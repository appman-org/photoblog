package org.appman.wdbase;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

/**
 * Created by Pieter on 10/26/2016.
 */
public class SelenideWrapper extends Selenide {

    public static SelenideElement $c(String htmlClass) {
        return $("." + htmlClass);
    }

    public static ElementsCollection $$c(String htmlClass){
        return $$("." + htmlClass);
    }
}
