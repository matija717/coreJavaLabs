package hr.java.shop.bencic8.utils;

import hr.java.shop.bencic8.ProductionApplication;
import hr.java.shop.bencic8.production.exception.SceneLoadException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

public class FxSceneUtil {
    public static  void sceneSetter(String name)throws SceneLoadException {
        FXMLLoader fxmlLoader = new FXMLLoader(ProductionApplication.class.getResource(name));
        Scene scene;
        try {
            scene = new Scene(fxmlLoader.load(), 600, 400);
        } catch (IOException e) {
            throw new SceneLoadException(e.getMessage());
        }
        ProductionApplication.getMainStage().setTitle("Production application!");
        ProductionApplication.getMainStage().setScene(scene);
        ProductionApplication.getMainStage().show();
    }
    public static void backToItemTypeAfterInput(){sceneSetter("itemTypePickScreen.fxml");}
}
