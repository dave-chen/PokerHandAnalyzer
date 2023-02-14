

# Poker Hand Analyzer API

A REST API that determines if a poker 7 hand is a straight.

## Requirements

Java 17
Gradle 7.4.2
Spring Boot 2.3.3 or later  
Getting Started

**Clone the repository:**  
git clone https://github.com/<your-username>/poker-hand-validation-api.git  
Navigate to the project directory:


**Download the zipfile:**  
Copy code  
mvn spring-boot:run  
Access the API endpoint at http://localhost:8080/is-straight.


## API Documentation

The API documentation is generated using Swagger and is available at http://localhost:8080/swagger-ui.html when the application is running.

**POSTMAN collection:**
{  
"info": {  
"name": "Poker Hand Validation API",  
"description": "A collection of API tests for the Poker Hand Validation API",  
"schema": "https://schema.getpostman.com/json/collection/v2.1.0/collection.json"  
},  
"item": [  
{  
"name": "Is Straight Endpoint",  
"request": {  
"url": "http://localhost:8080/is-straight",  
"method": "POST",  
"header": [  
{ "key": "Content-Type", "value": "application/json" } ],  
"body": {  
"mode": "raw",  
"raw": "{\"hand\":[\"2S\",\"3C\",\"4H\",\"5D\",\"6C\"]}"  
},  
"description": "Tests if the hand is a straight"  
},  
"response": []  
}  
]  
}

## Testing Document

This document outlines the testing process for the Poker Hand Analyzer API.

**Unit Testing**  
Unit tests are used to test individual components of the application, such as the isStraight method. The unit tests are implemented using JUnit and are located in the src/test/java directory.

To run the unit tests, run the following command in the project directory:

bash  
Copy code  
mvn test

**API Testing**  
API tests are used to test the API endpoints, such as the /is-straight endpoint. The API tests are implemented using Postman or similar API testing tool.

To run the API tests, perform the following steps:

Import the Postman collection located in the tests directory into Postman.

Run the API tests in the Postman collection.

**Integration Testing**  
End-to-end tests are used to test the application as a whole, from end-to-end. The end-to-end tests are implemented using Selenium or similar end-to-end testing tool.

To run the end-to-end tests, perform the following steps:

Start the application by running the following command in the project directory:

Copy code  
mvn spring-boot:run  
Run the end-to-end tests using Selenium or similar end-to-end testing tool.

**Code Coverage**  
Code coverage is a measure of how much of the application's code is being tested by the tests. The code coverage is generated using JaCoCo or similar code coverage tool.

To generate the code coverage report, run the following command in the project directory:

Copy code  
mvn jacoco:report  
The code coverage report will be generated in the target/site/jacoco directory and can be viewed in a web browser. The report shows which lines of code are covered by tests and which lines of code are not covered. The goal is to have a high code coverage, meaning that a large portion of the code is covered by tests.  
