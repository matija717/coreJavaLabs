package hr.java.shop.bencic7.utils;

import hr.java.shop.bencic7.production.enume.Cities;
import hr.java.shop.bencic7.production.exception.FileErrorException;
import hr.java.shop.bencic7.production.exception.NoCategoryException;
import hr.java.shop.bencic7.production.exception.NoCityException;
import hr.java.shop.bencic7.production.model.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

public class FileUtils {
    public static List<Kupci> kupci() throws NoCityException{
        List<Kupci> buyersList=new ArrayList<>();
        List<Address> addressList=addressInput();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("dat/kupci.txt"))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                Long id = Long.parseLong(line);
                String name = bufferedReader.readLine();
                String surname = bufferedReader.readLine();
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
                String itemsString=bufferedReader.readLine();
                String[] parts = itemsString.split(",");
                List<Item> allItems=itemsInput(categoriesViaFileInput());
                List<Item> buyerItems=new ArrayList<>();
                Arrays.stream(parts)
                        .forEach(p ->allItems.add(allItems
                                .stream()
                                .filter(i->i.getId().equals(Long.parseLong(p)))
                                .findFirst()
                                .get()));
                String date= bufferedReader.readLine();
                buyersList.add(new Kupci(name, id, surname, buyerItems,address,date));
            }
        } catch (IOException e) {
            throw new FileErrorException();
        }
        return buyersList;
    }
    public static List<Factory> allFactories() {
        List<Address> addressList = addressInput();
        List<Item> items = allItems(FileUtils.categoriesViaFileInput());
        return factoriesInput(addressList, items);
    }

    public static List<Store> storesInput() {
        List<Store> stores = new ArrayList<>();
        List<Item> items = allItems(categoriesViaFileInput());
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("dat/stores.txt"))) {
            String line;
            Set<Item> storeItems = new HashSet<>();
            while ((line = bufferedReader.readLine()) != null) {
                Long id = Long.parseLong(line);
                String name = bufferedReader.readLine();
                String webAddress = bufferedReader.readLine();
                String itemsString = bufferedReader.readLine();
                String[] parts = itemsString.split(",");
                Arrays.stream(parts)
                        .forEach(p -> storeItems.add(items.get(Integer.parseInt(p) - 1)));
                stores.add(new Store(name, id, webAddress, storeItems));
            }
        } catch (IOException e) {
            throw new FileErrorException();
        }
        return stores;
    }

    private static List<Factory> factoriesInput(List<Address> addressList, List<Item> items) {
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

    public static List<Address> addressInput() {
        List<Address> addressList = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("dat/addresses.txt"))) {

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                Long id = Long.parseLong(line);
                String name = bufferedReader.readLine();
                String street = bufferedReader.readLine();
                String houseNumber = bufferedReader.readLine();
                String selectedCityString = bufferedReader.readLine();
                Cities city = Arrays
                        .stream(Cities.values())
                        .filter(c -> c.getCityName().equals(selectedCityString))
                        .findFirst()
                        .orElseThrow(NoCityException::new);

                Address newAddress = new Address(id, name, street, houseNumber, city);
                addressList.add(newAddress);
            }

        } catch (IOException e) {
            throw new NoCityException(e.getMessage());
        }

        return addressList;
    }

    public static List<Item> allItems(List<Category> categories) {
        List<Item> allItems = new ArrayList<>();
        allItems.addAll(itemsInput(categories));
        allItems.addAll(bananasInput(categories));
        allItems.addAll(laptopsInput(categories));
        allItems.addAll(kiwisInput(categories));
        return allItems;
    }

    private static List<Banana> bananasInput(List<Category> categories) {
        List<Banana> bananas = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("dat/bananas.txt"))) {
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

    private static List<Laptop> laptopsInput(List<Category> categories) {
        List<Laptop> laptops = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("dat/laptops.txt"))) {
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

    private static List<Kiwi> kiwisInput(List<Category> categories) {
        List<Kiwi> kiwis = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("dat/kiwis.txt"))) {
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

    private static List<Item> itemsInput(List<Category> categories) throws FileErrorException {
        List<Item> items = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("dat/items.txt"))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                items.add(itemReader(bufferedReader, categories, line));
            }
        } catch (IOException e) {
            throw new FileErrorException(e.getMessage());
        }
        return items;
    }

    public static List<Category> categoriesViaFileInput() throws FileErrorException {
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
