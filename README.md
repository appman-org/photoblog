# Photo blog website project


## Background
I've started working on this project weeks before I went traveling for several months. At the time I left the initial
version was not live yet, so I spent significant time programming in between days of hiking in the mountains :)

I created this project because I enjoy programming and I wanted to build something from scratch so I could learn from it,
instead of using an already existing CMS.
 

## Features

The following page types are available:

- Media Listing pages; the album overview, blog overview and home pages: listing albums, blogs or both together.
- Album pages: overview of an album
- Photo pages: individual photo's
- Blog pages: individual blog posts
- Map page: all pictures on the map
- About page: simple content page
- RSS feed: the most recent albums and blogs are available as RSS feed

The following functionality is available:

- All the pages are responsive for use in both Desktop and Mobile browsers
- Pictures with GPS coordinates in Exif are shown on Google maps, on album pages, photo pages and all
of them together on the map page. Markers on the map of photo's that are close to each other on the map are clustered.
- Comments can be made via Disqus on photo pages, album pages and blog posts
- Tracking via Google Analytics


## Architecture

This photo blog code base consists of three separate Maven projects. 

- `website` - Production application code and unit/integration tests
- `webdriverbase` - Page Object Model and generic code that can be used for browser tests by any test framework
- `functionaltest` - functional tests in Gherkin/Cucumber, using the `webdriverbase` page model. 

The `PageAttributes` class in the `website` project is used to define HTML page attributes specifically for browser tests.
 These attributes are thus both used in the jsp page templates in the `website` project and in the page classes in the 
 `webdriverbase` project.

A separation between the page model and the functional tests was created to be open for adding technical browser tests
via a Junit style test framework, while using the same generic browser related code and page object model as the
functional tests.


## How to run

Follow the descriptions of the readme files of each project, starting with `website`, then `webdriverbase` and then `functionaltest`.


## Open issues

- The main repo for this codebase is in Bitbucket, the repo in Github is a copy with the my personal configuration removed.
This should be set up properly. Without proper configuration integration tests will fail and error pages will probably show
up when running the application.
- Adding new photos/albums is currently done with some 'temporary' workaround by running code via a Junit test and a large
number of manual steps. 