package CUS1166Project.Views;

import CUS1166Project.Controllers.UserController;
import CUS1166Project.Models.UserModel;
import com.sun.jdi.connect.Connector;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.Objects;

public class LoginView {

    public static void displayMenu(Stage stage) {

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
                UserModel user = new UserModel(tfUsername.getText(), tfPassword.getText());
                try {
                    UserController.userExists(user);
                    user.setDepartment(UserController.getDepartment(user));

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        //function to execute login if user presses enter while in password textfield
        tfPassword.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.ENTER){
                UserModel user = new UserModel(tfUsername.getText(), tfPassword.getText());
                try {
                    UserController.userExists(user);
                    user.setDepartment(UserController.getDepartment(user));

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        //Login button
        Button btLogin = new Button("Login");
        btLogin.setOnAction(e -> {
            UserModel user = new UserModel(tfUsername.getText(), tfPassword.getText());
            try {
                UserController.userExists(user);
                user.setDepartment(UserController.getDepartment(user));
                if(user.getDepartment().toLowerCase().equals("admin")) {
                    AdminMenuView.displayMenu(stage, user);
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
