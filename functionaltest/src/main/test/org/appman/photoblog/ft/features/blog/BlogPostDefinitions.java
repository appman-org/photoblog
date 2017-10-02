package org.appman.photoblog.ft.features.blog;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.appman.ftbase.core.AbstractCucumberDefinition;
import org.appman.photoblog.wd.page.TextBlogPostPage;

import static org.appman.wdbase.flow.AbstractFlow.at;
import static org.appman.wdbase.flow.AbstractFlow.goAt;

/**
 * Created by Pieter on 10/27/2016.
 */
public class BlogPostDefinitions extends AbstractCucumberDefinition {

    @Given("the text blog post page is openend")
    public void openTextBlogPostPage(){
        goAt(TextBlogPostPage.class);
    }

    @Then("the text of the post is present")
    public void checkTextPresent(){
        at(TextBlogPostPage.class)
                .checkTextContentIsPresent();
    }

}
