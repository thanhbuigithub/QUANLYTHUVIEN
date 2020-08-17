package control.utilities;

import com.jfoenix.controls.JFXDecorator;
import control.Main;
import control.controllers.MainController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class StageMaker {
    public static void createStage(String loc, String title, double width, double height) throws IOException {
        FXMLLoader loader = new FXMLLoader(MainController.class.getResource(loc));
        Stage stage = new Stage();
        stage.setTitle(title);
        JFXDecorator decorator = new JFXDecorator(stage, loader.load());
        Scene scene = new Scene(decorator, width, height);
        stage.setScene(scene);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(Main.stage);
        stage.showAndWait();
    }
}
