package CUS1166Project.Controllers;

import CUS1166Project.Models.Quarantine;
import CUS1166Project.Utilities.Connect;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.sql.SQLException;

public class QuarantineController {
    static Connect con = new Connect();

    public static void create(Quarantine q) throws Exception {
        con.st.executeUpdate("INSERT INTO quarantines VALUES (" + q.getResId() + ", '" + q.getDateStart() +
                "', '" + q.getDateEnd() + "');"
        );

        Alert alertCreated = new Alert (
                Alert.AlertType.NONE,
                "New quarantine record added!",
                ButtonType.OK
        );
        alertCreated.show();
    }
}
