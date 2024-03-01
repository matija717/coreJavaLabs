package hr.java.shop.bencic8;

import hr.java.shop.bencic8.production.model.Item;
import hr.java.shop.bencic8.production.model.NamedEntity;
import hr.java.shop.bencic8.production.model.Store;
import hr.java.shop.bencic8.utils.FileUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.util.*;

import static hr.java.shop.bencic8.utils.FileUtils.alertForSucssesfullSaving;
import static hr.java.shop.bencic8.utils.FxDialogUtil.showAddAlert;
import static hr.java.shop.bencic8.utils.FxDialogUtil.showErrorAlert;
import static hr.java.shop.bencic8.utils.InputCheckerUtil.restrictToAlphabeticInput;


public class StoreAddScreenController {
    private ObservableList<String> itemList;
    private Set<Item> storeItems = new LinkedHashSet<>();
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField webAddressTextField;
    @FXML
    private ChoiceBox<String> itemsChoiceBox;

    public void initialize() {
        initializeCategoryChoiceBox();
    }

    private void initializeCategoryChoiceBox() {
        initializeChoiceBox();
        restrictToAlphabeticInput(nameTextField);
        restrictToAlphabeticInput(webAddressTextField);
    }

    private void initializeChoiceBox() {
        List<Item> items = FileUtils.allItems();
        itemList = FXCollections
                .observableArrayList(items
                        .stream()
                        .map(NamedEntity::getName)
                        .toList());
        itemsChoiceBox.setItems(itemList);
    }

    public void saveStore() {
        List<Store> stores = FileUtils.storesInput();

        try {
            Set<Item> currentStoreItems = new LinkedHashSet<>(storeItems);
            String name = nameTextField.getText();
            String webAddress = webAddressTextField.getText();
            if(storeItems.isEmpty()||nameTextField.getText().isEmpty()||webAddressTextField.getText().isEmpty())throw new RuntimeException();
            stores.add(new Store(name, idCalc(stores), webAddress, currentStoreItems));
            FileUtils.saveStores(stores);
            storeItems.clear();
            initializeChoiceBox();
            Alert alert=alertForSucssesfullSaving();
            alert.showAndWait();
        } catch (RuntimeException e) {
            showErrorAlert(StoreAddScreenController.class.getSimpleName());
        }


    }

    private Long idCalc(List<Store> stores) {
        Optional<Long> storeIdOptional = stores.stream()
                .map(NamedEntity::getId)
                .max(Long::compareTo);

        return storeIdOptional.map(aLong -> aLong + 1).orElseGet(() -> 1L);
    }

    public void saveItem() {
        FactoryAddScreenController.savingItems(itemsChoiceBox, storeItems, itemList);
    }

}
