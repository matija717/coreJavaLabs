package hr.java.shop.bencic8;

import hr.java.shop.bencic8.production.model.Category;
import hr.java.shop.bencic8.production.model.NamedEntity;
import hr.java.shop.bencic8.utils.FileUtils;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;


import java.util.List;
import java.util.Optional;

import static hr.java.shop.bencic8.utils.FxDialogUtil.showErrorAlert;
import static hr.java.shop.bencic8.utils.InputCheckerUtil.restrictToAlphabeticInput;

public class CategoryAddScreenController {
    @FXML
    private TextField categoryNameTextField;
    @FXML
    private TextField categoryDescriptionTextField;

    public void initialize() {
        restrictToAlphabeticInput(categoryNameTextField);
        restrictToAlphabeticInput(categoryDescriptionTextField);
    }

    public void addNewCategory() {
        List<Category> categories = FileUtils.getCategories();
        try{
        String categorieName = categoryNameTextField.getText();
        String categoryDescription = categoryDescriptionTextField.getText();
        if(categorieName.isEmpty()||categoryDescription.isEmpty()) throw new RuntimeException();
        categories.add(new Category(categoryDescription, calculateID(categories), categorieName));
        FileUtils.saveCategories(categories);}
        catch (RuntimeException e){
            showErrorAlert(CategoryAddScreenController.class.getSimpleName());
        }
    }

    private Long calculateID(List<Category> categories) {
        Optional<Long> categoryIdOptional = categories.stream()
                .map(NamedEntity::getId)
                .max(Long::compareTo);

        return categoryIdOptional.map(aLong -> aLong + 1).orElseGet(() -> 1L);
    }


}
