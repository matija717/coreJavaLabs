package production.util;

import production.model.*;

import java.util.Scanner;

import static production.util.CheckerMethodUtil.integerChecker;

public class InputMethodsUtil {
    private static final Integer NUMBER_OF_CATEGORIES = 2;
    public static Factory singleFactoryInput(Scanner scanner, Item[] items) {
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

        return new Factory(name, address, itemPicker(items, factoryItems, scanner));
    }
    private static Address addressInput(Scanner scanner) {
        System.out.print("Street: ");
        String street = scanner.nextLine();
        System.out.print("House number: ");
        String houseNumber = scanner.nextLine();
        System.out.print("Postal code: ");
        String postalCode = scanner.nextLine();
        System.out.print("City: ");
        String city = scanner.nextLine();

        return new Address(street, houseNumber, city, postalCode);
    }
    public static Category[] categoriesInput(Scanner scanner) {
        System.out.println("Categories input!");
        Category[] categories = new Category[NUMBER_OF_CATEGORIES];
        for (int i = 0; i < NUMBER_OF_CATEGORIES; i++) {
            System.out.println((i + 1) + ". category");
            System.out.print("Name:");
            String name = scanner.nextLine();
            System.out.print("Description:");
            String description = scanner.nextLine();
            categories[i] = new Category(description, name);
        }

        return categories;
    }
    public static Store singleStoreInput(Scanner scanner, Item[] items) {
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

        return new Store(name, webAddress, itemPicker(items, storeItems, scanner));
    }
    public static Item[] itemPicker(Item[] items, Item[] pickedItems, Scanner scanner) {
        int pick;
        for (int i = 0; i < pickedItems.length; i++) {
            for (int j = 0; j < items.length; j++) {
                System.out.println((j + 1) + ". " + items[j].getName());
            }
            System.out.print("Pick(1-" + (items.length) + "): ");
            pick = scanner.nextInt();
            scanner.nextLine();
            pickedItems[i] = items[pick-1];
        }
        return pickedItems;
    }
}
