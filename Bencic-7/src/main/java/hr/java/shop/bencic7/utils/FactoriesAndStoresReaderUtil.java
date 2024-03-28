package hr.java.shop.bencic7.utils;

import hr.java.shop.bencic7.production.enume.Cities;
import hr.java.shop.bencic7.production.exception.FileErrorException;
import hr.java.shop.bencic7.production.exception.NoCityException;
import hr.java.shop.bencic7.production.model.Address;
import hr.java.shop.bencic7.production.model.Factory;
import hr.java.shop.bencic7.production.model.Item;
import hr.java.shop.bencic7.production.model.Store;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import static hr.java.shop.bencic7.utils.CategoriesAndItemsInputUtil.allItems;
import static hr.java.shop.bencic7.utils.CategoriesAndItemsInputUtil.categoriesViaFileInput;

public class FactoriesAndStoresReaderUtil {
    private FactoriesAndStoresReaderUtil(){
        throw new AssertionError("FactoriesAndStoresReaderUtil should not be instantiated");
    }
    public static List<Factory> allFactories() {
        List<Address> addressList = addressInput();
        List<Item> items = allItems(categoriesViaFileInput());
        return factoriesInput(addressList, items);
    }
    public static List<Store> storesInput() {
        List<Store> stores = new ArrayList<>();
        List<Item> items = allItems(categoriesViaFileInput());
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("Bencic-7/dat/stores.txt"))) {
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                Long id = Long.parseLong(line);
                String name = bufferedReader.readLine();
                String webAddress = bufferedReader.readLine();
                String itemsString = bufferedReader.readLine();
                String[] parts = itemsString.split(",");
                Set<Item> storeItems = new HashSet<>();
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
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("Bencic-7/dat/factories.txt"))) {
            String line;


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
                Set<Item> factoryItems = new HashSet<>();
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

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("Bencic-7/dat/addresses.txt"))) {

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
}
