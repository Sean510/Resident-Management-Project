package CUS1166Project.Controllers;

import CUS1166Project.Models.Request;
import CUS1166Project.Utilities.Connect;
import CUS1166Project.Models.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.ResultSet;

public class UserController {
    static Connect con = new Connect();


    //function to check if the user exists in the database
    public static boolean userExists (User model) throws Exception {
        String result = null;

        ResultSet rs = con.st.executeQuery("SELECT username FROM users WHERE username = '" + model.getUsername() +
                "';"
        );

        while (rs.next()) {
            result = rs.getString("username");
        }

        return result != null;
    }

    //function to get users type
    public static String getType (User model) throws Exception {
        String type = null;

        ResultSet rs = con.st.executeQuery("SELECT type FROM users WHERE username = '" +
                model.getUsername() + "';"
        );

        while (rs.next()) {
            type = rs.getString("type");
        }

        return type;
    }

    public static Boolean validCredentials (User model) throws Exception {
        String result = null;

        ResultSet rs = con.st.executeQuery("SELECT username FROM users WHERE username = '" + model.getUsername() +
                "' AND password = '" + model.getPassword() + "';"
        );

        while (rs.next()) {
            result = rs.getString("username");
        }

        return result != null;
    }

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

    //function to notify user their login credentials are invalid
    public static void alertInvalid() {
        Alert alert = new Alert(
                Alert.AlertType.NONE,
                "Please enter valid credentials!",
                ButtonType.OK
        );
        alert.show();
    }

    //function to generate log of users
    public static TableView generateUsers() throws Exception {
        ObservableList<User> users = FXCollections.observableArrayList();
        TableView tableView = new TableView();

        TableColumn<User, String> username = new TableColumn<>("Username");
        username.setCellValueFactory(new PropertyValueFactory<>("username"));

        TableColumn<User, String> password = new TableColumn<>("Password");
        password.setCellValueFactory(new PropertyValueFactory<>("password"));

        TableColumn<User, String> type = new TableColumn<>("Type");
        type.setCellValueFactory(new PropertyValueFactory<>("type"));

        tableView.getColumns().add(username);
        tableView.getColumns().add(password);
        tableView.getColumns().add(type);

        ResultSet rs = con.st.executeQuery("SELECT * FROM users;");

        while(rs.next()) {
            String uUsername = rs.getString("username");
            String uPassword = rs.getString("password");
            String uType = rs.getString("type");

            User user = new User(uUsername,uPassword,uType);
            users.add(user);
        }

        tableView.setItems(users);

        return tableView;
    }

    //function to get resident id from user info
    public static int getResidentId(User user) throws Exception {
        int result = 0;
        ResultSet rs = con.st.executeQuery("SELECT id FROM residents WHERE username = '" + user.getUsername() + "';");

        while(rs.next()) {
            result = rs.getInt("id");
        }

        return result;
    }
}
