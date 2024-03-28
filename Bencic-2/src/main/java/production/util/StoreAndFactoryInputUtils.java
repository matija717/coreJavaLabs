package production.util;

import production.model.Address;
import production.model.Factory;
import production.model.Item;
import production.model.Store;

import java.time.LocalDateTime;
import java.util.Scanner;

import static production.util.CheckerMethodUtils.integerChecker;
import static production.util.InputMethodsUtils.*;

public class StoreAndFactoryInputUtils {
    private static final Integer NUMBER_OF_STORES = 2;
    private static final Integer NUMBER_OF_FACTORIES = 2;

    public static Store[] storesInput(Scanner scanner, Item[] items) {
        System.out.println("Stores input!");
        Store[] stores = new Store[NUMBER_OF_STORES];
        for (int i = 0; i < stores.length; i++) {
            System.out.println((i + 1) + ". store");
            stores[i] = singleStoreInput(scanner, items);

        }
        return stores;
    }

    public static Factory[] factoriesInput(Scanner scanner, Item[] items) {
        System.out.println("Factories input!");
        Factory[] factories = new Factory[NUMBER_OF_FACTORIES];
        for (int i = 0; i < factories.length; i++) {
            System.out.println((i + 1) + ". factory");
            factories[i] = singleFactoryInput(scanner, items);

        }
        return factories;
    }

    private static Store singleStoreInput(Scanner scanner, Item[] items) {
        int itemNumber;
        System.out.print("Name:");
        String name = scanner.nextLine();
        System.out.print("Web address:");
        String webAddress = scanner.nextLine();

        do {
            System.out.print("Type number of items you want to select:");
            itemNumber = scanner.nextInt();
            scanner.nextLine();
            if (integerChecker(itemNumber, items.length)) System.out.println("Error!\nPlease type again!");
        } while (integerChecker(itemNumber, items.length));
        Item[] storeItems = new Item[itemNumber];
        System.out.print("Input identifier:");
        int identifier = scanner.nextInt();
        scanner.nextLine();
        LocalDateTime createdDateTime = LocalDateTime.now();

        return new Store(name, webAddress, itemPicker(items, storeItems, scanner), identifier, createdDateTime);
    }

    private static Factory singleFactoryInput(Scanner scanner, Item[] items) {
        int itemNumber;
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.println("Address input!");
        Address address = addressInput(scanner);
        do {
            System.out.print("Type number of items you want to select for factory:");
            itemNumber = scanner.nextInt();
            scanner.nextLine();
            if (integerChecker(itemNumber, items.length)) System.out.println("Error!\nPlease type again!");
        } while (integerChecker(itemNumber, items.length));

        Item[] factoryItems = new Item[itemNumber];
        System.out.print("Input identifier:");
        int identifier = scanner.nextInt();
        scanner.nextLine();
        LocalDateTime createdDateTime = LocalDateTime.now();

        return new Factory(name, address, itemPicker(items, factoryItems, scanner), identifier, createdDateTime);
    }
}
