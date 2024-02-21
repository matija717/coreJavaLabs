package hr.java.shop.bencic8;

import hr.java.shop.bencic8.production.exception.SceneLoadException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

import static hr.java.shop.bencic8.utils.FxSceneUtil.sceneSetter;

public class MenuController {
    public void showItemSearchScreen() {sceneSetter("itemSearchScreen.fxml");}
    public void showCategorySearchScreen(){
        sceneSetter("categorySearchScreen.fxml");
    }
    public void showFactorySearchScreen(){sceneSetter("factorySearchScreen.fxml");}
    public void showStoreSearchScreen(){sceneSetter("storeSearchController.fxml");}
    public void showCategoryAddScreen(){sceneSetter("categoryAddScreen.fxml");}
    public void showItemPickTypeScreen(){sceneSetter("itemTypePickScreen.fxml");}
    public  void showItemAddScreen(){sceneSetter("itemAddScreen.fxml");}
    public void showLaptopAddScreen(){sceneSetter("laptopAddScreen.fxml");}
    public void showBananaAddScreen(){sceneSetter("bananaAddScreen.fxml");}
    public void showKiwiAddScreen(){sceneSetter("kiwiAddScreen.fxml");}
    public void showStoreAddScreen(){sceneSetter("storeAddScreen.fxml");}
    public void showFactoryAddScreen(){sceneSetter("factoryAddScreen.fxml");}
    public void showBuyItemsAddScreen(){sceneSetter("buyItemsFromStoreScreen.fxml");}
}
