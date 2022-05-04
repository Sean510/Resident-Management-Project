package CUS1166Project.Views;

import CUS1166Project.Models.User;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class NurseMenuView {
    public static void display(Stage stage, User user) {
        stage.setTitle("SR. Housing");

        Label username = new Label("User: " + user.getUsername());
        GridPane.setConstraints(username,0,0);
        Label department = new Label("Department: Nursing");
        GridPane.setConstraints(department,1,0);

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(8);
        grid.setHgap(8);

        Label markCompleteLabel = new Label("Mark Request Completed:");
        markCompleteLabel.setUnderline(true);
        GridPane.setConstraints(markCompleteLabel,0,1);

        //MARK REQUEST COMPLETE
        Button btComplete = new Button("Mark Request Completed");
        btComplete.setOnAction(e -> {
            RequestView.displayCompleteRequest(stage, user);
        });
        GridPane.setConstraints(btComplete,0,2);


        Label genLogs = new Label("View Logs:");
        genLogs.setUnderline(true);
        GridPane.setConstraints(genLogs,0,3);

        //CREATE PENDING NURSE REQUEST
        Button btPending = new Button("Pending Nurse Requests");
        btPending.setOnAction(e -> {
            try {
                RequestView.displayPendingRequests(stage, user); //NEED TO UPDATE TO ONLY DISPLAY PENDING NURSING LOGS
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
        GridPane.setConstraints(btPending,0,4);

        //GENERATE NURSE LOGS
        Button btLogs = new Button("Generate Nurse Logs");
        btLogs.setOnAction(e -> {
            //include generate nurse logs
        });
        GridPane.setConstraints(btLogs,0,5);



        Button btLogout = new Button("Log Out");
        btLogout.setOnAction(e ->{
            try {
                LoginView.display(stage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        GridPane.setConstraints(btLogout,0,6);

        grid.getChildren().addAll(username,department,markCompleteLabel,genLogs,btPending,btLogs,btComplete,btLogout);

        Scene scene = new Scene(grid,350,250);
        stage.setScene(scene);
        stage.show();
    }
}
