Feature: Photo album

Scenario: Album page is working
  Given the album page is openend
  Then a thumbnail of a picture should be visible
  And the bread crumb block is shown
  And the comments block is shown

Scenario: Photo page is working
  Given the photo page is openend
  Then the full image is shown
  And the bread crumb block is shown
  And the comments block is shown
