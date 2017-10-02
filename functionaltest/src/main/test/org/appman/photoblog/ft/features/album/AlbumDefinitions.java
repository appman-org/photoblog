package org.appman.photoblog.ft.features.album;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.appman.ftbase.core.AbstractCucumberDefinition;
import org.appman.photoblog.wd.page.AlbumPage;
import org.appman.photoblog.wd.page.PhotoPage;

import static org.appman.wdbase.flow.AbstractFlow.at;
import static org.appman.wdbase.flow.AbstractFlow.goAt;

/**
 * Created by Pieter on 10/26/2016.
 */
public class AlbumDefinitions extends AbstractCucumberDefinition {

    @Given("the album page is openend")
    public void openAlbumPage(){ goAt(AlbumPage.class);}

    @Then("a thumbnail of a picture should be visible")
    public void checkPictureThumbnaialPresent(){
        at(AlbumPage.class)
                .checkPictureThumbnailIsVisible();
    }

    @Given("the photo page is openend")
    public void openPhotoPage(){ goAt(PhotoPage.class);}

    @Then("the full image is shown")
    public void checkFullImagePresent(){
        at(PhotoPage.class)
                .checkFullImageShown();
    }

}
