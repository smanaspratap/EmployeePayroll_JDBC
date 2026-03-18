# Employee Payroll JDBC Application

## Project Description
This project demonstrates how to connect a **Java application to a MySQL database using JDBC** and perform payroll related database operations.

The application connects to the **payroll_service database** and performs various operations such as retrieving employee payroll data, updating salary, retrieving employees by date range, calculating aggregate statistics, and handling database transactions.

---

# Technologies Used

- Java
- JDBC (Java Database Connectivity)
- MySQL
- Maven
- IntelliJ IDEA
- Git
- GitHub
- Git Flow

---

# Project Structure

```
Employee-Payroll-JDBC
│
├── src/main/java
│   └── payroll
│       ├── database
│       │     └── EmployeePayrollDBService.java
│       │
│       ├── model
│       │     └── EmployeePayrollData.java
│       │
│       ├── service
│       │
│       └── exception
│
├── pom.xml
└── README.md
```

---

# Database Used

Database Name:

```
payroll_service
```

Main Table:

```
employee_payroll
```

Columns used:

- id
- name
- gender
- salary
- start

---

# Maven Dependency

MySQL JDBC Driver used in this project:

```xml
<dependency>
    <groupId>com.mysql</groupId>
    <artifactId>mysql-connector-j</artifactId>
    <version>8.4.0</version>
</dependency>
```

---

# Use Cases Implemented

## UC1 – Establish Database Connection
- Loaded MySQL JDBC driver.
- Connected Java application to MySQL database.
- Verified successful connection.

Example:

```
jdbc:mysql://localhost:3306/payroll_service
```

---

## UC2 – Retrieve Employee Payroll Data
- Executed SELECT query using JDBC.
- Retrieved employee payroll data from database.
- Stored results in `EmployeePayrollData` objects.
- Returned results as `List<EmployeePayrollData>`.

---

## UC3 – Update Employee Salary
- Updated employee salary in database using JDBC.
- Used `PreparedStatement` to safely update records.

Example query:

```
UPDATE employee_payroll SET salary = ? WHERE name = ?
```

---

## UC4 – Retrieve Employees by Date Range
- Retrieved employees based on their joining date.
- Used `PreparedStatement` with date parameters.

Example query:

```
SELECT * FROM employee_payroll
WHERE start BETWEEN ? AND ?
```

---

## UC5 – Payroll Statistics by Gender
Implemented SQL aggregate functions using JDBC:

- SUM
- AVG
- MIN
- MAX
- COUNT

Example query:

```
SELECT gender,
       SUM(salary),
       AVG(salary),
       MIN(salary),
       MAX(salary),
       COUNT(*)
FROM employee_payroll
GROUP BY gender;
```

---

## UC6 – Database Transaction Handling
Implemented database transactions to ensure data consistency.

Used:

```
setAutoCommit(false)
commit()
rollback()
```

Transaction flow:

```
Start Transaction
      ↓
Execute Query
      ↓
Commit if successful
      ↓
Rollback if error occurs
```

---

# Key JDBC Concepts Used

- JDBC Driver
- Connection
- Statement
- PreparedStatement
- ResultSet
- Transactions (Commit / Rollback)

---

# Learning Outcomes

Through this project the following concepts were learned:

- Java JDBC connectivity
- Executing SQL queries from Java
- Handling query results using ResultSet
- Using PreparedStatement for secure queries
- Implementing database transactions
- Designing layered project structure
- Integrating MySQL with Java applications

---
