# Aspire-QA-Challenge
This is the QA challenge from Aspire and the test scripts are developed base on the manual test cases in file **testcase.xlsx**

## Architecture
* **Test pattern**: Page Object Model
* **Base** class: set/get driver
* **CommonActions** class:  contains common methods and method to capture screenshot after each test case.
* **Log**: Reporter.log of TestNG

## Library
* **TestNG**: test framework
* **Selenium WebDriver**: the web browser automation tool
* **WebDriverManager**: initialize browser driver management tool

## How to run

1. Clone repo at: https://github.com/minhtrangle/Aspire-QA-Challenge.git
2. Open Intellij and reload Maven dependencies
3. Right click on **src/test/resources/testng.xml** and select **Run...**

## View test result
* After test run finished, check the report following these steps:
* Right click on **test-output/Suite/AspireTest.html** and select **Open In/Browser/Chrome**


