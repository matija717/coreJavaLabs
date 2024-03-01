package production.util;

import production.model.Category;
import production.model.Item;

import java.math.BigDecimal;
import java.util.Scanner;

import static production.util.CheckerMethodUtil.bigDecimalChecker;
import static production.util.CheckerMethodUtil.integerChecker;

public class ItemInputUtil {
    private static final Integer NUMBER_OF_ITEMS = 2;


    public static Item[] itemsInput(Scanner scanner, Category[] categories) {
        System.out.println("Items input!");
        Item[] items = new Item[NUMBER_OF_ITEMS];
        System.out.println("Type date and time of buying:");
        for (int i = 0; i < items.length; i++) {
            System.out.println((i + 1) + ". item");
            items[i] = singleItemInput(scanner, categories);
        }
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
}
