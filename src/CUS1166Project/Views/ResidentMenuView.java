package CUS1166Project.Views;

import CUS1166Project.Controllers.ResidentController;
import CUS1166Project.Controllers.UserController;
import CUS1166Project.Models.User;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ResidentMenuView {

    //function to display resident main menu
    public static void display(Stage stage, User user) throws Exception {
        GridPane residentMenu = new GridPane();
        residentMenu.setPadding(new Insets(10,10,10,10));
        residentMenu.setVgap(8);
        residentMenu.setHgap(10);

        Label username = new Label("Welcome, " + user.getUsername() + "!");
        GridPane.setConstraints(username,0,0);

        Label requestFunctions = new Label("Requests:");
        requestFunctions.setUnderline(true);
        GridPane.setConstraints(requestFunctions,0,1);

        Button btCreateRequest = new Button("Create");
        btCreateRequest.setOnAction(e -> {
            try {
                RequestView.displayCreateRequestResident(stage, user);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        //sets the layout together
        GridPane.setConstraints(btCreateRequest,0,2);

        Button btViewPendingRequests = new Button("View Pending");
        btViewPendingRequests.setOnAction(e -> {
            try {
                RequestView.displayPendingRequestsResident(stage,user);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        //sets the layout together
        GridPane.setConstraints(btViewPendingRequests,1,2);

        //label for create section
        Label update = new Label("Update:");
        update.setUnderline(true);
        GridPane.setConstraints(update,0,3);

        //button to update emergency contact
        Button btUpdateInformation = new Button("Information");
        btUpdateInformation.setOnAction(e -> {
            try {
                ResidentView.displayUpdateResidentDetails(stage,user,
                        ResidentController.getResidentDetails(UserController.getResidentId(user))
                );
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        GridPane.setConstraints(btUpdateInformation,0,4);

        //Logout button
        Button btLogout = new Button("Log Out");
        btLogout.setOnAction(e -> {
            try {
                LoginView.display(stage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        //sets the layout together
        GridPane.setConstraints(btLogout,0,8);

        residentMenu.getChildren().addAll(username,requestFunctions,btCreateRequest,btViewPendingRequests,
                btUpdateInformation, update, btLogout
        );

        //casts the window with the scene in it
        Scene scene = new Scene(residentMenu,350,225);
        stage.setScene(scene);
        stage.show();
    }

}
