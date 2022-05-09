package CUS1166Project.Views;

import CUS1166Project.Models.User;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class NurseMenuView {

    //function to display main menu for nurse users
    public static void display(Stage stage, User user) throws Exception {

        GridPane nurseMenu = new GridPane();
        nurseMenu.setPadding(new Insets(10,10,10,10));
        nurseMenu.setVgap(8);
        nurseMenu.setHgap(10);

        Label username = new Label("Welcome, " + user.getUsername() + "!");
        GridPane.setConstraints(username,0,0);

        //label for requests section
        Label requestFunctions = new Label("Requests:");
        requestFunctions.setUnderline(true);
        GridPane.setConstraints(requestFunctions,0,1);

        //button to view pending requests
        Button btPending = new Button("Pending");
        btPending.setOnAction(e -> {
            try {
                RequestView.displayPendingNurseRequests(stage, user);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        //sets the layout together
        GridPane.setConstraints(btPending,0,2);


        //button to complete a request
        Button btComplete = new Button("Complete");
        btComplete.setOnAction(e -> {
            try {
                RequestView.displayCompleteRequestNotAdmin(stage,user);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        //sets the layout together
        GridPane.setConstraints(btComplete,1,2);

        //button to view log of requests
        Button btLog = new Button("Nurse Log");
        btLog.setOnAction(e -> {
            try {
                RequestView.displayNurseLogs(stage,user);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        GridPane.setConstraints(btLog,2,2);

        //label for missed meals section
        Label missedMeals = new Label("Missed Meals:");
        missedMeals.setUnderline(true);
        GridPane.setConstraints(missedMeals,0,3);

        //button to add a new missed meal
        Button btAddMissedMeal = new Button("Add New");
        btAddMissedMeal.setOnAction(e -> {
            try {
                MissedMealView.addMealMissed(stage, user);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        GridPane.setConstraints(btAddMissedMeal,0,4);

        //button to view uncontacted residents
        Button btUncontactedResidents = new Button("Uncontacted Residents");
        btUncontactedResidents.setOnAction(e -> {
            try{
                MissedMealView.displayUncontacted(stage, user);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        GridPane.setConstraints(btUncontactedResidents,1,4);

        //button to generate log of missed meals
        Button btMealLog = new Button("Missed Meal Log");
        btMealLog.setOnAction(e -> {
            try {
                MissedMealView.displayLogs(stage, user);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        GridPane.setConstraints(btMealLog,2,4);

        //label for quarantines section
        Label quarantines = new Label("Quarantines:");
        quarantines.setUnderline(true);
        GridPane.setConstraints(quarantines,0,5);

        //button to add a new quarantine record
        Button btAddQuarantine = new Button("Add");
        btAddQuarantine.setOnAction(e -> {
            try {
                QuarantineView.displayCreate(stage, user);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        GridPane.setConstraints(btAddQuarantine,0,6);

        //button to display quarantines in progress
        Button btCurrentQuarantines = new Button("In Progress");
        btCurrentQuarantines.setOnAction(e -> {
            try {
                QuarantineView.displayInProgress(stage, user);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        //sets the layout together
        GridPane.setConstraints(btCurrentQuarantines,1,6);

        //button to display quarantine logs
        Button btQuarantineLog = new Button("Quarantine Log");
        btQuarantineLog.setOnAction(e -> {
            try {
                QuarantineView.displayLogs(stage,user);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        GridPane.setConstraints(btQuarantineLog,2,6);


        //Login button
        Button btLogout = new Button("Log Out");
        btLogout.setOnAction(e -> {
            try {
                LoginView.display(stage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        //sets the layout together
        GridPane.setConstraints(btLogout,0,12);

        nurseMenu.getChildren().addAll(username,requestFunctions,btPending,btComplete,btLog,
                missedMeals,btAddMissedMeal,btUncontactedResidents,btMealLog,
                quarantines,btAddQuarantine,btCurrentQuarantines,btQuarantineLog,
                btLogout
        );

        //casts the window with the scene in it
        Scene scene = new Scene(nurseMenu,500,300);
        stage.setScene(scene);
        stage.show();
    }
}
