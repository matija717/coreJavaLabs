package hr.java.shop.bencic8.utils;

import hr.java.shop.bencic8.production.enume.Cities;
import hr.java.shop.bencic8.production.exception.FileErrorException;
import hr.java.shop.bencic8.production.exception.NoCategoryException;
import hr.java.shop.bencic8.production.exception.NoCityException;
import hr.java.shop.bencic8.production.model.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

 public class InputReaderUtil {
    protected static List<Factory> readFactories(List<Address> addressList, List<Item> items) {
        List<Factory> factoryList = new ArrayList<>();


        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("dat/factories.txt"))) {
            String line;

            Set<Item> factoryItems = new HashSet<>();
            while ((line = bufferedReader.readLine()) != null) {
                Long id = Long.parseLong(line);
                String name = bufferedReader.readLine();
                String cityName = bufferedReader.readLine();
                Cities city = Arrays.stream(Cities.values())
                        .filter(c -> c.getCityName().equals(cityName))
                        .findFirst()
                        .orElseThrow(NoCityException::new);
                Address address = addressList
                        .stream()
                        .filter(a -> a.getCity().equals(city))
                        .findFirst()
                        .orElseThrow(NoCityException::new);
                String itemsString = bufferedReader.readLine();
                String[] parts = itemsString.split(",");
                Arrays.stream(parts)
                        .forEach(p -> factoryItems.add(items.get(Integer.parseInt(p) - 1)));

                factoryList.add(new Factory(name, id, address, factoryItems));
            }


        } catch (IOException e) {
            throw new NoCityException(e.getMessage());
        }

        return factoryList;
    }

    protected static List<Laptop> readLaptops() {
        List<Laptop> laptops = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("dat/laptops.txt"))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                Item laptop = readOneItemData(bufferedReader, line);
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

    protected static List<Banana> readBananas() {
        List<Banana> bananas = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("dat/bananas.txt"))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                Item banana = readOneItemData(bufferedReader, line);
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

    private static Item readOneItemData(BufferedReader bufferedReader, String itemName)
            throws IOException, NoCategoryException {
        List<Category> categories = readCategories();
        Long id = Long.valueOf(bufferedReader.readLine());
        String categoryName = bufferedReader.readLine();


        Category itemCategory = categories.stream()
                .filter(category -> category.getName().equals(categoryName))
                .findFirst()
                .orElseThrow(() -> new NoCategoryException("No category match found"));

        BigDecimal width = new BigDecimal(bufferedReader.readLine());
        BigDecimal height = new BigDecimal(bufferedReader.readLine());
        BigDecimal length = new BigDecimal(bufferedReader.readLine());
        BigDecimal productionCost = new BigDecimal(bufferedReader.readLine());
        BigDecimal sellingPrice = new BigDecimal(bufferedReader.readLine());
        BigDecimal discountAmount = new BigDecimal(bufferedReader.readLine());

        return new Item(itemName, id, itemCategory, width, height, length, productionCost, sellingPrice,
                new Discount(discountAmount));
    }


    protected static List<Item> readItems() throws FileErrorException {
        List<Item> items = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("dat/items.txt"))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                items.add(readOneItemData(bufferedReader, line));
            }
        } catch (IOException e) {
            throw new FileErrorException(e.getMessage());
        }
        return items;
    }

    protected static List<Kiwi> readKiwis() {
        List<Kiwi> kiwis = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("dat/kiwis.txt"))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                Item banana = readOneItemData(bufferedReader, line);
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

    protected static List<Category> readCategories() throws FileErrorException {
        List<Category> categories = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("dat/categories.txt"))) {
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
