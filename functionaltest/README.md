Functional tests with Cucumber
========================


### Installation

Make sure the dependent project webdriverbase is installed as described in the projects readme.

### Suggested plugins for Intellij

Via File > Settings > Plugins, add the following plugins:

1. Gherkin
2. Cucumber for Java

### How to run

To run Selenide+Cucumber tests in Intellij, use the "run" feature just as with regular Unit tests. It can happen that the default options are not picked up properly on the first attempt, next time the tests shoudl run with the proper configuration.

In order to run the tests from the command line, make sure to create a jar from the "website" project first.

Then run the tests with:
```
mvn test -Dwebdriver.gecko.driver="<path to geckodriver.exe file>"
```
