package hr.java.shop.bencic8;

import hr.java.shop.bencic8.production.model.*;
import hr.java.shop.bencic8.utils.FileUtils;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.util.List;

import static hr.java.shop.bencic8.utils.FxDialogUtil.getAlert;
import static hr.java.shop.bencic8.utils.FxDialogUtil.showErrorAlert;
import static hr.java.shop.bencic8.utils.FxSceneUtil.backToItemTypeAfterInput;
import static hr.java.shop.bencic8.utils.InputCheckerUtil.*;
import static hr.java.shop.bencic8.utils.InputUtil.itemDataSoak;

public class LaptopAddScreenController {
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
    @FXML
    private TextField warnityTextField;

    public void initialize() {
        itemInputInitialization(categoryChoiceBox, heightTextField, lengthTextField, widthTextField,
                productionCostTextField, sellingPriceTextField, discountAmountTextField, nameTextField);
        restrictToIntegerInput(warnityTextField);
    }

    protected static void itemInputInitialization(ChoiceBox<String> categoryChoiceBox, TextField heightTextField, TextField lengthTextField, TextField widthTextField, TextField productionCostTextField, TextField sellingPriceTextField, TextField discountAmountTextField, TextField nameTextField) {
        categoryChoiceBox.setItems(FXCollections.observableArrayList(FileUtils.getCategories()
                .stream()
                .map(NamedEntity::getName)
                .toList()));
        restrictToBigDecimalInput(heightTextField);
        restrictToBigDecimalInput(lengthTextField);
        restrictToBigDecimalInput(widthTextField);
        restrictToBigDecimalInput(productionCostTextField);
        restrictToBigDecimalInput(sellingPriceTextField);
        restrictToBigDecimalInput(discountAmountTextField);
        restrictToAlphabeticInput(nameTextField);
    }

    public void saveLaptop() {
        List<Item> items = FileUtils.allItems();
        try {
            Item item = itemDataSoak(categoryChoiceBox.getSelectionModel().getSelectedItem(), nameTextField, heightTextField
                    , lengthTextField, widthTextField, productionCostTextField, sellingPriceTextField, discountAmountTextField);
            items.add(new Laptop(item.getName()
                    , item.getId()
                    , item.getObject()
                    , item.getWidth()
                    , item.getHeight()
                    , item.getLength()
                    , item.getProductionCost()
                    , item.getSellingPrice()
                    , item.getDiscountAmount()
                    , Integer.parseInt(warnityTextField.getText())));
            FileUtils.saveLaptops(items);
        } catch (RuntimeException e) {
            showErrorAlert(LaptopAddScreenController.class.getSimpleName());
        }
        backToItemTypeAfterInput();
    }
}
