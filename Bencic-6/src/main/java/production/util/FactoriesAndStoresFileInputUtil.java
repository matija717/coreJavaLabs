package production.util;

import production.enume.Cities;
import production.exception.NoCityException;
import production.model.Address;
import production.model.Factory;
import production.model.Item;
import production.model.Store;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class FactoriesAndStoresFileInputUtil {
    private FactoriesAndStoresFileInputUtil(){
        throw new AssertionError("Factories and stores file input util should not bi instantiated");
    }
    public static List<Factory> factoriesInput(List<Address> addressList, List<Item> items) {
        List<Factory> factoryList = new ArrayList<>();


        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("Bencic-6/dat/factories.txt"))) {
            String line;

            Set<Item> factoryItems = new HashSet<>();
            while ((line = bufferedReader.readLine()) != null) {
                Long id = Long.parseLong(line);
                String name = bufferedReader.readLine();
                String cityName = bufferedReader.readLine();
                Cities city = Arrays.stream(Cities.values())
                        .filter(c -> c.getCity().equals(cityName))
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
    public static List<Store> storesInput(List<Item> items) {
        List<Store> stores = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("Bencic-6/dat/stores.txt"))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                Long id = Long.parseLong(line);
                String name = bufferedReader.readLine();
                String webAddress = bufferedReader.readLine();
                String itemsString = bufferedReader.readLine();
                String[] parts = itemsString.split(",");
                Set<Item> storeItems = new HashSet<>();
                Arrays.stream(parts)
                        .forEach(p -> storeItems.add(items.get(Integer.parseInt(p))));

                stores.add(new Store(name, id, webAddress, storeItems));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stores;
    }
    public static List<Address> addressInput() {
        List<Address> addressList = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("Bencic-6/dat/addresses.txt"))) {

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                Long id = Long.parseLong(line);
                String name = bufferedReader.readLine();
                String houseNumber = bufferedReader.readLine();
                String selectedCityString = bufferedReader.readLine();
                Cities city = Arrays
                        .stream(Cities.values())
                        .filter(c -> c.getCity().equals(selectedCityString))
                        .findFirst()
                        .orElseThrow(NoCityException::new);

                Address newAddress = new Address(name,id, houseNumber, city);
                addressList.add(newAddress);
            }

        } catch (IOException e) {
            throw new NoCityException(e.getMessage());
        }

        return addressList;
    }

    public static void serializeFactoriesAndStores(List<Factory> factories, List<Store> stores) {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                Files.newOutputStream(Paths.get("Bencic-6/dat/binary.dat")))) {
            List<Object> objectsToSerialize = new ArrayList<>();

            objectsToSerialize.addAll(factories.stream()
                    .filter(factory -> factory.getItems().size() > 4)
                    .toList());

            objectsToSerialize.addAll(stores.stream()
                    .filter(store -> store.getItems().size() > 4)
                    .toList());

            oos.writeObject(objectsToSerialize);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
