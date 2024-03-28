package hr.java.shop.bencic7.controllers;

import hr.java.shop.bencic7.production.exception.SceneLoadException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

public class MenuController {
    public void showItemSearchScreen() {sceneSetter("/hr/java/shop/bencic7/itemSearchScreen.fxml");}
    public void showCategorySearchScreen(){
        sceneSetter("/hr/java/shop/bencic7/categorySearchScreen.fxml");
    }
    public void showFactorySearchScreen(){sceneSetter("/hr/java/shop/bencic7/factorySearchScreen.fxml");}
    public void showStoreSearchScreen(){sceneSetter("/hr/java/shop/bencic7/storeSearchController.fxml");}
    private void sceneSetter(String name)throws SceneLoadException{
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
}
