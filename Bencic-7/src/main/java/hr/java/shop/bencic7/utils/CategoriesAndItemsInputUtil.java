package hr.java.shop.bencic7.utils;

import hr.java.shop.bencic7.production.exception.FileErrorException;
import hr.java.shop.bencic7.production.model.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import static hr.java.shop.bencic7.utils.ItemInputReaderUtil.*;

public class CategoriesAndItemsInputUtil {
    private CategoriesAndItemsInputUtil(){
        throw new AssertionError("CategoriesAndItemsInputUtil should not be instantiated");
    }

    public static List<Item> allItems(List<Category> categories) {
        List<Item> allItems = new ArrayList<>();
        allItems.addAll(itemsInput(categories));
        allItems.addAll(bananasInput(categories));
        allItems.addAll(laptopsInput(categories));
        allItems.addAll(kiwisInput(categories));
        return allItems;
    }

    public static List<Category> categoriesViaFileInput() throws FileErrorException {
        List<Category> categories = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("Bencic-7/dat/categories.txt"))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                Long id = Long.parseLong(bufferedReader.readLine());
                String caregoryDescription = bufferedReader.readLine();
                categories.add(new Category(caregoryDescription, id, line));
            }

        } catch (IOException e) {
            throw new FileErrorException(e.getMessage());
        }
        return categories;
    }


}
