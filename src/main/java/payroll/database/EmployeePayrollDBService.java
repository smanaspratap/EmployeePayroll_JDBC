package payroll.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

public class EmployeePayrollDBService {

    public static void main(String[] args) {

        String jdbcURL = "jdbc:mysql://localhost:3306/payroll_service";
        String username = "root";
        String password = "Root@123";

        Connection connection;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded!");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Cannot find the driver in classpath!", e);
        }

        listDrivers();

        try {
            System.out.println("Connecting to database: " + jdbcURL);
            connection = DriverManager.getConnection(jdbcURL, username, password);
            System.out.println("Connection successful: " + connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void listDrivers() {
        Enumeration<java.sql.Driver> driverList = DriverManager.getDrivers();
        while (driverList.hasMoreElements()) {
            java.sql.Driver driverClass = driverList.nextElement();
            System.out.println("Driver: " + driverClass.getClass().getName());
        }
    }
}
