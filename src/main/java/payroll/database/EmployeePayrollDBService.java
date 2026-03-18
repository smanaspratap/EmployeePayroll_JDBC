package payroll.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import payroll.model.EmployeePayrollData;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeePayrollDBService {

    public static void main(String[] args) {

        EmployeePayrollDBService employeePayrollDBService =
                new EmployeePayrollDBService();

        System.out.println("Before Update:");

        employeePayrollDBService.readData()
                .forEach(System.out::println);

        employeePayrollDBService.updateEmployeeSalary("Bill", 3000000);

        System.out.println("\nAfter Update:");

        employeePayrollDBService.readData()
                .forEach(System.out::println);

        System.out.println("\nEmployees Joined Between Date Range:");

        employeePayrollDBService
                .getEmployeeByDateRange("2018-01-01", "2024-12-31")
                .forEach(System.out::println);
    }

    private static void listDrivers() {
        Enumeration<java.sql.Driver> driverList = DriverManager.getDrivers();
        while (driverList.hasMoreElements()) {
            java.sql.Driver driverClass = driverList.nextElement();
            System.out.println("Driver: " + driverClass.getClass().getName());
        }
    }

    public List<EmployeePayrollData> getEmployeeByDateRange(String startDate, String endDate) {

        List<EmployeePayrollData> employeePayrollList = new ArrayList<>();

        String query = "SELECT id, name, salary FROM employee_payroll WHERE start BETWEEN ? AND ?";

        try (Connection connection =
                     DriverManager.getConnection(
                             "jdbc:mysql://localhost:3306/payroll_service",
                             "root",
                             "Root@123");

             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setDate(1, Date.valueOf(startDate));
            preparedStatement.setDate(2, Date.valueOf(endDate));

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double salary = resultSet.getDouble("salary");

                employeePayrollList.add(
                        new EmployeePayrollData(id, name, salary)
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employeePayrollList;
    }

    public int updateEmployeeSalary(String name, double salary) {

        String query = "UPDATE employee_payroll SET salary = ? WHERE name = ?";

        try (Connection connection =
                     DriverManager.getConnection(
                             "jdbc:mysql://localhost:3306/payroll_service",
                             "root",
                             "Root@123");

             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setDouble(1, salary);
            preparedStatement.setString(2, name);

            return preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }
    public List<EmployeePayrollData> readData() {

        List<EmployeePayrollData> employeePayrollList = new ArrayList<>();

        String query = "SELECT id, name, salary FROM employee_payroll";

        try (Connection connection =
                     DriverManager.getConnection("jdbc:mysql://localhost:3306/payroll_service","root","Root@123");
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {

                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double salary = resultSet.getDouble("salary");

                EmployeePayrollData employeePayrollData =
                        new EmployeePayrollData(id, name, salary);

                employeePayrollList.add(employeePayrollData);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employeePayrollList;
    }
}
