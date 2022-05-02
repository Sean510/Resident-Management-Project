package CUS1166Project.Controllers;

import CUS1166Project.Connect;
import CUS1166Project.Models.UserModel;

import java.sql.ResultSet;

public class UserController {
    static Connect con = new Connect();

    //function to check if the user exists in the database
    public static boolean userExists(UserModel model) throws Exception {
        String result = null;

        ResultSet rs = con.st.executeQuery("SELECT username FROM employees WHERE username = '" + model.getUsername() +
                "';"
        );

        while (rs.next()) {
            result = rs.getString("username");
        }

        return result != null;
    }

    //function to get users department
    public static String getDepartment(UserModel model) throws Exception {
        String department = null;

        ResultSet rs = con.st.executeQuery("SELECT department FROM employees WHERE username = '" +
                model.getUsername() + "';"
        );

        while (rs.next()) {
            department = rs.getString("department");
        }

        return department;
    }
}
