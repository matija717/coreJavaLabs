package hr.java.shop.bencic7;

import hr.java.shop.bencic7.production.model.Address;
import hr.java.shop.bencic7.production.model.Factory;
import hr.java.shop.bencic7.utils.FileUtils;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.util.List;
import java.util.Optional;

public class FactorySearchController {
    @FXML
    private TextField factoryNameTextField;
    @FXML
    private ChoiceBox<String> cityOfFactoryChoiceBox;
    @FXML
    private TableView<Factory> tableOfFactoriesTableView;
    @FXML
    private TableColumn<Factory,String> factoryNameTableColumn;
    @FXML
    private TableColumn<Factory,String> cityOfFactoryTableColumn;
    @FXML
    private TableColumn<Factory,Integer> numberOfItemsTableColumn;
    public void initialize(){
        factoryNameTableColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getName()));
        cityOfFactoryTableColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getAddress().getCity().getCityName()));

        numberOfItemsTableColumn.setCellValueFactory(cellData -> {
            Integer size = cellData.getValue().getItems().size();
            return Bindings.createIntegerBinding(() -> size).asObject();
        });
        initializeCityChoiceBox();

    }

    private void initializeCityChoiceBox() {
        List<Address> addressList=FileUtils.addressInput();
        ObservableList<String> observableList=FXCollections.observableArrayList(addressList
                .stream()
                .map(a->a.getCity().getCityName())
                .toList());
        cityOfFactoryChoiceBox.setItems(observableList);
    }

    public void searchFactories(){
        List<Factory> factories= FileUtils.allFactories();
        if(Optional.ofNullable(factoryNameTextField.getText()).isPresent()){
            factories=factories.stream()
                    .filter(f->f.getName().contains(factoryNameTextField.getText()))
                    .toList();
        }
        if(Optional.ofNullable(cityOfFactoryChoiceBox.getSelectionModel().getSelectedItem()).isPresent()){
            factories=factories.stream()
                    .filter(f->f.getAddress().getCity().getCityName()
                            .equals(cityOfFactoryChoiceBox.getSelectionModel().getSelectedItem()))
                    .toList();
        }
        ObservableList<Factory> observableList= FXCollections.observableArrayList(factories);
        tableOfFactoriesTableView.setItems(observableList);
    }

}
