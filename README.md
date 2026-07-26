-- ContactBook
A contact book application built with Java, JDBC, and MySQL, supporting full CRUD (Create, Read, Update, Delete) operations. Built as a practice project to strengthen JDBC-based database integration and layered application architecture.

-- Overview
ContactBook lets users store, view, update, and delete contact records (name, cell number, email) backed by a MySQL database. The project follows a layered architecture separating database access, business logic to keep concerns isolated and the codebase testable.

-- Features
   Create, read, update, and delete contact records
   Lookup contacts by name or cell number
   Combining multiple contact fields
   Centralized exception handling for database operations

--Tech Stack
   Language: Java
   Database: MySQL
   Database Access: JDBC (for learning JDBC fundamentals)

-- Prerequisites
   Java JDK (11+ recommended)
   MySQL Server
   MySQL JDBC Driver (Connector/J) on the classpath

-- Setup
1. Clone the repository:
   git clone https://github.com/buhletech/contactbook.git
   cd contactbook

2.Create the MySQL database and required tables (update with your actual schema/script location):
   CREATE DATABASE contactbook;
      -- run schema.sql or equivalent here

3. Configure your database connection details (update this section to match how the project actually loads credentials e.g. a config file, environment variables, or a DBConnection class).
4. Build and run the project using your preferred method (Maven/Gradle/IDE run configuration, update accordingly).
