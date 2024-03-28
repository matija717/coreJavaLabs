package production.util;

import production.model.Address;
import production.model.Factory;
import production.model.Item;
import production.model.Store;

import java.util.Scanner;

import static production.main.Main.*;
import static production.util.InputCheckerMethods.integerInputMismatchChecker;
import static production.util.ItemsInputUtil.itemPicker;
import static production.util.LoggerMethods.consoleLogger;
import static production.util.LoggerMethods.writeInConsoleWithLogger;

public class FactoriesAndStoresInputUtil {
    private static final int NUMBER_OF_STORES = 3;
    private static final int NUMBER_OF_FACTORIES = 2;

    public static Store[] storesInput(Scanner scanner, Item[] items) {
        logger.error("Store input!");
        consoleLogger.info("\nStores input!");
        Store[] stores = new Store[NUMBER_OF_STORES];
        for (int i = 0; i < stores.length; i++) {
            if(i==0)writeInConsoleWithLogger("\n");
            writeInConsoleWithLogger((i + 1) + ". store");
            stores[i] = singleStoreInput(scanner, items);
        }
        return stores;
    }
    private static Store singleStoreInput(Scanner scanner, Item[] items) {
        logger.error("Data for store!");
        consoleLogger.info("\nName:");
        String name = scanner.nextLine();
        consoleLogger.info("Web address:");
        String webAddress = scanner.nextLine();
        String messageForInput = "Type number of items you want to select:";
        int itemNumber = integerInputMismatchChecker(scanner, messageForInput, 0, items.length);

        Item[] storeItems = new Item[itemNumber];

        return new Store(name, webAddress, itemPicker(items, storeItems, scanner));
    }
    public static Address addressInput(Scanner scanner) {
        consoleLogger.info("\nStreet: ");
        String street = scanner.nextLine();
        consoleLogger.info("House number: ");
        String houseNumber = scanner.nextLine();
        consoleLogger.info("Postal code: ");
        String postalCode = scanner.nextLine();
        consoleLogger.info("City: ");
        String city = scanner.nextLine();

        return new Address(street, houseNumber, city, postalCode);
    }
    public static Factory[] factoriesInput(Scanner scanner, Item[] items) {
        logger.error("Factories input!");
        consoleLogger.info("\nFactories input!");
        Factory[] factories = new Factory[NUMBER_OF_FACTORIES];
        for (int i = 0; i < factories.length; i++) {
            if(i==0)writeInConsoleWithLogger("\n");
            writeInConsoleWithLogger((i + 1) + ". factory");
            factories[i] = singleFactoryInput(scanner, items);

        }
        return factories;
    }
    private static Factory singleFactoryInput(Scanner scanner, Item[] items) {
        logger.error("Data for factory input!");
        consoleLogger.info("\nName: ");
        String name = scanner.nextLine();
        consoleLogger.info("Address input!");
        Address address = addressInput(scanner);
        String messageForInput = "Number of items:";
        int itemNumber = integerInputMismatchChecker(scanner, messageForInput, 1, items.length);
        Item[] factoryItems = new Item[itemNumber];

        return new Factory(name, address, itemPicker(items, factoryItems, scanner));
    }
}
