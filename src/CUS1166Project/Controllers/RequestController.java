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
import java.sql.ResultSet;

public class RequestController {
    static Connect con = new Connect();

    //method to check if request exists
    public static boolean exists(int id) throws Exception {
        String result = null;
        ResultSet rs = con.st.executeQuery("SELECT id FROM requests WHERE id = " + id + ";");

        while (rs.next()) {
            result = rs.getString("id");
        }

        System.out.println(result);
        return result != null;
    }

    //method to get the next id for creating a new request
    public static int getNewID() throws Exception {
        int result = 0;
        ResultSet rs = con.st.executeQuery("SELECT id FROM requests WHERE id = " +
                "(SELECT MAX(id) FROM requests);"
        );

        while (rs.next()) {
            result = Integer.parseInt(rs.getString("id"));
        }

        return result + 1;
    }

    //method to create a new request
    public static void createRequest(Request model) throws Exception {
        if(ResidentController.idExists(model.getResId())) {
            con.st.executeUpdate("INSERT INTO requests (id, type, resId, dateCreated, description) VALUES ("
                    + model.getId() + ", '" + model.getType() + "', " + model.getResId() + ", '" + model.getDateCreated() +
                    "', '" + model.getDescription() + "');"
            );

            Alert alertRequestCreated = new Alert (
                    Alert.AlertType.NONE,
                    "Request successfully created!",
                    ButtonType.OK
            );
            alertRequestCreated.show();
        } else {
            Alert alertResidentNotExist = new Alert (
                    Alert.AlertType.NONE,
                    "Please make sure you enter a resident that exists.",
                    ButtonType.OK
            );
            alertResidentNotExist.show();
        }
    }

    //method to mark a request as completed
    public static void completeRequest(int id, String date, String username) throws Exception {
        if (exists(id)) {
            con.st.executeUpdate("UPDATE requests SET dateCompleted = '" + date + "', completedBy = '" + username +
                    "' WHERE id = " + id + ";"
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

        TableColumn<Request, Integer> id = new TableColumn<>("Request ID");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Request, String> type = new TableColumn<>("Request Type");
        type.setCellValueFactory(new PropertyValueFactory<>("type"));

        TableColumn<Request, Integer> resId = new TableColumn<>("Resident ID");
        resId.setCellValueFactory(new PropertyValueFactory<>("resId"));

        TableColumn<Request, String> dateCreated = new TableColumn<>("Date Created");
        dateCreated.setCellValueFactory(new PropertyValueFactory<>("dateCreated"));

        TableColumn<Request, String> description = new TableColumn<>("Description");
        description.setCellValueFactory(new PropertyValueFactory<>("description"));

        tableView.getColumns().add(id);
        tableView.getColumns().add(type);
        tableView.getColumns().add(resId);
        tableView.getColumns().add(dateCreated);
        tableView.getColumns().add(description);

        ResultSet rs = con.st.executeQuery("SELECT * FROM requests WHERE dateCompleted IS NULL AND completedBy IS NULL;");

        while(rs.next()) {
            int rId = rs.getInt("id");
            String rType = rs.getString("type");
            int rResId = rs.getInt("resId");
            String rDateCreated = rs.getString("dateCreated");
            String rDescription = rs.getString("description");

            requests.add(new Request(rId, rType, rResId, rDateCreated, rDescription));
        }

        tableView.setItems(requests);

        return tableView;
    }

    //function to generate logs of nurse requests
    public static TableView generateNurseLogs() throws Exception {
        ObservableList<Request> requests = FXCollections.observableArrayList();
        TableView tableView = new TableView();

        TableColumn<Request, Integer> id = new TableColumn<>("Request ID");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Request, String> type = new TableColumn<>("Request Type");
        type.setCellValueFactory(new PropertyValueFactory<>("type"));

        TableColumn<Request, Integer> resId = new TableColumn<>("Resident ID");
        resId.setCellValueFactory(new PropertyValueFactory<>("resId"));

        TableColumn<Request, String> dateCreated = new TableColumn<>("Date Created");
        dateCreated.setCellValueFactory(new PropertyValueFactory<>("dateCreated"));

        TableColumn<Request, String> description = new TableColumn<>("Description");
        description.setCellValueFactory(new PropertyValueFactory<>("description"));

        TableColumn<Request, String> dateCompleted = new TableColumn<>("Date Completed");
        dateCompleted.setCellValueFactory(new PropertyValueFactory<>("dateCompleted"));

        TableColumn<Request, String> completedBy = new TableColumn<>("Completed By");
        completedBy.setCellValueFactory(new PropertyValueFactory<>("completedBy"));

        tableView.getColumns().add(id);
        tableView.getColumns().add(type);
        tableView.getColumns().add(resId);
        tableView.getColumns().add(dateCreated);
        tableView.getColumns().add(description);
        tableView.getColumns().add(dateCompleted);
        tableView.getColumns().add(completedBy);

        ResultSet rs = con.st.executeQuery("SELECT * FROM requests WHERE type = 'nurse' ORDER BY dateCreated;");

        while(rs.next()) {
            int rsID = rs.getInt("id");
            String rsRequestType = rs.getString("type");
            int rsResidentID = rs.getInt("resId");
            String rsDateCreated = rs.getString("dateCreated");
            String rsDescription = rs.getString("description");
            String rsDateCompleted = rs.getString("dateCompleted");
            String rsCompletedBy = rs.getString("completedBy");

            Request request = new Request(rsID, rsRequestType, rsResidentID, rsDateCreated, rsDescription, rsDateCompleted, rsCompletedBy);
            requests.add(request);
        }

        tableView.setItems(requests);

        return tableView;
    }

    //function to generate log of maintenance requests
    public static TableView generateMaintenanceLogs() throws Exception {
        ObservableList<Request> requests = FXCollections.observableArrayList();
        TableView tableView = new TableView();

        TableColumn<Request, Integer> id = new TableColumn<>("Request ID");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Request, String> type = new TableColumn<>("Request Type");
        type.setCellValueFactory(new PropertyValueFactory<>("type"));

        TableColumn<Request, Integer> resId = new TableColumn<>("Resident ID");
        resId.setCellValueFactory(new PropertyValueFactory<>("resId"));

        TableColumn<Request, String> dateCreated = new TableColumn<>("Date Created");
        dateCreated.setCellValueFactory(new PropertyValueFactory<>("dateCreated"));

        TableColumn<Request, String> description = new TableColumn<>("Description");
        description.setCellValueFactory(new PropertyValueFactory<>("description"));

        TableColumn<Request, String> dateCompleted = new TableColumn<>("Date Completed");
        dateCompleted.setCellValueFactory(new PropertyValueFactory<>("dateCompleted"));

        TableColumn<Request, String> completedBy = new TableColumn<>("Completed By");
        completedBy.setCellValueFactory(new PropertyValueFactory<>("completedBy"));

        tableView.getColumns().add(id);
        tableView.getColumns().add(type);
        tableView.getColumns().add(resId);
        tableView.getColumns().add(dateCreated);
        tableView.getColumns().add(description);
        tableView.getColumns().add(dateCompleted);
        tableView.getColumns().add(completedBy);

        ResultSet rs = con.st.executeQuery("SELECT * FROM requests WHERE type = 'maintenance' ORDER BY dateCreated;");

        while(rs.next()) {
            int rsID = rs.getInt("id");
            String rsRequestType = rs.getString("type");
            int rsResidentID = rs.getInt("resId");
            String rsDateCreated = rs.getString("dateCreated");
            String rsDescription = rs.getString("description");
            String rsDateCompleted = rs.getString("dateCompleted");
            String rsCompletedBy = rs.getString("completedBy");

            Request request = new Request(rsID, rsRequestType, rsResidentID, rsDateCreated, rsDescription, rsDateCompleted, rsCompletedBy);
            requests.add(request);
        }

        tableView.setItems(requests);

        return tableView;
    }

    //function to generate logs of all requests
    public static TableView generateLogs() throws Exception {
        ObservableList<Request> requests = FXCollections.observableArrayList();
        TableView tableView = new TableView();

        TableColumn<Request, Integer> id = new TableColumn<>("Request ID");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Request, String> type = new TableColumn<>("Request Type");
        type.setCellValueFactory(new PropertyValueFactory<>("type"));

        TableColumn<Request, Integer> resId = new TableColumn<>("Resident ID");
        resId.setCellValueFactory(new PropertyValueFactory<>("resId"));

        TableColumn<Request, String> dateCreated = new TableColumn<>("Date Created");
        dateCreated.setCellValueFactory(new PropertyValueFactory<>("dateCreated"));

        TableColumn<Request, String> description = new TableColumn<>("Description");
        description.setCellValueFactory(new PropertyValueFactory<>("description"));

        TableColumn<Request, String> dateCompleted = new TableColumn<>("Date Completed");
        dateCompleted.setCellValueFactory(new PropertyValueFactory<>("dateCompleted"));

        TableColumn<Request, String> completedBy = new TableColumn<>("Completed By");
        completedBy.setCellValueFactory(new PropertyValueFactory<>("completedBy"));

        tableView.getColumns().add(id);
        tableView.getColumns().add(type);
        tableView.getColumns().add(resId);
        tableView.getColumns().add(dateCreated);
        tableView.getColumns().add(description);
        tableView.getColumns().add(dateCompleted);
        tableView.getColumns().add(completedBy);

        ResultSet rs = con.st.executeQuery("SELECT * FROM requests ORDER BY dateCreated;");

        while(rs.next()) {
            int rsID = rs.getInt("id");
            String rsRequestType = rs.getString("type");
            int rsResidentID = rs.getInt("resId");
            String rsDateCreated = rs.getString("dateCreated");
            String rsDescription = rs.getString("description");
            String rsDateCompleted = rs.getString("dateCompleted");
            String rsCompletedBy = rs.getString("completedBy");

            Request request = new Request(rsID, rsRequestType, rsResidentID, rsDateCreated, rsDescription, rsDateCompleted, rsCompletedBy);
            requests.add(request);
        }

        tableView.setItems(requests);

        return tableView;
    }
 }
