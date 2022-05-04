package CUS1166Project.Controllers;

import CUS1166Project.Utilities.Connect;
import CUS1166Project.Models.User;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class EmployeeController {
    static Connect con = new Connect();

    //function to add user to database
    public static void addUser(User model) throws Exception {
        if (UserController.userExists(model)) {
            Alert alertUserExists = new Alert (
                    Alert.AlertType.NONE,
                    "User already exists, please try again.",
                    ButtonType.OK
            );
            alertUserExists.show();
        } else {
            con.st.executeUpdate("INSERT INTO users VALUES ('" + model.getUsername() + "', '" +
                    model.getPassword() + "', '" + model.getType() + "');"
            );

            Alert alertUserAdded = new Alert (
                    Alert.AlertType.NONE,
                    "User Added!",
                    ButtonType.OK
            );
            alertUserAdded.show();
        }
    }

    //function to delete user from database
    public static void deleteUser(User model) throws Exception {
        if (!UserController.userExists(model)) {
            Alert alertUserDoesNotExist = new Alert (
                    Alert.AlertType.NONE,
                    "Please enter a user that exists.",
                    ButtonType.OK
            );
            alertUserDoesNotExist.show();
        } else {
            con.st.executeUpdate("DELETE FROM users WHERE username = '" + model.getUsername() + "' AND password = '"
            + model.getPassword() + "';"
            );

            Alert alertUserDeleted = new Alert (
                    Alert.AlertType.NONE,
                    "User Deleted!",
                    ButtonType.OK
            );
            alertUserDeleted.show();
        }
    }

    //function to update an users password in the database
    public static void updatePassword(User model, String newPassword) throws Exception {
        if (!UserController.validCredentials(model)) {
            Alert alertInvalidCredentials = new Alert (
                Alert.AlertType.NONE,
                "Please enter valid credentials.",
                ButtonType.OK
            );
            alertInvalidCredentials.show();
        } else {
            con.st.executeUpdate("UPDATE users SET password = '" + newPassword + "' WHERE username = '" +
                    model.getUsername() + "';"
            );

            Alert alertPasswordUpdated = new Alert (
                    Alert.AlertType.NONE,
                    "This user's password has been changed!",
                    ButtonType.OK
            );
            alertPasswordUpdated.show();
        }
    }
}
