# Spam Detection

##Overview
The Spam Detection System is a backend application built with Spring Boot that enables spam detection and user management. It provides secure authentication using JWT, ensures robust API security with Spring Security, and leverages Spring Data JPA for efficient database operations.

##Key Features
-✔ User Authentication & Authorization – Secure login and registration using JWT-based authentication.
-✔ Spam Reporting – Users can report phone numbers as spam, contributing to a database of flagged numbers.
-✔ User Management – Supports profile updates and search functionality based on names and phone numbers.
-✔ RESTful API Design – Well-structured API endpoints for seamless integration.
-✔ MySQL Database Integration – Persistent storage of user data and spam reports.
-✔ Automated Database Population – Uses SQL scripts to initialize the database with sample data.

## Technologies Used

- **Java 17**
- **Spring Boot** (Version 3.4.1)
- **Spring Data JPA** (for database management)
- **Spring Security** (for securing APIs)
- **MySQL** (for relational database persistence)
- **JWT** (for authentication)

## Requirements

- Java 17
- Maven 3.6+
- MySQL Database

## Setup Instructions
- Configuring Database - Install mySql in your system and run the query
  - create schema 'spam_detection'
- In application.properties
    - add mysql username and password
- run the application 
- data.sql script will automatically populate the database with random data

## Api Available
- http://localhost:8080/api/register
  - This is to register new users. It is a post query and require name, phone number, email, password. Does not require auth
- http://localhost:8080/auth/login
    - This is to login and generate a token. Post query and requires phone number and password. Does not require auth
- http://localhost:8080/api/update
  - This is to update information for existing user. This is put query and require name and email to be updated. Extracts detail from user token
- http://localhost:8080/search/searchByName
    - To search users by name
- http://localhost:8080/search/searchByNumber
    - To search global contacts by phone number
- http://localhost:8080/search/displayProfile
    - To display profile when searched and clicked
- http://localhost:8080/spam/report
    - To report a phone number as spam
