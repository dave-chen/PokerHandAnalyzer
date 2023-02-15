


# Poker Hand Analyzer API

A REST API that determines if a poker 7 hand is a straight.

![Build Status](https://app.travis-ci.com/dave-chen/PokerHandAnalyzer.svg?branch=master)

## Requirements

Java 17  
Gradle 7.4.2  
Spring Boot 2.3.3 or later

## Getting Started

**Clone the repository:**

    git clone https://github.com/dave-chen/PokerHandAnalyzer.git  


OR

**Download the zipfile:**
https://github.com/dave-chen/PokerHandAnalyzer/archive/refs/heads/master.zip


**Run the application:**

     ./gradlew bootrun


## API Documentation

**Swagger:**
http://localhost:8080/swagger-ui.html when the application is running.


## Testing Document

Outlines the testing process for the Poker Hand Analyzer API.

**Unit Testing**
Unit tests are used to test individual components of the application, such as the isStraight method. The unit tests are implemented using JUnit and are located in the src/test/java directory.

To run the unit tests, run the following command in the project directory:

     ./gradlew test

**Endpoint Testing**

The endpoint tests can be tested by CURL:

To run the  tests, perform the following steps when the application is running:

      curl -X POST \
      http://localhost:8080/v1/poker/is-straight \
      -H 'Content-Type: application/json' \
      -d '["9D", "8C","2S", "3C", "4H", "5D", "6C"]'

Postman testing collection:

Coming soon...


**Integration Testing**

Coming soon

**Code Coverage**
The code coverage is generated using JaCoCo or similar code coverage tool.    
To generate the code coverage report, run the following command in the project directory:

    ./gradlew jacocoTestReport

The report will be generated inside the default directory:

    $buildDir/reports/jacoco/test
