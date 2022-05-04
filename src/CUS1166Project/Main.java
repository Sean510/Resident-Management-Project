package CUS1166Project;

import CUS1166Project.Views.LoginView;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception{
        LoginView.display(stage);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
