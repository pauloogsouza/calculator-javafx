package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception{
        Parent parent = FXMLLoader.load(getClass().getResource("/gui/View.fxml"));
        Scene scene = new Scene(parent);
        stage.setTitle("Calculator");
        stage.setResizable(false);
        Image image = new Image("resources/icons/calculator-icon.png");
        stage.getIcons().add(image);
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}