package production.main;

import production.model.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static final Integer NUMBER_OF_CATEGORIES = 2;
    private static final Integer NUMBER_OF_ITEMS = 2;
    private static final Integer NUMBER_OF_FACTORIES = 2;
    private static final Integer NUMBER_OF_STORES = 2;

    private static final DateTimeFormatter dateShape = DateTimeFormatter.ofPattern("dd-MM-yyyy-HH:MM:ss");


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Category[] categories = categoriesInput(scanner);
        Item[] items = itemsInput(scanner, categories);
        Cart[] carts= cartsInput(scanner,items);
        cartPrint(carts);
        Store[] stores = storesInput(scanner, items);
        Factory[] factories = factoriesInput(scanner, items);


        Item cheapestItem = findCheapestItem(stores);
        Item biggestItem = findBiggestItem(factories);

        System.out.println("Article with biggest volume is " + biggestItem.getName());
        System.out.println("Article with cheapest price is " + cheapestItem.getName());
    }

    private static void cartPrint(Cart[] carts) {


    }

    private static Cart[] cartsInput(Scanner scanner, Item[] items) {
        System.out.println("Input number of wanted carts");
        int numberOfCarts= scanner.nextInt();
        scanner.nextLine();
        Cart[] carts= new Cart[numberOfCarts];

        for (int i = 0; i <carts.length; i++) {
            LocalDateTime timeOfBuying=LocalDateTime.now();
            System.out.print("Insert number of Items for cart");
            int cartItemsNumber= scanner.nextInt();
            scanner.nextLine();
            Item[] cartItems=new Item[cartItemsNumber];
            Cart cart=new Cart(itemPicker(items,cartItems,scanner),timeOfBuying);
            carts[i]=cart;

        }
        System.out.println("Is that all or you want to remove something");
        carts=cartEditor(carts,scanner);


        return carts;
    }

    private static Cart[] cartEditor(Cart[] carts, Scanner scanner) {
        System.out.println("Welcome to cart editor");

        for (Cart c:carts) {
            for (int i = 0; i <c.getBoughtStuff().length; i++) {
                System.out.println((i+1)+"."+c.getBoughtStuff()[i].getName());
            }
            System.out.println("Pick item to delete:");
            int selectedItem=scanner.nextInt();
            scanner.nextLine();
            Item[] selectedItems=new Item[c.getBoughtStuff().length-1];
            for (int i = 0; i < c.getBoughtStuff().length; i++) {
                if(!(i==selectedItem-1)){
                    selectedItems[i]=c.getBoughtStuff()[i];
                }
            }
            c.setBoughtStuff(selectedItems);
        }
        return carts;
    }

    private static Item findBiggestItem(Factory[] factories) {
        Item biggestItem = factories[0].getItems()[0];
        for (Factory s : factories) {
            for (int i = 0; i < s.getItems().length; i++) {
                if (s.getItems()[0].equals(s.getItems()[i])) {
                    biggestItem = s.getItems()[i];
                } else if (volumeOfItemCalculation(biggestItem).
                        compareTo(volumeOfItemCalculation(s.getItems()[i])) < 0) {
                    biggestItem = s.getItems()[i];
                }
            }
        }
        return biggestItem;
    }

    private static BigDecimal volumeOfItemCalculation(Item item) {
        return item.getHeight().multiply(item.getLength().multiply(item.getWidth()));
    }

    private static Item findCheapestItem(Store[] stores) {
        Item cheapestItem = stores[0].getItems()[0];
        for (Store s : stores) {
            for (int i = 0; i < s.getItems().length; i++) {
                if (s.getItems()[0].equals(s.getItems()[i])) {
                    cheapestItem = s.getItems()[i];
                } else if (s.getItems()[i].getSellingPrice()
                        .compareTo(cheapestItem.getSellingPrice()) < 0) {
                    cheapestItem = s.getItems()[i];
                }
            }
        }
        return cheapestItem;
    }

    private static Factory[] factoriesInput(Scanner scanner, Item[] items) {
        System.out.println("Factories input!");
        Factory[] factories = new Factory[NUMBER_OF_FACTORIES];
        for (int i = 0; i < factories.length; i++) {
            System.out.println((i + 1) + ". factory");
            factories[i] = singleFactoryInput(scanner, items);

        }
        return factories;
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

        return new Factory(name, address, itemPicker(items, factoryItems, scanner));
    }

    private static Item[] itemPicker(Item[] items, Item[] pickedItems, Scanner scanner) {
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

    private static Store[] storesInput(Scanner scanner, Item[] items) {
        System.out.println("Stores input!");
        Store[] stores = new Store[NUMBER_OF_STORES];
        for (int i = 0; i < stores.length; i++) {
            System.out.println((i + 1) + ". store");
            stores[i] = singleStoreInput(scanner, items);

        }
        return stores;
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
        scanner.nextLine();
        Item[] storeItems = new Item[itemNumber];

        return new Store(name, webAddress, itemPicker(items, storeItems, scanner));
    }

    private static Item[] itemsInput(Scanner scanner, Category[] categories) {
        System.out.println("Items input!");
        Item[] items = new Item[NUMBER_OF_ITEMS];
        System.out.println("Type date and time of buying:");
        LocalDateTime timeOfBuying=LocalDateTime.parse(scanner.nextLine(),dateShape);
        for (int i = 0; i < items.length; i++) {
            System.out.println((i + 1) + ". item");
            items[i] = singleItemInput(scanner, categories);
            System.out.println("Item added to cart");
        }
        System.out.println("Is that all or you want to remove something");

        Cart cart=new Cart(items,timeOfBuying);

        return items;
    }

    private static Item singleItemInput(Scanner scanner, Category[] categories) {
        BigDecimal width, length, height, productionCost, sellingPrice;
        int categoryPick;

        System.out.print("Name:");
        String name = scanner.nextLine();
        System.out.println("Pick category from 1 to " + categories.length);
        for (int j = 0; j < categories.length; j++) {
            System.out.println((j + 1) + ". " + categories[j].getName());
        }
        do {
            System.out.print("Pick:");
            categoryPick = scanner.nextInt() - 1;
            scanner.nextLine();
            if (integerChecker(categoryPick, categories.length)) System.out.println("Error!\nPlease type again!");
        } while (integerChecker(categoryPick, categories.length));

        do {
            System.out.print("Width:");
            width = scanner.nextBigDecimal();
            scanner.nextLine();
            if (bigDecimalChecker(width)) System.out.println("Error!\nPlease type again!");
        } while (bigDecimalChecker(width));

        do {
            System.out.print("Length:");
            length = scanner.nextBigDecimal();
            scanner.nextLine();
            if (bigDecimalChecker(length)) System.out.println("Error!\nPlease type again!");
        } while (bigDecimalChecker(length));

        do {
            System.out.print("Height:");
            height = scanner.nextBigDecimal();
            scanner.nextLine();
            if (bigDecimalChecker(height)) System.out.println("Error!\nPlease type again!");
        } while (bigDecimalChecker(height));

        do {
            System.out.print("Production cost:");
            productionCost = scanner.nextBigDecimal();
            scanner.nextLine();
            if (bigDecimalChecker(productionCost)) System.out.println("Error!\nPlease type again!");
        } while (bigDecimalChecker(productionCost));

        do {
            System.out.print("Selling price:");
            sellingPrice = scanner.nextBigDecimal();
            scanner.nextLine();
            if (bigDecimalChecker(sellingPrice)) System.out.println("Error!\nPlease type again!");
        } while (bigDecimalChecker(sellingPrice));

        return new Item(name, categories[categoryPick], width, height,
                length, productionCost, sellingPrice);
    }

    private static Category[] categoriesInput(Scanner scanner) {
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

    private static boolean bigDecimalChecker(BigDecimal input) {
        return input.compareTo(new BigDecimal("0")) <= 0;
    }

    private static boolean integerChecker(Integer input, Integer maxNumber) {
        return !(input > 0 && input <= maxNumber);
    }

}
