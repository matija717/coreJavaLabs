package hr.java.shop.bencic7.utils;

import hr.java.shop.bencic7.production.exception.FileErrorException;
import hr.java.shop.bencic7.production.exception.NoCategoryException;
import hr.java.shop.bencic7.production.model.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ItemInputReaderUtil {
    private ItemInputReaderUtil(){
        throw new AssertionError("ItemInputReader should not be instantiated");
    }
    static List<Item> itemsInput(List<Category> categories) throws FileErrorException {
        List<Item> items = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("Bencic-7/dat/items.txt"))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                items.add(itemReader(bufferedReader, categories, line));
            }
        } catch (IOException e) {
            throw new FileErrorException(e.getMessage());
        }
        return items;
    }
    static List<Banana> bananasInput(List<Category> categories) {
        List<Banana> bananas = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("Bencic-7/dat/bananas.txt"))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                Item banana = itemReader(bufferedReader, categories, line);
                BigDecimal weight = BigDecimal.valueOf(Double.parseDouble(bufferedReader.readLine()));
                bananas.add(new Banana(
                        banana.getName(),
                        banana.getId(),
                        banana.getObject(),
                        banana.getWidth(),
                        banana.getHeight(),
                        banana.getLength(),
                        banana.getProductionCost(),
                        banana.getSellingPrice(),
                        banana.getDiscountAmount(),
                        weight
                ));
            }
        } catch (IOException e) {
            throw new FileErrorException(e.getMessage());
        }
        return bananas;
    }
    static List<Laptop> laptopsInput(List<Category> categories) {
        List<Laptop> laptops = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("Bencic-7/dat/laptops.txt"))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                Item laptop = itemReader(bufferedReader, categories, line);
                Integer warnity = Integer.parseInt(bufferedReader.readLine());
                laptops.add(new Laptop(
                        laptop.getName(),
                        laptop.getId(),
                        laptop.getObject(),
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
    static List<Kiwi> kiwisInput(List<Category> categories) {
        List<Kiwi> kiwis = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("Bencic-7/dat/kiwis.txt"))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                Item banana = itemReader(bufferedReader, categories, line);
                BigDecimal weight = BigDecimal.valueOf(Double.parseDouble(bufferedReader.readLine()));
                kiwis.add(new Kiwi(
                        banana.getName(),
                        banana.getId(),
                        banana.getObject(),
                        banana.getWidth(),
                        banana.getHeight(),
                        banana.getLength(),
                        banana.getProductionCost(),
                        banana.getSellingPrice(),
                        banana.getDiscountAmount(),
                        weight
                ));
            }
        } catch (IOException e) {
            throw new FileErrorException(e.getMessage());
        }
        return kiwis;
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
}
