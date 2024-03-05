package production.util;

import production.model.Category;
import production.model.Discount;
import production.model.Ingredient;
import production.model.Item;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Scanner;

import static production.util.CheckerMethodUtils.bigDecimalChecker;
import static production.util.CheckerMethodUtils.integerChecker;

public class InputMethodsForItemAndCategories {
    private static final Integer NUMBER_OF_CATEGORIES = 3;
    protected static Item singleItemInput(Scanner scanner, Category[] categories) {
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
        System.out.print("Type discount amount in range from 0% to 100% without % sign:");
        BigDecimal discountAmount = scanner.nextBigDecimal().multiply(BigDecimal.valueOf(0.01));
        scanner.nextLine();
        sellingPrice = sellingPrice.add(sellingPrice.negate().multiply(discountAmount));
        System.out.print("Input identifier:");
        int identifier = scanner.nextInt();
        scanner.nextLine();
        LocalDateTime createdDateTime = LocalDateTime.now();
        System.out.print("Input ingredient:");
        String ingredient = scanner.nextLine();


        return new Item(name, categories[categoryPick], width, height,
                length, productionCost, sellingPrice, new Discount(discountAmount), identifier, createdDateTime,
                new Ingredient(ingredient));
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
            System.out.print("Input identifier:");
            int identifier = scanner.nextInt();
            scanner.nextLine();
            LocalDateTime createdDateTime = LocalDateTime.now();
            categories[i] = new Category(description, name, identifier, createdDateTime);
        }

        return categories;
    }

}
