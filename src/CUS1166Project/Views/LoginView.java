package CUS1166Project.Views;

import CUS1166Project.Controllers.UserController;
import CUS1166Project.Models.User;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class LoginView {

    public static void display(Stage stage) {

        //actual window surrounding the scene
        stage.setTitle("SR. Housing");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(8);
        grid.setHgap(10);

        //username label
        Label username = new Label("Username: ");
        GridPane.setConstraints(username,0,0);

        //username textfield
        TextField tfUsername = new TextField();
        tfUsername.setPromptText("username"); // grayed out text prompting users to enter their username
        GridPane.setConstraints(tfUsername,1,0);

        //password label
        Label password = new Label("Password: ");
        GridPane.setConstraints(password,0,1);

        //password textfield
        PasswordField tfPassword = new PasswordField();
        tfPassword.setPromptText("password");
        GridPane.setConstraints(tfPassword,1,1);


        //function to execute login if user presses enter while in username textfield
        tfUsername.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.ENTER){
                try {
                    User user = new User(tfUsername.getText(), tfPassword.getText());
                    if (UserController.validCredentials(user)) {
                        user.setType(UserController.getType(user));
                        switch (user.getType().toLowerCase()) {
                            case "admin" -> AdminMenuView.display(stage, user);
                            case "maintenance" -> MaintenanceMenuView.display(stage, user);
                            case "nurse" -> NurseMenuView.display(stage, user);
//                            case "resident" -> ResidentMenuView.display(stage, user);
                        }
                    } else {
                        UserController.alertInvalid();
                        tfUsername.clear();
                        tfPassword.clear();
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        //function to execute login if user presses enter while in password textfield
        tfPassword.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.ENTER){
                try {
                    User user = new User(tfUsername.getText(), tfPassword.getText());
                    if (UserController.validCredentials(user)) {
                        user.setType(UserController.getType(user));
                        switch (user.getType().toLowerCase()) {
                            case "admin" -> AdminMenuView.display(stage, user);
                            case "maintenance" -> MaintenanceMenuView.display(stage, user);
                            case "nurse" -> NurseMenuView.display(stage, user);
//                            case "resident" -> ResidentMenuView.display(stage, user);
                        }
                    } else {
                        UserController.alertInvalid();
                        tfUsername.clear();
                        tfPassword.clear();
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        //Login button
        Button btLogin = new Button("Login");
        btLogin.setOnAction(e -> {
            try {
                User user = new User(tfUsername.getText(), tfPassword.getText());
                if (UserController.validCredentials(user)) {
                    user.setType(UserController.getType(user));
                    switch (user.getType().toLowerCase()) {
                        case "admin" -> AdminMenuView.display(stage, user);
                        case "maintenance" -> MaintenanceMenuView.display(stage, user);
                        case "nurse" -> NurseMenuView.display(stage, user);
//                            case "resident" -> ResidentMenuView.display(stage, user);
                    }
                } else {
                    UserController.alertInvalid();
                    tfUsername.clear();
                    tfPassword.clear();
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        //sets the layout together
        GridPane.setConstraints(btLogin,1,2);
        grid.getChildren().addAll(username, tfUsername, password, tfPassword, btLogin);

        //casts the window with the scene in it
        Scene scene = new Scene(grid,300,200);
        stage.setScene(scene);
        stage.show();
    }
}
