package production.util;

import production.model.*;

import java.math.BigDecimal;
import java.util.Scanner;

import static production.util.InputMethodsForItemAndCategories.singleItemInput;

public class ItemInputUtil {
    private static final Integer NUMBER_OF_ITEMS = 3;
    public static Item[] articlesSelectionInput(Scanner scanner, Category[] categories) {
        Item[] items = new Item[NUMBER_OF_ITEMS];
        for (int i = 0; i < NUMBER_OF_ITEMS; i++) {
            System.out.println((i + 1) + ". item choose lap");
            System.out.print("Choose edibles(0) or (normal item or laptop) (1) to input:");
            int edibleOrNonEdiblePicker = scanner.nextInt();
            scanner.nextLine();

            if (edibleOrNonEdiblePicker == 0) {
                System.out.print("Chose between (1)" + Banana.class.getSimpleName() +
                        " or (2)" + Kiwi.class.getSimpleName() + ": ");
                int pickerBetweenBannanasAndKiwis = scanner.nextInt();
                scanner.nextLine();
                if (pickerBetweenBannanasAndKiwis == 1) {
                    System.out.println("Banana picked up for " + (i + 1) + ".input!");
                    items[i] = bananaInput(categories, scanner);
                } else if (pickerBetweenBannanasAndKiwis == 2) {
                    System.out.println("Kiwi picked up for " + (i + 1) + ". input!");
                    items[i] = kiwiInput(categories, scanner);
                }
            } else if (edibleOrNonEdiblePicker == 1) {
                System.out.print("Choose normal items(1) or laptops(2) to input:");
                int pickerBetweenNormalItemAndLaptop = scanner.nextInt();
                scanner.nextLine();
                if (pickerBetweenNormalItemAndLaptop == 1) {
                    System.out.println("Normal item picked up for " + (i + 1) + ". input!");
                    items[i]=singleItemInput(scanner,categories);
                } else if (pickerBetweenNormalItemAndLaptop == 2) {
                    System.out.println("Laptop picked up for " + (i + 1) + ". input!");
                    items[i]= laptopInput(categories, scanner);
                }
            }
        }
        return items;
    }
    private static Kiwi kiwiInput(Category[] categories, Scanner scanner) {
        System.out.println("Kiwi input!");
        System.out.print("Type weight of your chosen item(kg):");
        BigDecimal weight = scanner.nextBigDecimal();
        scanner.nextLine();
        Item item = singleItemInput(scanner, categories);
        Kiwi kiwi = new Kiwi(item.getName(), item.getObject()
                , item.getWidth(), item.getHeight(), item.getLength(),
                item.getProductionCost(), item.getSellingPrice(), weight, item.getDiscountAmount(),
                item.getIdentifier(), item.getCreatedDateTime(), item.getIngredients());
        System.out.println("Input kiwi count costs " + kiwi.calculatePrice() + " EUR");
        System.out.println("Input kiwi has " + kiwi.calculateKilocalories() + " kcal");

        return kiwi;
    }

    private static Banana bananaInput(Category[] categories, Scanner scanner) {

        System.out.println("Banana input!");
        System.out.print("Type weight of your chosen item(kg):");
        BigDecimal weight = scanner.nextBigDecimal();
        scanner.nextLine();
        Item item = singleItemInput(scanner, categories);
        Banana banana = new Banana(item.getName(), item.getObject()
                , item.getWidth(), item.getHeight(), item.getLength(),
                item.getProductionCost(), item.getSellingPrice(), weight, item.getDiscountAmount(),
                item.getIdentifier(), item.getCreatedDateTime(), item.getIngredients());
        System.out.println("Input banana item cost you " + banana.calculatePrice() + " EUR");
        System.out.println("Input banana item has " + banana.calculateKilocalories() + " kcal");


        return banana;
    }

    private static Laptop laptopInput(Category[] categories, Scanner scanner) {
        System.out.println("Laptops Input");
        Item item = singleItemInput(scanner, categories);
        System.out.print("Type duration of guarantee value in years:");
        Integer guarantee = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Type tax amount in range from 0% to 100% without % sign:");
        BigDecimal taxOfProduct = scanner.nextBigDecimal().multiply(BigDecimal.valueOf(0.01));
        scanner.nextLine();
        Laptop laptop = new Laptop(item.getName(), item.getObject()
                , item.getWidth(), item.getHeight(), item.getLength(),
                item.getProductionCost(), item.getSellingPrice(), item.getDiscountAmount(), guarantee,
                item.getIdentifier(), item.getCreatedDateTime(), item.getIngredients(), taxOfProduct);
        System.out.println("Price of laptop with discount is:" + laptop.getSellingPrice());
        laptop.setSellingPrice(laptop.getSellingPrice().multiply(taxOfProduct).add(laptop.getSellingPrice()));
        System.out.println("Price of laptop with added tax is:" + laptop.getSellingPrice());
        laptop.setYears(laptop.durationOfGuarantee(laptop.getYears()));

        return laptop;
    }
}
