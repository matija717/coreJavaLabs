package hr.java.shop.bencic7;

import hr.java.shop.bencic7.production.model.Item;
import hr.java.shop.bencic7.production.model.Kupci;
import hr.java.shop.bencic7.production.model.NamedEntity;
import hr.java.shop.bencic7.utils.FileUtils;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BuyersSearchController {
    @FXML
    private TextField buyerNameTextField;
    @FXML
    private TextField buyerSurnameTextfield;
    @FXML
    private TableView<Kupci> kupciTableView;
    @FXML
    private TableColumn<Kupci,String> buyerNameTableColumn;
    @FXML
    private TableColumn<Kupci,String> buyerSurnameTableColumn;
    @FXML
    private TableColumn<Kupci,String> buyerCityAddressTableColumn;
    @FXML
    private TableColumn<Kupci,String> buyerItemsListTableColumn;

    public void initialize(){
        buyerNameTableColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getName()));
        buyerSurnameTableColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getSurname()));
        buyerCityAddressTableColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getAddress().getCity().getCityName()));
        buyerItemsListTableColumn.setCellValueFactory(cellData->
                new SimpleStringProperty(allITems(cellData.getValue().getItemsForBuy())));
    }
    public void searchBuyers(){
        List<Kupci> buyers= FileUtils.kupci();
        if(Optional.ofNullable(buyerNameTextField.getText()).isPresent()){
            buyers=buyers.stream()
                    .filter(b->b.getName().contains(buyerNameTextField.getText()))
                    .toList();
        }
        if(Optional.ofNullable(buyerSurnameTextfield.getText()).isPresent()){
            buyers=buyers.stream()
                    .filter(b->b.getName().contains(buyerSurnameTextfield.getText()))
                    .toList();
        }
        ObservableList<Kupci> kupciObservableList=
                FXCollections.observableArrayList(buyers);
        kupciTableView.setItems(kupciObservableList);
    }
    private String allITems(List<Item> itemString){
        return itemString.stream()
                .map(NamedEntity::getName)
                .collect(Collectors.joining(","));
    }
}
