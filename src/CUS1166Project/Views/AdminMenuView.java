package CUS1166Project.Views;

import CUS1166Project.Models.User;
import javafx.css.Style;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Objects;

public class AdminMenuView {

    public static void display(Stage stage, User user) throws Exception {

        GridPane adminMenu = new GridPane();
        adminMenu.setPadding(new Insets(10,10,10,10));
        adminMenu.setVgap(8);
        adminMenu.setHgap(10);

        Label username = new Label("User: " + user.getUsername());
        GridPane.setConstraints(username,0,0);
        Label department = new Label("Department: Administration");
        GridPane.setConstraints(department,1,0);

        Label adminFunctions = new Label("Manage:");
        adminFunctions.setUnderline(true);
        GridPane.setConstraints(adminFunctions,0,1);

        Button btManageUsers = new Button("Users");
        btManageUsers.setOnAction(e -> {
            try {
                UserView.displayMenu(stage, user);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        //sets the layout together
        GridPane.setConstraints(btManageUsers,0,2);

        Button btSearch = new Button("Quick Search \uD83D\uDD0D");
        btSearch.setOnAction(e -> {
            try {
//                Project_1.Views.quickSearch.displayMain(stage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        GridPane.setConstraints(btSearch,4,0);

        Button btManageResidents = new Button("Residents");
        btManageResidents.setOnAction(e -> {
            try {
                ResidentView.displayMenu(stage, user);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        //sets the layout together
        GridPane.setConstraints(btManageResidents,1,2);

        //label for create section
        Label create = new Label("Create:");
        create.setUnderline(true);
        GridPane.setConstraints(create,0,3);

        //button to create request
        Button btCreateRequest = new Button("Request");
        btCreateRequest.setOnAction(e -> {
           try {
               RequestView.displayCreateRequest(stage, user);
           } catch (Exception ex) {
               ex.printStackTrace();
           }
        });
        GridPane.setConstraints(btCreateRequest,0,4);

        //button to create new quarantine
        Button btCreateQuarantine = new Button("Quarantine");
        btCreateQuarantine.setOnAction(e -> {
            try{
                QuarantineView.displayCreate(stage, user);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        GridPane.setConstraints(btCreateQuarantine,1,4);

        //button to create missed meal
        Button btCreateMeal = new Button("Missed Meal");
        btCreateMeal.setOnAction(e -> {
            MissedMealView.addMealMissed(stage, user);
        });
        GridPane.setConstraints(btCreateMeal,2,4);

        //label for things in progress
        Label manageLists = new Label("In Progress:");
        manageLists.setUnderline(true);
        GridPane.setConstraints(manageLists,0,5);

        //button to display pending requests
        Button btPendingRequests = new Button("Requests");
        btPendingRequests.setOnAction(e -> {
            try {
                RequestView.displayPendingRequests(stage, user);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        GridPane.setConstraints(btPendingRequests,0,6);

        //button to display uncontacted residents who missed meals
        Button btUncontacted = new Button("Uncontacted Residents");
        btUncontacted.setOnAction(e -> {
            try {
                MissedMealView.displayUncontacted(stage, user);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        //sets the layout together
        GridPane.setConstraints(btUncontacted,1,6);

        //button to display residents in quarantine
        Button btQuarantines = new Button("Quarantines");
        btQuarantines.setOnAction(e -> {
            try {
                QuarantineView.displayInProgress(stage, user);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        GridPane.setConstraints(btQuarantines,2,6);



        Label markFunctions = new Label("Complete:");
        markFunctions.setUnderline(true);
        GridPane.setConstraints(markFunctions,0,7);

        Button btCompleteRequest = new Button("Request");
        btCompleteRequest.setOnAction(e -> {
            try {
                RequestView.displayCompleteRequest(stage, user);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        //sets the layout together
        GridPane.setConstraints(btCompleteRequest,0,8);

        Button btMarkContacted = new Button("Resident Contacted");
        btMarkContacted.setOnAction(e -> {
            try {
//                MissedMeal.displayMain(stage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        //sets the layout together
        GridPane.setConstraints(btMarkContacted,1,8);



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

        adminMenu.getChildren().addAll(username,department,btSearch,adminFunctions,btManageUsers,btManageResidents,
                create,btCreateRequest,btCreateQuarantine, btCreateMeal,
                manageLists,btPendingRequests,btCompleteRequest,markFunctions,btMarkContacted,btUncontacted, btQuarantines,btLogout
        );

        //casts the window with the scene in it
        Scene scene = new Scene(adminMenu,500,350);
        stage.setScene(scene);
        stage.show();
    }
}
