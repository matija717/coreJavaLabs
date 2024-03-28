package production.util;


import production.exception.FileErrorException;

import production.model.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;

import java.util.List;

import static production.util.ItemInputUtil.*;

public class CategoryAndItemsInputUtils {
    private CategoryAndItemsInputUtils(){
        throw new AssertionError("CategoryAndItemsInputUtils should not be instantiated");
    }

    public static List<Category> categoriesViaFileInput() throws RuntimeException {
        List<Category> categories = new ArrayList<>();
        String absolutehPath = "Bencic-6/dat/categories.txt";
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(absolutehPath))) {
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

    public static List<Item> allItemsInput() {
        List<Item> allItems = new ArrayList<>();
        allItems.addAll(itemsInput());
        allItems.addAll(bananasInput());
        allItems.addAll(kiwisInput());
        allItems.addAll(laptopsInput());
        return allItems;
    }




}
