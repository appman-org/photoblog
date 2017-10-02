package org.appman.photoblog.ft.features.medialisting;

import static com.codeborne.selenide.Condition.*;

import cucumber.api.java.en.And;
import org.appman.ftbase.core.AbstractCucumberDefinition;
import org.appman.photoblog.wd.AbstractMediaListingPage;
import org.appman.photoblog.wd.page.AlbumListingPage;
import org.appman.photoblog.wd.page.BlogListingPage;
import org.appman.photoblog.wd.page.HomePage;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

import static com.codeborne.selenide.Selenide.open;
import static org.appman.photoblog.wd.flow.AbstractBlogFlow.getCurrentBlogPage;
import static org.appman.wdbase.flow.AbstractFlow.at;
import static org.appman.wdbase.flow.AbstractFlow.goAt;

public class MediaListingDefinitions extends AbstractCucumberDefinition {

    @Given("^that I navigate to home with page number (\\d+)$")
    public void openHomePageWithNumber(int pageNumber) {
        goAt(HomePage.class, String.valueOf(pageNumber));
    }

    @Then("There should be a blogpost with a title")
    public void checkBlogPostTitle() {
        at(HomePage.class, String.valueOf(1))
        .checkBlogPostIsShown();
    }

    @Then("the pagination element should be displayed")
    public void checkPagination(){
        AbstractMediaListingPage page = getCurrentBlogPage();
        page.pagination.shouldBe(visible);
    }


    @Then("^the pagination item First should be marked as active$")
    public void thePaginationItemFirstShouldBeMarkedAsActive() throws Throwable {
        AbstractMediaListingPage page = getCurrentBlogPage();
        page.checkPaginationItemIsActive(1, true);

    }

    @And("^the pagination item of page (\\d+) should be marked as active$")
    public void thePaginationItemOfPageShouldBeMarkedAsActive(int pageNumber) throws Throwable {
        AbstractMediaListingPage page = getCurrentBlogPage();
        page.checkPaginationItemIsActive(pageNumber, true);
    }

    @Given("^that I navigate to the Album Listing with page number (\\d+)$")
    public void thatINavigateToTheAlbumListingWithPageNumberNumber(int pageNumber) throws Throwable {
        goAt(AlbumListingPage.class, String.valueOf(pageNumber));
    }

    @Given("^that I navigate to the Blog Listing with page number (\\d+)$")
    public void thatINavigateToTheBlogListingWithPageNumberNumber(int pageNumber) throws Throwable {
        goAt(BlogListingPage.class, String.valueOf(pageNumber));
    }

}
