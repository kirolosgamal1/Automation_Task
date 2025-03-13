# Automated Testing Project

This repository contains two key components for automated testing:
1. **UI Automation**: End-to-end testing of Amazon's website using Selenium WebDriver, TestNG, Page Object Model (POM), and Data-Driven Testing (DDT).
2. **API Testing**: Validation of REQRES APIs using Java and RestAssured.

## Table of Contents
- Project Summary
- Setup Instructions
- Test Scenarios
- How to Execute Tests
- Error Handling Approach
- Technologies Used

## Project Summary

### UI Automation - Amazon Egypt
This automation suite executes the following workflow on the Amazon Egypt website:
1. Open the Amazon homepage and log in.
2. Access the "All" menu, navigate to "Video Games," and select "All Video Games."
3. Apply filters for "Free Shipping" and "Condition: New."
4. Sort the results by "Price: High to Low."
5. Add products costing less than 15,000 EGP to the cart.
6. Confirm all selected items are in the cart.
7. Add a delivery address.
8. Verify the total price calculation.

The automation is built using Selenium WebDriver for UI interactions, TestNG for test execution, and Maven for dependency management.

### API Testing - ReqRes
The API test suite validates the [ReqRes API](https://reqres.in) through these scenarios:
1. **User Creation**: Send a POST request to `/api/users` with user details.
2. **User Retrieval**: Fetch user details using a GET request to `/api/users/{id}`.
3. **User Update**: Modify user details with a PUT request to `/api/users/{id}`.

This part of the project utilizes RestAssured for API validation, TestNG for execution, and Maven for dependency handling.

## Setup Instructions

To run the project locally, follow these steps:

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/kirolosgamal1/Automation_Task.git
   ```

2. **Ensure Prerequisites are Installed**:
   - Java **17 or higher**
   - Apache Maven for dependency management
   - Required dependencies (RestAssured, TestNG, Selenium) are managed via Maven

## Test Scenarios

### UI Automation Workflow

1. **Amazon Login**:
   - Open Amazon Egypt and sign in with valid credentials.

2. **Navigating to Video Games**:
   - Access the "All" menu, then click "Video Games" and select "All Video Games."

3. **Applying Filters**:
   - Enable "Free Shipping" and set "Condition" to "New."

4. **Sorting and Adding Items**:
   - Sort items by "Price: High to Low" and add all products under 15,000 EGP to the cart.

5. **Cart & Address Verification**:
   - Ensure all selected items are in the cart.
   - Add a shipping address.

6. **Price Validation**:
   - Confirm the total amount matches the expected value.

### API Test Cases

1. **Create a User**:
   - Send a POST request to `/api/users` with user details (name, job).
   - Validate the response for successful user creation.

2. **Retrieve User Data**:
   - Fetch details using a GET request to `/api/users/{id}`.
   - Verify that the response matches the expected user details.

3. **Update User Information**:
   - Modify user data using a PUT request to `/api/users/{id}`.
   - Confirm the updated details in the response.

## How to Execute Tests

### Running UI Tests:
1. Locate `gui_testng.xml`.
2. Right-click and select **Run** to start UI automation tests.

### Running API Tests:
1. Locate `testng.xml`.
2. Right-click and select **Run** to execute API test cases.

## Error Handling Approach

To improve test reliability and maintainability, error handling is implemented using:

- **Soft Assertions**: Ensures validations do not stop test execution prematurely.
- **Try-Catch Blocks**: Captures exceptions and logs detailed error messages for debugging.
- **Dynamic Waits & Timeouts**: Implements custom waiting mechanisms for better element handling.

## Technologies Used

- **Selenium WebDriver** → UI Automation
- **RestAssured** → API Testing
- **TestNG** → Test Execution Framework
- **Maven** → Dependency Management
- **Java** → Programming Language
- **Page Object Model (POM)** → Design Pattern for UI Tests
- **Data-Driven Testing (DDT)** → Parameterized Testing Approach

---
This project ensures seamless automation coverage for both UI and API testing using best practices in software testing and automation.

