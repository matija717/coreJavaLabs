package hr.java.shop.bencic8;



import hr.java.shop.bencic8.production.exception.NoItemTypePickError;
import hr.java.shop.bencic8.utils.FileUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;


import java.util.Optional;

import static hr.java.shop.bencic8.utils.FxDialogUtil.getAlert;

public class ItemTypePickScreenController {

    private final MenuController menuController = new MenuController();
    @FXML
    private ChoiceBox<String> itemTypeStringChoiceBox;

    public void initialize() {
        itemTypeStringChoiceBox.setItems(itemTypes());
    }

    public void itemInput() {

        if (Optional.ofNullable(itemTypeStringChoiceBox.getSelectionModel().getSelectedItem()).isPresent()) {
            String type = itemTypeStringChoiceBox.getSelectionModel().getSelectedItem();

            switch (type) {
                case "Item" -> menuController.showItemAddScreen();
                case "Banana" -> menuController.showBananaAddScreen();
                case "Kiwi" -> menuController.showKiwiAddScreen();
                case "Laptop" -> menuController.showLaptopAddScreen();
                default -> throw new NoItemTypePickError();
            }
        } else {
            Alert alert = getAlert(Alert.AlertType.ERROR, "Pogreška kod odabira",
                    "Nije odabrana vrsta itema", "Molimo odaberite jednu od ponuđenih");
            alert.showAndWait();
        }

    }


    private ObservableList<String> itemTypes() {
        return FXCollections.observableArrayList(FileUtils.allItems()
                .stream()
                .map(item -> item.getClass().getSimpleName())
                .distinct()
                .toList());
    }

}
