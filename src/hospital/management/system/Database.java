package hospital.management.system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    public static final String URL = "jdbc:mysql://localhost:3306/hospital_management_system";
    public static final String USER = "root";
    public static final String Password = "root123!";
    Connection connection;

    Database() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, Password);
            Statement stmt = connection.createStatement();
            //System.out.println("Connected successfully");
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL Driver not found!");
        } catch (SQLException e) {
            System.out.println("Connection failed");
        }
    }
}
