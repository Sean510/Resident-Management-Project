package CUS1166Project.Utilities;
import java.sql.*;

public class Connect {
    public static Connection con;
    public static Statement st;

    public Connect() {
        connect();
    }

    public static void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver Loaded");
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/seniorhousing", "root", "FWnShPG/dzS+6g^");
            System.out.println("Connection Successful");
            st = con.createStatement();
            System.out.println("Statement Created");
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e);
        }
    }
}

