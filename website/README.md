# Photo blog website project


## Exploring the code

The best place to start looking at the code is probably in the `org.appman.photoblog.page` package.
Over here you'll find code that is directly related to the different pages on the website, you can for instance find
the `PhotoPageControler` class in the package `org.appman.photoblog.page.album.controller`. From here you can see what
code is called on each page request.

**Definitions/rules**

- Controllers use methods from Facade classes.
- Facade classes are meant for all the presentation logic and gather all the needed data from any source, this can be
other facade's but usually services.
- Service classes are meant to perform very specific tasks. Services can in some cases call other services but never Facades.
- Util classes contain only static methods


## Setting up the environment
The following software is needed:

- Java 8 JDK
- Maven 3.3.9
- GIT
- SourceTree (optional)
- IntelliJ Community edition (or other preferred IDE)


Add the following environment variables if not present:

- JAVA_HOME - location of the installed JDK (i.e. C:\Program Files\Java\jdk1.8.0_77)
- M2_HOME - location of Maven (i.e. C:\Program Files (x86)\apache-maven-3.3.9)
- GIT_HOME - location of Git (i.e. C:\Program Files\Git)

Add the following to the path:

    %JAVA_HOME%
    %M2_HOME%\bin
    %GIT_HOME%

Install the following plugins for Intellij:
- Lombok plugin


## Building and running
When running the application directly:

    mvn spring-boot:run

Running a .war file:

    java -jar  [filename].war

In addition for selecting the active spring profile:

    -Dspring.profiles.active=[profilename]


## Creating a .war or .jar file
A .war file is needed for deployment of the application, the .jar is needed for dependent test projects.


For a .war file:

    mvn clean install

And for a .jar file:

    mvn clean install -P jar

Check the target folder for the result.


## Spring profiles
Three spring profiles are available:

- none/default - used for running the application local for testing and debugging.
- `prod` - production
- `dev` - integration testing

To select the profile, add the following command line option:
 `-Dspring.profiles.active=[profile_name]`
 This can be done both in Intellij Run configurations and in the options in AWS Elastic Beanstalk.
