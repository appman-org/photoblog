package org.appman.photoblog.ft.features.content;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.appman.ftbase.core.AbstractCucumberDefinition;
import org.appman.photoblog.wd.page.ContentPage;

import static org.appman.wdbase.flow.AbstractFlow.at;
import static org.appman.wdbase.flow.AbstractFlow.go;

/**
 * Created by Pieter on 10/27/2016.
 */
public class ContentDefinitions extends AbstractCucumberDefinition {

    @Given("the about page is openend")
    public void openAboutPage(){
        go(ContentPage.class);
    }


    @Then("the content is displayed")
    public void atAboutPage(){
        at(ContentPage.class);

    }
}
