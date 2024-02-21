package hr.java.shop.bencic7;

import hr.java.shop.bencic7.production.exception.SceneLoadException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

public class MenuController {
    public void showItemSearchScreen() {sceneSetter("itemSearchScreen.fxml");}
    public void showCategorySearchScreen(){
        sceneSetter("categorySearchScreen.fxml");
    }
    public void showFactorySearchScreen(){sceneSetter("factorySearchScreen.fxml");}
    public void showStoreSearchScreen(){sceneSetter("storeSearchController.fxml");}
    public void showBuyersSearchScreen(){sceneSetter("buyersSearchController.fxml");}
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
