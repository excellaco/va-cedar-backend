# java-spring-webflux-starter
A REST API starter for Java, Spring, and Webflux

## Dependencies
#### [Spring](https://spring.io/)
- Spring Boot 2.4.1
- Webflux
- Web
- Logging
- Jetty
- JPA
- Actuator
- Security Oauth2

#### Utilities
- [Gradle](https://gradle.org/) - Build automation system
- [Lombok 4.0.0](https://projectlombok.org/) - Developer productivity tool
- [Swagger 2.9.2](https://swagger.io/) - API Documentation
- [goJF](https://github.com/sherter/google-java-format-gradle-plugin): Google Code formatter

#### DB
- [Postgres](https://www.postgresql.org/) - Default database for the repository
- [Flyway](https://flywaydb.org/) - Database migration tool

#### Testing
- [TestNG](https://testng.org/doc/) - Testing framework
- [Mockito](https://site.mockito.org/) - Mocking framework
- [Jacoco](https://www.eclemma.org/jacoco/) - Test coverage tool

#### Static Code Analysis
- [SonarQube 2.7.1](https://www.sonarqube.org/) - Code inspection tool
- [PMD](https://pmd.github.io/) - Static code analysis tool

_See build.gradle for complete list of dependencies_

## Prerequisites

For local development, you will need:

1. [Git](www.git-scm.com)
    * **Windows Users** [You need to preserve Unix line endings on checkout](https://help.github.com/en/articles/configuring-git-to-handle-line-endings) because shells don't like carriage return. Run `git config --global core.autocrlf input`. Ideally you did this before cloning; if not, re-checkout after changing this setting. If you don't do this, git auto-converts LF to CRLF (\r) and the shell scripts will break if run locally.
1. [Docker Compose](https://docs.docker.com/compose/install/) (docker-compose)
    * Linux: It might be easiest just to use your package manager to install the `docker-compose` package. The official instructions can overly complicate it, and also leave off all but the most popular distros.
    * **Windows Users**: make sure you have Win 10 *Professional*, or you may not be able to install the latest Docker tools due to lack of Hyper-V support.
1. <u>JDK >= 11</u> for IDE tooling. To check for a suitable JDK, use `java -version`, making sure it is not a JRE. If you don't have one:
    * Linux: Install the latest OpenJDK package using your distro's package manager. Add the installed binary folder to your PATH and set JAVA_HOME, replacing references to any older JDKs, if the package didn't do it for you.
    * Mac (Homebrew): `brew cask install java`
    * Windows:  Install the [latest JDK](https://www.oracle.com/technetwork/java/javase/downloads/index.html). Edit the system environment variables to add the binary folder to PATH and set JAVA_HOME, unless the installer does this for you.
1. IDE
    * IntelliJ - [Setup](docs/IntelliJ_Setup.md) 
    * VSCode - Not recommended (Gradle plug-in does not support `TestCompile` or `TestImplementation`)

Docker (Engine) version 18.09.8 is known to work;
docker-compose version 1.24.1 is known to work; docker-compose version 1.17.1 is known **not** to work.
Older versions may not support the docker-compose 3.7 file format, which this project uses.

## Quickstart
1. Install the [prerequisites](#prerequisites) above
1. Clone this repository: `git clone git@github.com:excellaco/java-spring-webflux-starter.git`
1. Open a terminal in the root of the cloned project. <u>Windows users should open a Git Bash</u>, which comes with Git for Windows and GitHub Desktop
1. Run `docker-compose run api bash`. Use this bash prompt for the next step
1. Run `./generate-keystore`. It doesn't really matter what information you enter here
1. Run `exit` once to drop out of the docker container
1. Run `./start bootrun` and wait for the backend to start up (`75% EXECUTING` will display when it is up and running)
1. Go to http://localhost:8080/api/swagger-ui.html to see interactive API documentation
1. Before you perform requests from Swagger, click the small lock icon and enter `user` / `pass`

* Terminal or application complaining about \r characters? See the Windows warning under the Git prerequisite

## Detailed Setup Help
### Set up the application
This project uses OAuth2 and JWT for authentication / authorization. In order for the app to work correctly, you must create a Java KeyStore (.jks) file in the classpath (i.e., in src/main/resources). Follow the below steps to generate a keystore file in the project root:

1. `docker-compose run api bash` to get a session on a docker container with the project mounted
1. `./generate-keystore`
1. Enter dummy information for the prompts
1. `exit` once to drop out of the docker session

The generate-keystore script is interactive. It will ask you for a keystore filename (e.g. mytest.jks) and a keystore password. It will then create an .env file for you, then run `keytool` behind the scenes. This will prompt for several user inputs. You can enter dummy data, as this information will only be used in your local environment.

### Run the application
1. Compile the app: `./start clean build`
    * Note: if a script gives permission denied, you may need to run `chmod a+x script_name`
    * Note: if the build fails on the format check, run this and then try again: `./start goJF`. If this is a fresh checkout, go yell at whoever merged the format violations to master.
    * Note: if you get a lot of FileNotFoundException test failures, double check you ran the `generate-keystore` script. There should be a .env file in the app's root directory and a \*.jks file in `src/main/resources`
1. Run the application: `./start bootrun`  (`75% EXECUTING` will display when it is up and running)
1. Navigate to:
	> http://localhost:8080/api/swagger-ui.html
1. Click the lock button to pop up a login prompt. The default credentials are `user` / `pass` .

### Use OAuth to interact with the API (Cleanup TODO)
You can use OAuth to interact with the app's API from the command line:

1. Run the app `./start bootrun`:
1. From the container prompt, run the command: `./get-token`. This will create a bearer token and store it in a file called `token`.
1. Use token to access the API, for example: `curl -K token localhost:8080/api/employee/2`

### Suggested Reading
#### Reactive Web Frameworks

[WebFlux](https://spring.io/guides/gs/reactive-rest-service/) is a reactive web framework for Spring. For more information on reactive design and its basic principles, we suggest looking at the [Reactive Manifesto](https://www.reactivemanifesto.org/).

For a more detailed guide to understanding how to handle reactive and functional types like `Mono` and `Flux`, please refer to our [Java Reactive Tutorial](https://github.com/excellalabs/reactive-in-java)

