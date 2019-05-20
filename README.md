# Hotel Booking Test Automation

Test Automation framework to write and execute tests for the hotel booking form.

## Tools
* IDE: Eclipse
* Langauge: Java
* Libraries: Selenium WebDriver, Junit, Cucumber.

## Execution Instructions

To run tests from the command line: 
* "mvn test" will execute the tests in the default browser chrome.
* "mvn test -Dbrowser=<browser>" to run on a specific browser.
* Currently supports Mac and Windows OS and latest versions of Firefox, Chrome .

To run tests from IDE:
* "RunCuksesTest" file -> The test scripts will run on the chrome browser by default.

## Reporting:
* The report is available at the following location: 
/hotel-booking-qa/target/cucumber-html-report/index.html