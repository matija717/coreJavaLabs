package production.util;

import production.exception.FileErrorException;
import production.exception.NoCategoryException;
import production.model.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static production.util.CategoryAndItemsInputUtils.categoriesViaFileInput;

public class ItemInputUtil {
    private ItemInputUtil() {
        throw new AssertionError("ItemInputUtil should not be instantiated");
    }

    public static List<Item> itemsInput() throws FileErrorException {
        List<Item> items = new ArrayList<>();
        List<Category> categories = categoriesViaFileInput();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("Bencic-6/dat/items.txt"))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                items.add(itemReader(bufferedReader, categories, line));
            }
        } catch (IOException e) {
            throw new FileErrorException(e.getMessage());
        }
        return items;
    }

    private static Item itemReader(BufferedReader bufferedReader, List<Category> categories, String itemName)
            throws IOException, NoCategoryException {
        Long id = Long.parseLong(bufferedReader.readLine());
        String categoryName = bufferedReader.readLine();

        Category itemCategory = categories.stream()
                .filter(category -> category.getName().equals(categoryName))
                .findFirst()
                .orElseThrow(() -> new NoCategoryException("No category match found"));

        BigDecimal width = BigDecimal.valueOf(Double.parseDouble(bufferedReader.readLine()));
        BigDecimal height = BigDecimal.valueOf(Double.parseDouble(bufferedReader.readLine()));
        BigDecimal length = BigDecimal.valueOf(Double.parseDouble(bufferedReader.readLine()));
        BigDecimal productionCost = BigDecimal.valueOf(Double.parseDouble(bufferedReader.readLine()));
        BigDecimal sellingPrice = BigDecimal.valueOf(Double.parseDouble(bufferedReader.readLine()));
        BigDecimal discountAmount = BigDecimal.valueOf(Double.parseDouble(bufferedReader.readLine()));

        return new Item(itemName, id, itemCategory, width, height, length, productionCost, sellingPrice,
                new Discount(discountAmount));
    }

    public static List<Banana> bananasInput() {
        List<Banana> bananas = new ArrayList<>();
        List<Category> categories = categoriesViaFileInput();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("Bencic-6/dat/bananas.txt"))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                Item banana = itemReader(bufferedReader, categories, line);
                BigDecimal weight = BigDecimal.valueOf(Double.parseDouble(bufferedReader.readLine()));
                bananas.add(new Banana(
                        banana.getName(),
                        banana.getId(),
                        banana.getCategory(),
                        banana.getWidth(),
                        banana.getHeight(),
                        banana.getLength(),
                        banana.getProductionCost(),
                        banana.getSellingPrice(),
                        weight,
                        banana.getDiscountAmount()

                ));
            }
        } catch (IOException e) {
            throw new FileErrorException(e.getMessage());
        }
        return bananas;
    }

    public static List<Kiwi> kiwisInput() {
        List<Kiwi> kiwis = new ArrayList<>();
        List<Category> categories = categoriesViaFileInput();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("Bencic-6/dat/kiwis.txt"))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                Item kiwi = itemReader(bufferedReader, categories, line);
                BigDecimal weight = BigDecimal.valueOf(Double.parseDouble(bufferedReader.readLine()));
                kiwis.add(new Kiwi(
                        kiwi.getName(),
                        kiwi.getId(),
                        kiwi.getCategory(),
                        kiwi.getWidth(),
                        kiwi.getHeight(),
                        kiwi.getLength(),
                        kiwi.getProductionCost(),
                        kiwi.getSellingPrice(),
                        weight,
                        kiwi.getDiscountAmount()
                ));
            }
        } catch (IOException e) {
            throw new FileErrorException(e.getMessage());
        }
        return kiwis;
    }

    public static List<Laptop> laptopsInput() {
        List<Laptop> laptops = new ArrayList<>();
        List<Category> categories = categoriesViaFileInput();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("Bencic-6/dat/laptops.txt"))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                Item laptop = itemReader(bufferedReader, categories, line);
                Integer warnity = Integer.parseInt(bufferedReader.readLine());
                laptops.add(new Laptop(
                        laptop.getName(),
                        laptop.getId(),
                        laptop.getCategory(),
                        laptop.getWidth(),
                        laptop.getHeight(),
                        laptop.getLength(),
                        laptop.getProductionCost(),
                        laptop.getSellingPrice(),
                        laptop.getDiscountAmount(),
                        warnity
                ));
            }
        } catch (IOException e) {
            throw new FileErrorException(e.getMessage());
        }
        return laptops;
    }
}
