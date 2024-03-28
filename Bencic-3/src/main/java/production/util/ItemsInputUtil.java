package production.util;

import production.exception.SameItemException;
import production.model.*;

import java.math.BigDecimal;
import java.util.Scanner;

import static production.main.Main.logger;
import static production.util.InputCheckerMethods.*;
import static production.util.InputMethods.singleItemInput;
import static production.util.LoggerMethods.writeInConsoleWithLogger;
import static production.util.LoggerMethods.writeInFIleWithLogger;

public class ItemsInputUtil {
    private static final Integer NUMBER_OF_ITEMS = 3;

    public static Item[] articlesSelectionInput(Scanner scanner, Category[] categories) {
        Item[] items = new Item[NUMBER_OF_ITEMS];
        for (int i = 0; i < NUMBER_OF_ITEMS; i++) {
            writeInConsoleWithLogger("\n" + (i + 1) + ". item choose lap\n");
            String message = "Choose edibles(0) or (normal item or laptop) (1) to input:";
            int edibleOrNonEdiblePicker = integerInputMismatchChecker(scanner, message, 0, 1);
            String input = ". input!";
            if (edibleOrNonEdiblePicker == 0) {
                message = "Chose between (1)" + Banana.class.getSimpleName() +
                        " or (2)" + Kiwi.class.getSimpleName() + ": ";
                int pickerBetweenBananasAndKiwis = integerInputMismatchChecker(
                        scanner, message, 1, 2);
                if (pickerBetweenBananasAndKiwis == 1) {
                    writeInConsoleWithLogger("Banana picked up for " + (i + 1) + input);
                    items[i] = bananaInput(categories, scanner);
                } else if (pickerBetweenBananasAndKiwis == 2) {
                    writeInConsoleWithLogger("Kiwi picked up for " + (i + 1) + input);
                    items[i] = kiwiInput(categories, scanner);
                }
            } else if (edibleOrNonEdiblePicker == 1) {
                message = "Choose normal items(1) or laptops(2) to input:";
                int pickerBetweenNormalItemAndLaptop = integerInputMismatchChecker(
                        scanner, message, 1, 2);
                if (pickerBetweenNormalItemAndLaptop == 1) {
                    writeInConsoleWithLogger("Normal item picked up for " + (i + 1) + input);
                    items[i] = singleItemInput(scanner, categories);
                } else if (pickerBetweenNormalItemAndLaptop == 2) {
                    writeInConsoleWithLogger("Laptop picked up for " + (i + 1) + input);
                    items[i] = laptopInput(categories, scanner);
                }
            }
        }
        return items;
    }

    private static Kiwi kiwiInput(Category[] categories, Scanner scanner) {
        writeInConsoleWithLogger("\nKiwi input!");
        Item item = singleItemInput(scanner, categories);
        String message = "Type weight of your chosen item(kg):";
        BigDecimal weight = bigDecimalInputMismatchChecker(scanner, message);
        Kiwi kiwi = new Kiwi(item.getName(), item.getObject()
                , item.getWidth(), item.getHeight(), item.getLength(),
                item.getProductionCost(), item.getSellingPrice(), weight, item.getDiscountAmount());
        writeInConsoleWithLogger("Input kiwi count costs " + kiwi.calculatePrice() + " EUR");
        writeInConsoleWithLogger("\nInput kiwi has " + kiwi.calculateKilocalories() + " kcal");
        return kiwi;
    }

    private static Banana bananaInput(Category[] categories, Scanner scanner) {
        writeInConsoleWithLogger("\nBanana input!");
        Item item = singleItemInput(scanner, categories);
        String message = "Type weight of your chosen item(kg):";
        BigDecimal weight = bigDecimalInputMismatchChecker(scanner, message);
        Banana banana = new Banana(item.getName(), item.getObject()
                , item.getWidth(), item.getHeight(), item.getLength(),
                item.getProductionCost(), item.getSellingPrice(), weight, item.getDiscountAmount());
        writeInConsoleWithLogger("Input banana item cost you " + banana.calculatePrice() + " EUR");
        writeInConsoleWithLogger("\nInput banana item has " + banana.calculateKilocalories() + " kcal");
        return banana;
    }

    private static Laptop laptopInput(Category[] categories, Scanner scanner) {
        writeInConsoleWithLogger("\nLaptops Input");
        Item item = singleItemInput(scanner, categories);
        writeInConsoleWithLogger("Type duration of guarantee value in years:");
        Integer guarantee = scanner.nextInt();;
        writeInConsoleWithLogger("Type tax amount in range from 0% to 100% without % sign:");
        BigDecimal taxOfProduct = scanner.nextBigDecimal().multiply(BigDecimal.valueOf(0.01));
        scanner.nextLine();

        Laptop laptop = new Laptop(item.getName(), item.getObject()
                , item.getWidth(), item.getHeight(), item.getLength(),
                item.getProductionCost(), item.getSellingPrice(), item.getDiscountAmount(), guarantee);

        writeInConsoleWithLogger("Price of laptop with discount is:" + laptop.getSellingPrice());
        laptop.setSellingPrice(laptop.getSellingPrice().multiply(taxOfProduct).add(laptop.getSellingPrice()));
        writeInConsoleWithLogger("\nPrice of laptop with added tax is:" + laptop.getSellingPrice());

        return laptop;
    }

    public static Item[] itemPicker(Item[] items, Item[] pickedItems, Scanner scanner) {
        logger.info("Choosing items for store!");
        int pick = 0;
        boolean continueLoop = false;
        for (int i = 0; i < pickedItems.length; i++) {
            do {
                try {

                    for (int j = 0; j < items.length; j++)
                        writeInConsoleWithLogger((j + 1) + ". " + items[j].getName()+"\n");
                    String messageForInput = (i + 1) + ".item pick\nPick(1-" + items.length + "): ";
                    pick = integerInputMismatchChecker(scanner, messageForInput, 1, items.length)-1;
                    if (i >= 1) {
                        Item pickedItem = items[pick];
                        continueLoop = sameItemInputChecker(pickedItems, pickedItem);
                    }

                } catch (SameItemException ex) {
                    continueLoop = true;
                    writeInConsoleWithLogger(ex.getMessage());
                    writeInFIleWithLogger("Same item picked" + ex.getMessage());
                }
            } while (continueLoop);
            pickedItems[i] = items[pick];
        }
        return pickedItems;
    }
}
