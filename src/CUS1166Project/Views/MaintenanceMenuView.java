package CUS1166Project.Views;

import CUS1166Project.Models.User;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MaintenanceMenuView {

    //function to display resident main menu
    public static void display(Stage stage, User user) throws Exception {
        GridPane maintenanceMenu = new GridPane();
        maintenanceMenu.setPadding(new Insets(10,10,10,10));
        maintenanceMenu.setVgap(8);
        maintenanceMenu.setHgap(10);

        Label username = new Label("Welcome, " + user.getUsername() + "!");
        GridPane.setConstraints(username,0,0);

        Label requestFunctions = new Label("Requests:");
        requestFunctions.setUnderline(true);
        GridPane.setConstraints(requestFunctions,0,1);

        Button btPending = new Button("Pending");
        btPending.setOnAction(e -> {
            try {
                RequestView.displayPendingMaintenanceRequests(stage, user);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        //sets the layout together
        GridPane.setConstraints(btPending,0,2);

        Button btComplete = new Button("Complete");
        btComplete.setOnAction(e -> {
            try {
                RequestView.displayCompleteRequestMaintenance(stage,user);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        //sets the layout together
        GridPane.setConstraints(btComplete,1,2);

        Button btLog = new Button("Maintenance Log");
        btLog.setOnAction(e -> {
            try {
                RequestView.displayMaintenanceLog(stage,user);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        GridPane.setConstraints(btLog,0,3);

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

        maintenanceMenu.getChildren().addAll(username,requestFunctions,btPending,btComplete,btLog, btLogout);

        //casts the window with the scene in it
        Scene scene = new Scene(maintenanceMenu,350,225);
        stage.setScene(scene);
        stage.show();
    }
}
