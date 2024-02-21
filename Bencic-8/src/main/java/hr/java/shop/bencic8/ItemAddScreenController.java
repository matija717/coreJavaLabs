package hr.java.shop.bencic8;


import hr.java.shop.bencic8.production.model.Item;

import hr.java.shop.bencic8.utils.FileUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


import java.util.List;


import static hr.java.shop.bencic8.utils.FxDialogUtil.getAlert;
import static hr.java.shop.bencic8.utils.FxDialogUtil.showErrorAlert;
import static hr.java.shop.bencic8.utils.FxSceneUtil.backToItemTypeAfterInput;
import static hr.java.shop.bencic8.utils.InputUtil.itemDataSoak;

public class ItemAddScreenController {
    @FXML
    private Label typeOfItemInputLabel;
    @FXML
    private TextField nameTextField;
    @FXML
    private ChoiceBox<String> categoryChoiceBox;
    @FXML
    private TextField heightTextField;
    @FXML
    private TextField lengthTextField;
    @FXML
    private TextField widthTextField;
    @FXML
    private TextField productionCostTextField;
    @FXML
    private TextField sellingPriceTextField;
    @FXML
    private TextField discountAmountTextField;

    public void initialize() {
        typeOfItemInputLabel.setText("Item input");
        LaptopAddScreenController.itemInputInitialization(categoryChoiceBox, heightTextField, lengthTextField,
                widthTextField, productionCostTextField, sellingPriceTextField, discountAmountTextField, nameTextField);
    }

    public void saveItem() {
        List<Item> items = FileUtils.allItems();
        try {
            items.add(itemDataSoak(categoryChoiceBox.getSelectionModel().getSelectedItem(), nameTextField, heightTextField
                    , lengthTextField, widthTextField, productionCostTextField,
                    sellingPriceTextField, discountAmountTextField));
            FileUtils.saveItems(items);
            initialize();
        } catch (RuntimeException e) {
            showErrorAlert(ItemAddScreenController.class.getSimpleName());
        }
        backToItemTypeAfterInput();
    }
}
