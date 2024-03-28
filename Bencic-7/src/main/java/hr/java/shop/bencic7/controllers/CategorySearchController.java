package hr.java.shop.bencic7.controllers;

import hr.java.shop.bencic7.production.model.Category;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.util.List;
import java.util.Optional;

import static hr.java.shop.bencic7.utils.CategoriesAndItemsInputUtil.categoriesViaFileInput;

public class CategorySearchController {
    @FXML
    private TextField categoryNameTextField;
    @FXML
    private TextField categoryDescriptionTextField;
    @FXML
    private TableView<Category> categoryTableView;
    @FXML
    private TableColumn<Category, String> categoryNameTableColumn;
    @FXML
    private TableColumn<Category, String> categoryDescriptionTableColumn;

    public void initialize() {
        categoryNameTableColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getName()));
        categoryDescriptionTableColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getDescription()));
    }

    public void searchCategories() {
        List<Category> categoryList = categoriesViaFileInput();
        if (Optional.ofNullable(categoryNameTextField.getText()).isPresent()) {
            categoryList = categoryList.stream()
                    .filter(c -> c.getName().contains(categoryNameTextField.getText()))
                    .toList();
        }
        if (Optional.ofNullable(categoryDescriptionTextField.getText()).isPresent()) {
            categoryList = categoryList.stream()
                    .filter(c -> c.getDescription().contains(categoryDescriptionTextField.getText()))
                    .toList();
        }
        ObservableList<Category> categoryObservableList =
                FXCollections.observableArrayList(categoryList);
        categoryTableView.setItems(categoryObservableList);
    }
}
