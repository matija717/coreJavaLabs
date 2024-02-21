package hr.java.shop.bencic7;

import hr.java.shop.bencic7.production.model.Category;
import hr.java.shop.bencic7.production.model.Item;
import hr.java.shop.bencic7.production.model.NamedEntity;
import hr.java.shop.bencic7.utils.FileUtils;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class ItemSearchController {
    @FXML
    private TextField itemNameTextField;
    @FXML
    private ChoiceBox<String> categoryToPick;
    @FXML
    private TableView<Item> itemTableView;
    @FXML
    private TableColumn<Item, String> itemNameTableColumn;
    @FXML
    private TableColumn<Item, String> categoryNameTableColumn;
    @FXML
    private TableColumn<Item, BigDecimal> itemWidthTableColumn;
    @FXML
    private TableColumn<Item, BigDecimal> itemHeightTableColumn;
    @FXML
    private TableColumn<Item, BigDecimal> itemLenghtTableColumn;
    @FXML
    private TableColumn<Item, BigDecimal> itemProductionCostTableColumn;
    @FXML
    private TableColumn<Item, BigDecimal> itemSellingPriceTableColumn;

    public void initialize() {
        itemNameTableColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getName()));
        categoryNameTableColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getObject().getName()));
        itemWidthTableColumn.setCellValueFactory(cellData ->
                new SimpleObjectProperty<>(cellData.getValue().getWidth()));
        itemHeightTableColumn.setCellValueFactory(cellData ->
                new SimpleObjectProperty<>(cellData.getValue().getHeight()));
        itemLenghtTableColumn.setCellValueFactory(cellData ->
                new SimpleObjectProperty<>(cellData.getValue().getLength()));
        itemProductionCostTableColumn.setCellValueFactory(cellData ->
                new SimpleObjectProperty<>(cellData.getValue().getProductionCost()));
        itemSellingPriceTableColumn.setCellValueFactory(cellData ->
                new SimpleObjectProperty<>(cellData.getValue().getSellingPrice()));
        initializeCategoryChoiceBox();
    }

    private void initializeCategoryChoiceBox() {
        List<Category> categories = FileUtils.categoriesViaFileInput();
        ObservableList<String> categoriesList = FXCollections
                .observableArrayList(categories
                        .stream()
                        .map(NamedEntity::getName)
                        .toList());
        categoryToPick.setItems(categoriesList);
    }

    public void searchItems() {
        List<Item> itemList = FileUtils.allItems(FileUtils.categoriesViaFileInput());
        if (Optional.ofNullable(itemNameTextField.getText()).isPresent()) {
            itemList = itemList.stream()
                    .filter(i -> i.getName().contains(itemNameTextField.getText()))
                    .toList();
        }
        if (Optional.ofNullable(categoryToPick.getSelectionModel().getSelectedItem()).isPresent()) {
            itemList = itemList.stream()
                    .filter(i -> i.getObject().getName().equals(categoryToPick.getSelectionModel().getSelectedItem()))
                    .toList();
        }
        ObservableList<Item> observableItemList =
                FXCollections.observableArrayList(itemList);
        itemTableView.setItems(observableItemList);
    }

}