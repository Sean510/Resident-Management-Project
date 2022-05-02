package CUS1166Project.Controllers;

import CUS1166Project.Connect;
import CUS1166Project.Models.UserModel;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.sql.PreparedStatement;

public class EmployeeController {
    static Connect con = new Connect();

    public static void addEmployee(UserModel model) throws Exception {
        if (UserController.userExists(model)) {
            Alert alertUserExists = new Alert (
                    Alert.AlertType.NONE,
                    "User already exists, please try again.",
                    ButtonType.OK
            );
            alertUserExists.show();
        } else {
            con.st.executeUpdate("INSERT INTO employees VALUES ('" + model.getUsername() + "', '" + model.getPassword() +
                    "', '" + model.getDepartment() + "');"
            );

            Alert alertUserAdded = new Alert (
                    Alert.AlertType.NONE,
                    "User Added!",
                    ButtonType.OK
            );
            alertUserAdded.show();
        }
    }
}
