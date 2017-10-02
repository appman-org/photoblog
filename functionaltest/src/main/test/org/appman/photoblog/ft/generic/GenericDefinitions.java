package org.appman.photoblog.ft.generic;

import static com.codeborne.selenide.Condition.*;
import static org.appman.photoblog.wd.flow.AbstractBlogFlow.getCurrentBlogPage;

import cucumber.api.java.en.Then;
import org.appman.ftbase.core.AbstractCucumberDefinition;

/**
 * Created by Pieter on 11/5/2016.
 */
public class GenericDefinitions extends AbstractCucumberDefinition {

    @Then("the comments block is shown")
    public void checkCommentsBlockShown(){
        getCurrentBlogPage().commentsBlock.shouldBe(visible);
    }

    @Then("the bread crumb block is shown")
    public void checkBreadCrumBlockShown(){
        getCurrentBlogPage().breadCrumbBlock.shouldBe(visible);
    }

}
