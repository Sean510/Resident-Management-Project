package CUS1166Project.Views;

import CUS1166Project.Models.UserModel;
import com.sun.jdi.connect.Connector;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.Objects;

public class AdminMenuView {

    public static void displayMenu(Stage stage, UserModel user) throws Exception {
        stage.setTitle("SR. Housing");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(8);
        grid.setHgap(10);

        //Login button
        Button btLogout = new Button("Log Out");
        btLogout.setOnAction(e -> {
            try {
                LoginView.displayMenu(stage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        //sets the layout together
        GridPane.setConstraints(btLogout,1,4);

        Button btManageEmployee = new Button("Manage Employee");
        btManageEmployee.setOnAction(e -> {
            try {
                EmployeeView.displayMenu(stage, user);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        //sets the layout together
        GridPane.setConstraints(btManageEmployee,0,0);

        Button btManageResidents = new Button("Manage Resident");
        btManageResidents.setOnAction(e -> {
            try {
//                Residents.displayMain(stage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        //sets the layout together
        GridPane.setConstraints(btManageResidents,1,0);

        Button btQuarantineLists = new Button("Quarantine List");
        btQuarantineLists.setOnAction(e -> {
            try {
//                Quarantine.displayMain(stage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        //sets the layout together
        GridPane.setConstraints(btQuarantineLists,0,1);

        Button btRequests = new Button("Requests");
        btRequests.setOnAction(e -> {
            try {
//                Requests.displayMain(stage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        //sets the layout together
        GridPane.setConstraints(btRequests,1,1);

        Button btMissedMeal = new Button("Missed Meal");
        btMissedMeal.setOnAction(e -> {
            try {
//                MissedMeal.displayMain(stage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        //sets the layout together
        GridPane.setConstraints(btMissedMeal,0,2);

        Button btHospitalList = new Button("Hospital List");
        btHospitalList.setOnAction(e -> {
            try {
//                HospitalList.displayMain(stage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        //sets the layout together
        GridPane.setConstraints(btHospitalList,1,2);


        grid.getChildren().addAll(btManageEmployee, btManageResidents, btQuarantineLists, btRequests,
                btMissedMeal, btHospitalList, btLogout);

        //casts the window with the scene in it
        Scene scene = new Scene(grid,300,200);
        stage.setScene(scene);
        stage.show();

    }
}
