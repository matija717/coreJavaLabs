package production.util;

import production.enume.Cities;
import production.exception.NoCityException;
import production.genericsi.FoodStore;
import production.genericsi.TechnicalStore;
import production.model.*;

import java.util.*;

import static production.main.Main.consoleLogger;
import static production.main.Main.logger;
import static production.util.ItemsInputUtil.itemPicker;
import static production.util.LoggerMethods.writeInConsoleWithLogger;

public class FactoriesAndStoresInputUtil {
    private static final int NUMBER_OF_STORES = 3;
    private static final int NUMBER_OF_FACTORIES = 2;

    public static List<Store> storesInput(Scanner scanner, List<Item> items) {
        logger.error("Store input!");
        consoleLogger.info("Stores input!");
        List<Store> stores = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_STORES; i++) {
            writeInConsoleWithLogger((i + 1) + ". store");
            stores.add(singleStoreInput(scanner, items));

        }
        stores.add(technicalStoreInput(scanner, items));
        stores.add(foodStoreInput(scanner, items));
        return stores;
    }

    private static TechnicalStore<Technical> technicalStoreInput(Scanner scanner, List<Item> items) {
        consoleLogger.info("\nName:");
        String name = scanner.nextLine();
        consoleLogger.info("\nWeb address:");
        String webAddress = scanner.nextLine();
        TechnicalStore<Technical> techStore = new TechnicalStore<>(name, webAddress, new HashSet<>());
        items.stream().filter(Technical.class::isInstance)
                .map(Technical.class::cast)
                .forEach(techStore::addTechnicalItem);
        return techStore;
    }

    private static FoodStore<Edible> foodStoreInput(Scanner scanner, List<Item> items) {
        consoleLogger.info("\nName:");
        String name = scanner.nextLine();
        consoleLogger.info("\nWeb address:");
        String webAddress = scanner.nextLine();
        FoodStore<Edible> foodStore = new FoodStore<>(name, webAddress, new HashSet<>());
        items.stream().filter(Technical.class::isInstance)
                .map(Edible.class::cast)
                .forEach(foodStore::addEdibleItem);
        return foodStore;
    }


    private static Store singleStoreInput(Scanner scanner, List<Item> items) {
        logger.error("Data for store!");
        consoleLogger.info("Name:");
        String name = scanner.nextLine();
        consoleLogger.info("Web address:");
        String webAddress = scanner.nextLine();


        return new Store(name, webAddress, itemPicker(items, scanner));
    }

    public static Address addressInput(Scanner scanner) {
        consoleLogger.info("Street: ");
        String street = scanner.nextLine();
        consoleLogger.info("House number: ");
        String houseNumber = scanner.nextLine();
        try {
            Cities city = citySelection(scanner);
            return new Address(street, houseNumber, city);
        } catch (NoCityException ex) {
            writeInConsoleWithLogger("No city found");
        }
        throw new RuntimeException();
    }

    private static Cities citySelection(Scanner scanner) {
        consoleLogger.info("\nCity: ");
        String city = scanner.nextLine();
        Optional<Cities> cityValue = Arrays.stream(Cities.values())
                .filter(c -> c.getCity().equals(city)).findAny();

        if (cityValue.isPresent()) return cityValue.get();
        else throw new NoCityException("No city found try again");
    }

    public static List<Factory> factoriesInput(Scanner scanner, List<Item> items) {
        logger.error("Factories input!");
        consoleLogger.info("Factories input!");
        List<Factory> factories = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_FACTORIES; i++) {
            writeInConsoleWithLogger((i + 1) + ". factory");
            factories.add(singleFactoryInput(scanner, items));

        }
        return factories;
    }

    private static Factory singleFactoryInput(Scanner scanner, List<Item> items) {
        logger.error("Data for factory input!");
        consoleLogger.info("Name: ");
        String name = scanner.nextLine();
        consoleLogger.info("Address input!");
        Address address = addressInput(scanner);


        return new Factory(name, address, itemPicker(items, scanner));
    }
}
