# Hotel Booking API Test Automation Framework

This project contains an **API Test Automation Framework** built to test a **Hotel Booking REST API** using **Cucumber BDD**, **Rest Assured**, and **Java**.

The framework follows **Behavior Driven Development (BDD)** principles to make test scenarios readable and maintainable.

---

## Tech Stack

* Java
* Rest Assured
* Cucumber BDD
* Maven
* JUnit / TestNG
* Git
* Eclipse IDE

---

## Project Structure

```
hotel-booking-api-framework
│
├── src/test/java
│   ├── stepdefinitions
│   │   └── BookingSteps.java
│   │
│   ├── runners
│   │   └── TestRunner.java
│   │
│   ├── utils
│   │   └── AuthUtil.java
│   │
│   └── models
│       └── Booking.java
│
├── src/test/resources
│   └── features
│       └── booking.feature
│
├── pom.xml
└── README.md
```

---

## Features Covered

The framework automates the following **Hotel Booking API operations**:

* Create Booking
* Get Booking Details
* Update Booking
* Delete Booking
* Authentication Token Generation

---

## API Endpoints Tested

| Method | Endpoint      | Description                   |
| ------ | ------------- | ----------------------------- |
| POST   | /booking      | Create a booking              |
| GET    | /booking/{id} | Get booking details           |
| PUT    | /booking/{id} | Update booking                |
| DELETE | /booking/{id} | Delete booking                |
| POST   | /auth         | Generate authentication token |

---

## Sample Feature File

```gherkin
Feature: Hotel Booking API

Scenario: Create a new booking
Given I have valid booking details
When I send a POST request to "/booking"
Then the response status code should be 200
And the response should contain booking id
```

---

## Prerequisites

Make sure the following tools are installed:

* Java 8 or higher
* Maven
* Git
* Eclipse IDE

---

## How to Run Tests

Clone the repository:

```
git clone https://github.com/asimkumar04/booking-api-test.git
```

Navigate to the project directory:

```
cd booking-api-test
```

Run the tests using Maven:

```
mvn test
```

---

## Authentication

The framework includes a reusable method to generate an authentication token using username and password before executing secured API operations.

---

## Best Practices Used

* BDD framework using Cucumber
* POJO classes for request payloads
* Reusable utility methods
* Modular framework structure
* Git version control

---

## Author

Asim Kumar

---

## Future Improvements

* Add API schema validation
* Integrate reporting (Allure / Extent Reports)
* Add CI/CD integration (GitHub Actions / Jenkins)
* Add environment configuration support

---
