package hr.java.shop.bencic7;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ProductionApplication extends Application {
    private static Stage mainStage;
    @Override
    public void start(Stage stage) throws IOException {
        mainStage=stage;
        FXMLLoader fxmlLoader = new FXMLLoader(ProductionApplication.class.getResource("menu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Production application!");
        stage.setScene(scene);
        stage.show();
    }
    public static Stage getMainStage(){
        return mainStage;
    }

    public static void main(String[] args) {
        launch();
    }
}