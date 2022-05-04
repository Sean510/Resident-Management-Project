package CUS1166Project.Controllers;

import CUS1166Project.Models.Request;
import CUS1166Project.Utilities.Connect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.FileOutputStream;
import java.sql.ResultSet;

public class RequestController {
    static Connect con = new Connect();

    //method to check if request exists
    public static boolean exists(int id) throws Exception {
        String result = null;
        ResultSet rs = con.st.executeQuery("SELECT requestID FROM requests WHERE requestID = " + id + ";");

        while (rs.next()) {
            result = rs.getString("requestID");
        }

        System.out.println(result);
        return result != null;
    }

    //method to get the next id for creating a new request
    public static int getNewID() throws Exception {
        int result = 0;
        ResultSet rs = con.st.executeQuery("SELECT requestID FROM requests WHERE requestID = " +
                "(SELECT MAX(requestID) FROM requests);"
        );

        while (rs.next()) {
            result = Integer.parseInt(rs.getString("requestID"));
        }

        return result + 1;
    }

    //method to create a new request
    public static void createRequest(Request model) throws Exception {
        con.st.executeUpdate("INSERT INTO requests (requestID, requestType, residentID, dateCreated, description) VALUES ("
            + model.getID() + ", '" + model.getType() + "', " + model.getResidentID() + ", '" + model.getDateCreated() +
                "', '" + model.getDescription() + "');"
        );

        Alert alertRequestCreated = new Alert (
                Alert.AlertType.NONE,
                "Request successfully created!",
                ButtonType.OK
        );
        alertRequestCreated.show();
    }

    //method to mark a request as completed
    public static void completeRequest(int id, String date, int employeeID) throws Exception {
        if (exists(id)) {
            con.st.executeUpdate("UPDATE requests SET dateCompleted = '" + date + "', completedBy = " + employeeID +
                    " WHERE requestID = " + id + ";"
            );

            Alert alertRequestComplete = new Alert(
                    Alert.AlertType.NONE,
                    "This request has been marked complete!",
                    ButtonType.OK
            );
            alertRequestComplete.show();
        } else {
            Alert alertRequestNotExist = new Alert (
                    Alert.AlertType.NONE,
                    "Please make sure this request exists.",
                    ButtonType.OK
            );
            alertRequestNotExist.show();
        }
    }

    //method returns a tableview of pending requests
    public static TableView generatePendingRequests() throws Exception {
        ObservableList<Request> requests = FXCollections.observableArrayList();
        TableView tableView = new TableView();

        TableColumn<Request, Integer> ID = new TableColumn<>("Request ID");
        ID.setCellValueFactory(new PropertyValueFactory<>("ID"));

        TableColumn<Request, String> type = new TableColumn<>("Request Type");
        type.setCellValueFactory(new PropertyValueFactory<>("type"));

        TableColumn<Request, Integer> residentID = new TableColumn<>("Resident ID");
        residentID.setCellValueFactory(new PropertyValueFactory<>("residentID"));

        TableColumn<Request, String> dateCreated = new TableColumn<>("Date Created");
        dateCreated.setCellValueFactory(new PropertyValueFactory<>("dateCreated"));

        TableColumn<Request, String> description = new TableColumn<>("Description");
        description.setCellValueFactory(new PropertyValueFactory<>("description"));

        tableView.getColumns().add(ID);
        tableView.getColumns().add(type);
        tableView.getColumns().add(residentID);
        tableView.getColumns().add(dateCreated);
        tableView.getColumns().add(description);

        ResultSet rs = con.st.executeQuery("SELECT * FROM requests WHERE dateCompleted IS NULL AND completedBy IS NULL;");

        while(rs.next()) {
            int rsID = rs.getInt("requestID");
            String rsRequestType = rs.getString("requestType");
            int rsResidentID = rs.getInt("residentID");
            String rsDateCreated = rs.getString("dateCreated");
            String rsDescription = rs.getString("description");

            Request request = new Request(rsID, rsRequestType, rsResidentID, rsDateCreated, rsDescription);
            requests.add(request);
        }

        tableView.setItems(requests);

        return tableView;
    }

    public static TableView generateNurseLogs() throws Exception {
        ObservableList<Request> requests = FXCollections.observableArrayList();
        TableView tableView = new TableView();

        TableColumn<Request, Integer> ID = new TableColumn<>("Request ID");
        ID.setCellValueFactory(new PropertyValueFactory<>("ID"));

        TableColumn<Request, String> type = new TableColumn<>("Request Type");
        type.setCellValueFactory(new PropertyValueFactory<>("type"));

        TableColumn<Request, Integer> residentID = new TableColumn<>("Resident ID");
        residentID.setCellValueFactory(new PropertyValueFactory<>("residentID"));

        TableColumn<Request, String> dateCreated = new TableColumn<>("Date Created");
        dateCreated.setCellValueFactory(new PropertyValueFactory<>("dateCreated"));

        TableColumn<Request, String> description = new TableColumn<>("Description");
        description.setCellValueFactory(new PropertyValueFactory<>("description"));

        TableColumn<Request, String> dateCompleted = new TableColumn<>("Date Completed");
        description.setCellValueFactory(new PropertyValueFactory<>("dateCompleted"));

        TableColumn<Request, String> completedBy = new TableColumn<>("Completed By");
        description.setCellValueFactory(new PropertyValueFactory<>("completedBy"));

        tableView.getColumns().add(ID);
        tableView.getColumns().add(type);
        tableView.getColumns().add(residentID);
        tableView.getColumns().add(dateCreated);
        tableView.getColumns().add(description);
        tableView.getColumns().add(dateCompleted);
        tableView.getColumns().add(completedBy);

        ResultSet rs = con.st.executeQuery("SELECT * FROM requests WHERE requestType = 'Nurse' AND completedBy is not null;");

        while(rs.next()) {
            int rsID = rs.getInt("requestID");
            String rsRequestType = rs.getString("requestType");
            int rsResidentID = rs.getInt("residentID");
            String rsDateCreated = rs.getString("dateCreated");
            String rsDescription = rs.getString("description");
            String rsDateCompleted = rs.getString("dateCompleted");
            int rsCompletedBy = rs.getInt("completedBy");

            Request request = new Request(rsID, rsRequestType, rsResidentID, rsDateCreated, rsDescription, rsDateCompleted, rsCompletedBy);
            requests.add(request);
        }

        tableView.setItems(requests);

        return tableView;
    }

 }
