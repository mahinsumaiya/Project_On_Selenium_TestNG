# Automation Project Using Selenium TestNG

## Project Summary
This project focuses on automating the testing process for key functionalities of a DailyFinance Management System. The primary objectives include user registration, profile updates, cost management, and verifying dashboard data. By employing the Page Object Model (POM) design pattern, the tests are structured for designing and developing project.

## Project Description
1.Visit the website https://dailyfinance.roadtocareer.net/

2.Register User implementing different Scenarios

3.Log in as admin (pass admin credentials from the terminal) and check if the last registered user is displayed on the admin dashboard.

4.Log in with the last registered user and update their profile image.

5.Add a cost/expenditure from a CSV file. Create a CSV file with 5 rows, This test will loop 5 times, as there are 5 data sets in the CSV.

6.Print the total cost and assert it against your expected total sum of the amounts.

7.Search for an item by name from the list and assert that the total cost matches the item's price.

8.Create Regression Suite and Smoke Suite, ANd run them individually.

## Prerequisites
##### • Selenium as automation tool;

##### • TestNG as framework;

##### • IDE like IntelliJ IDEA;

##### • Gradle for dependency management;

##### • Data manipulation: Simple JSON and CSV Parser.

## How to run this project

##### • Clone the project;

##### • Open the project from IntellIJ; 

##### • File>Open>Select and expand folder>Open as project

##### • Command for run smoke and regression suit: gradle clean test -PsuiteName="smokeSuite.xml" & -PsuiteName="regressionSuite.xml"

## Command for generating Allure report:

##### allure generate allure-results --clean -output

##### allure serve allure-results


## Output

### Regression Suite Report

![Allure_Report(Overview)](https://github.com/user-attachments/assets/408e5b85-a246-4cae-b60e-2e8c8d9e0d87)
![Allure_Report(Behaviors)](https://github.com/user-attachments/assets/e60d2c8b-6cc2-4b71-8e8c-8e426e3b830a)

### Project Showcasing of Regression Suite Automation

https://github.com/user-attachments/assets/327f9489-3988-4168-912e-dc116ec5b14b


