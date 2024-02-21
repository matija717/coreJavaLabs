package hr.java.shop.bencic8;

import hr.java.shop.bencic8.production.model.Category;
import hr.java.shop.bencic8.utils.FileUtils;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.util.List;
import java.util.Optional;

import static hr.java.shop.bencic8.utils.FxDialogUtil.showSearchAlert;
import static hr.java.shop.bencic8.utils.InputCheckerUtil.restrictToAlphabeticInput;

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
        restrictToAlphabeticInput(categoryNameTextField);
        restrictToAlphabeticInput(categoryDescriptionTextField);
        categoryNameTableColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getName()));
        categoryDescriptionTableColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getDescription()));
    }

    public void searchCategories() {
        List<Category> categoryList = FileUtils.getCategories();
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
        showSearchAlert(categoryNameTextField.getText(),categoryDescriptionTextField.getText());
    }
}
