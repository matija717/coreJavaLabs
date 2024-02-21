package hr.java.shop.bencic8.utils;

import hr.java.shop.bencic8.production.exception.NoCategoryException;
import hr.java.shop.bencic8.production.model.Category;
import hr.java.shop.bencic8.production.model.Discount;
import hr.java.shop.bencic8.production.model.Item;
import hr.java.shop.bencic8.production.model.NamedEntity;
import javafx.scene.control.TextField;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class InputUtil {
    public static Item itemDataSoak( String category, TextField nameTextField, TextField heightTextField
            , TextField lengthTextField, TextField widthTextField, TextField productionCostTextField,
                                TextField sellingPriceTextField, TextField discountAmountTextField) {
        String name = nameTextField.getText();
        BigDecimal height = new BigDecimal(heightTextField.getText());
        BigDecimal length = new BigDecimal(lengthTextField.getText());
        BigDecimal width = new BigDecimal(widthTextField.getText());
        BigDecimal productionCost = new BigDecimal(productionCostTextField.getText());
        BigDecimal sellingPrice = new BigDecimal(sellingPriceTextField.getText());
        BigDecimal discountAmount = new BigDecimal(discountAmountTextField.getText());
        return new Item(name, idCalc(FileUtils.allItems()), categoryFinder(category), width, height,
                length, productionCost, sellingPrice, new Discount(discountAmount));
    }

    private static Category categoryFinder(String category) {
        Optional<Category> category1 = FileUtils.getCategories()
                .stream()
                .filter(c -> c.getName().equals(category))
                .findFirst();
        if (category1.isPresent()) {
            return category1.get();
        } else throw new NoCategoryException("No category Found");
    }

    private static Long idCalc(List<Item> items) {
        Optional<Long> itemIdOptional = items.stream()
                .map(NamedEntity::getId)
                .max(Long::compareTo);

        return itemIdOptional.map(aLong -> aLong + 1).orElse(1L);
    }
}
