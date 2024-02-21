package hr.java.shop.bencic7;

import hr.java.shop.bencic7.production.model.Store;
import hr.java.shop.bencic7.utils.FileUtils;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.util.List;
import java.util.Optional;

public class StoreSearchController {
    @FXML
    private TextField storeNameTextField;
    @FXML
    private TextField storeWebAddressTextField;
    @FXML
    private TableView<Store> tableStoreTableView;
    @FXML
    private TableColumn<Store, String> storeNameTableView;
    @FXML
    private TableColumn<Store, String> storeWebAddressTableView;

    public void initialize() {
        storeNameTableView.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getName()));
        storeWebAddressTableView.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getWebAddress()));
    }

    public void searchStores() {
        List<Store> stores = FileUtils.storesInput();
        if (Optional.ofNullable(storeNameTextField.getText()).isPresent()) {
            stores = stores.stream()
                    .filter(s -> s.getName().contains(storeNameTextField.getText()))
                    .toList();
        }
        if (Optional.ofNullable(storeWebAddressTextField.getText()).isPresent()) {
            stores = stores.stream()
                    .filter(s -> s.getWebAddress().contains(storeWebAddressTextField.getText()))
                    .toList();
        }
        ObservableList<Store> storeObservableList =
                FXCollections.observableArrayList(stores);
        tableStoreTableView.setItems(storeObservableList);

    }
}
