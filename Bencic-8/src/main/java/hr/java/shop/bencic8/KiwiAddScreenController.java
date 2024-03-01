package hr.java.shop.bencic8;


import hr.java.shop.bencic8.production.model.*;
import hr.java.shop.bencic8.utils.FileUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.math.BigDecimal;
import java.util.List;


import static hr.java.shop.bencic8.utils.FxDialogUtil.getAlert;
import static hr.java.shop.bencic8.utils.FxDialogUtil.showErrorAlert;
import static hr.java.shop.bencic8.utils.FxSceneUtil.backToItemTypeAfterInput;
import static hr.java.shop.bencic8.utils.InputCheckerUtil.restrictToBigDecimalInput;
import static hr.java.shop.bencic8.utils.InputUtil.*;

public class KiwiAddScreenController {
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
    private TextField weightTextField;

    public void initialize() {
        LaptopAddScreenController.itemInputInitialization(categoryChoiceBox, heightTextField, lengthTextField, widthTextField,
                productionCostTextField, sellingPriceTextField, discountAmountTextField, nameTextField);
        restrictToBigDecimalInput(weightTextField);
    }

    public void saveKiwi() {
        List<Item> items = FileUtils.allItems();
        try {
            Item item = itemDataSoak(categoryChoiceBox.getSelectionModel().getSelectedItem(), nameTextField, heightTextField
                    , lengthTextField, widthTextField, productionCostTextField, sellingPriceTextField, discountAmountTextField);
            BigDecimal weight = new BigDecimal(weightTextField.getText());
            items.add(new Kiwi(item.getName()
                    , item.getId()
                    , item.getObject()
                    , item.getWidth()
                    , item.getHeight()
                    , item.getLength()
                    , item.getProductionCost()
                    , item.getSellingPrice()
                    , item.getDiscountAmount()
                    , weight));
            FileUtils.saveKiwis(items);
        } catch (RuntimeException e) {
            showErrorAlert(KiwiAddScreenController.class.getSimpleName());
        }
        backToItemTypeAfterInput();
    }
}

