Feature: Media Post Listing pages

Scenario: Home page is working
  Given that I navigate to home with page number 1
  Then There should be a blogpost with a title
  Then the pagination element should be displayed

Scenario: Home page number 1
  Given that I navigate to home with page number 1
  Then the pagination item First should be marked as active

Scenario: Home page number 2
  Given that I navigate to home with page number 2
  Then the pagination item of page 2 should be marked as active

Scenario: Album Listing page number 1
  Given that I navigate to the Album Listing with page number 1
  Then the pagination element should be displayed
  And the pagination item First should be marked as active

Scenario: Album Listing page number 2
  Given that I navigate to the Album Listing with page number 2
  Then the pagination item of page 2 should be marked as active

Scenario: Blog Listing page number 1
  Given that I navigate to the Blog Listing with page number 1
  Then the pagination element should be displayed
  And the pagination item First should be marked as active

  Scenario: Blog Listing page number 2
    Given that I navigate to the Blog Listing with page number 2
    Then the pagination item of page 2 should be marked as active