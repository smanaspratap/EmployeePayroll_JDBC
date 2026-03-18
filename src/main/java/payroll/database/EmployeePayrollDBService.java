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

        List<EmployeePayrollData> employeePayrollData =
                employeePayrollDBService.readData();

        employeePayrollData.forEach(System.out::println);
    }

    private static void listDrivers() {
        Enumeration<java.sql.Driver> driverList = DriverManager.getDrivers();
        while (driverList.hasMoreElements()) {
            java.sql.Driver driverClass = driverList.nextElement();
            System.out.println("Driver: " + driverClass.getClass().getName());
        }
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
