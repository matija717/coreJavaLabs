package production.util;

import production.model.Address;

import production.model.Item;

import java.util.Scanner;


public class InputMethodsUtils {

    public static Address addressInput(Scanner scanner) {
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

    public static Item[] itemPicker(Item[] items, Item[] pickedItems, Scanner scanner) {
        int pick;
        for (int i = 0; i < pickedItems.length; i++) {
            for (int j = 0; j < items.length; j++) {
                System.out.println((j + 1) + ". " + items[j].getName());
            }
            System.out.print("Pick(1-" + (items.length + 1) + "): ");
            pick = scanner.nextInt();
            scanner.nextLine();
            pickedItems[i] = items[pick];
        }
        return pickedItems;
    }

}
