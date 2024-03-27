package production.util;

import production.exception.SameItemException;
import production.model.*;

import java.math.BigDecimal;
import java.util.*;

import static production.main.Main.logger;
import static production.util.InputCheckerMethods.*;
import static production.util.InputCheckerMethods.bigDecimalInputMismatchChecker;
import static production.util.InputMethods.singleItemInput;
import static production.util.LoggerMethods.writeInConsoleWithLogger;
import static production.util.LoggerMethods.writeInFIleWithLogger;

public class ItemsInputUtil {
    private static final Integer NUMBER_OF_ITEMS = 3;

    public static List<Item> articlesSelectionInput(Scanner scanner, List<Category> categories) {
        List<Item> items = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_ITEMS; i++) {
            writeInConsoleWithLogger("\n" + (i + 1) + ". item choose lap");
            String message = "Choose edibles(1) or (normal item or laptop) (2) to input:";
            int edibleOrNonEdiblePicker = integerInputMismatchChecker(scanner, message
                    , 1, 2);
            scanner.nextLine();
            String input = ". input!";
            if (edibleOrNonEdiblePicker == 1) {
                message = "Chose between (1)" + Banana.class.getSimpleName() +
                        " or (2)" + Kiwi.class.getSimpleName() + ": ";
                int pickerBetweenBananasAndKiwis = integerInputMismatchChecker(scanner, message
                        , 1, 2);
                scanner.nextLine();
                if (pickerBetweenBananasAndKiwis == 1) {
                    writeInConsoleWithLogger("\nBanana picked up for " + (i + 1) + input);
                    items.add(bananaInput(categories, scanner));
                } else if (pickerBetweenBananasAndKiwis == 2) {
                    writeInConsoleWithLogger("\nKiwi picked up for " + (i + 1) + input);
                    items.add(kiwiInput(categories, scanner));
                }
            } else if (edibleOrNonEdiblePicker == 2) {
                message = "Choose normal items(1) or laptops(2) to input:";
                int pickerBetweenNormalItemAndLaptop = integerInputMismatchChecker(scanner, message
                        , 1, 2);
                scanner.nextLine();
                if (pickerBetweenNormalItemAndLaptop == 1) {
                    writeInConsoleWithLogger("\nNormal item picked up for " + (i + 1) + input);
                    items.add(singleItemInput(scanner, categories));
                } else if (pickerBetweenNormalItemAndLaptop == 2) {
                    writeInConsoleWithLogger("\nLaptop picked up for " + (i + 1) + input);
                    items.add(laptopInput(categories, scanner));
                }
            }
        }
        return items;
    }

    private static Kiwi kiwiInput(List<Category> categories, Scanner scanner) {
        Item item = singleItemInput(scanner, categories);
        writeInConsoleWithLogger("Type weight of your chosen item(kg):");
        BigDecimal weight = bigDecimalInputMismatchChecker(scanner, "\nWeight:");

        Kiwi kiwi = new Kiwi(item.getName(), item.getObject()
                , item.getWidth(), item.getHeight(), item.getLength(),
                item.getProductionCost(), item.getSellingPrice(), weight, item.getDiscountAmount());
        writeInConsoleWithLogger("\nInput kiwi count costs " + kiwi.calculatePrice() + " EUR");
        writeInConsoleWithLogger("\nInput kiwi has " + kiwi.calculateKilocalories() + " kcal");
        return kiwi;
    }

    private static Banana bananaInput(List<Category> categories, Scanner scanner) {
        Item item = singleItemInput(scanner, categories);
        writeInConsoleWithLogger("Type weight of your chosen item(kg):");
        BigDecimal weight = bigDecimalInputMismatchChecker(scanner, "\nWeight:");

        Banana banana = new Banana(item.getName(), item.getObject()
                , item.getWidth(), item.getHeight(), item.getLength(),
                item.getProductionCost(), item.getSellingPrice(), weight, item.getDiscountAmount());
        writeInConsoleWithLogger("\nInput banana item cost you " + banana.calculatePrice() + " EUR");
        writeInConsoleWithLogger("\nInput banana item has " + banana.calculateKilocalories() + " kcal");
        return banana;
    }

    private static Laptop laptopInput(List<Category> categories, Scanner scanner) {
        Item item = singleItemInput(scanner, categories);
        writeInConsoleWithLogger("Type duration of guarantee value in years:");
        Integer guarantee = scanner.nextInt();
        scanner.nextLine();
        Laptop laptop = new Laptop(item.getName(), item.getObject()
                , item.getWidth(), item.getHeight(), item.getLength(),
                item.getProductionCost(), item.getSellingPrice(), item.getDiscountAmount(), guarantee);

        writeInConsoleWithLogger("\nPrice of laptop with discount is:" + laptop.getSellingPrice());
        return laptop;
    }

    public static Set<Item> itemPicker(List<Item> items, Scanner scanner) {
        logger.info("Choosing items for store!");
        String messageForInput = "Type number of items you want to select:";
        Set<Item> pickedItems = new HashSet<>();
        int itemNumber = integerInputMismatchChecker(scanner, messageForInput, 0, items.size());
        int pick = 0;
        boolean continueLoop = false;
        for (int i = 0; i < itemNumber; i++) {
            do {
                try {
                    for (int j = 0; j < items.size(); j++) {
                        writeInConsoleWithLogger("\n" + (j + 1) + ". " + items.get(j).getName());
                    }
                    messageForInput = "\n" + (i + 1) + ".item pick\nPick(1-" + items.size() + "): ";
                    pick = integerInputMismatchChecker(scanner, messageForInput, 1, items.size()) - 1;
                    scanner.nextLine();
                    if (i >= 1) {
                        Item pickedItem = items.get(pick);
                        continueLoop = sameItemInputChecker(pickedItems, pickedItem);
                    }

                } catch (SameItemException ex) {
                    continueLoop = true;
                    writeInConsoleWithLogger(ex.getMessage());
                    writeInFIleWithLogger("Same item picked" + ex.getMessage());
                }
            } while (continueLoop);
            pickedItems.add(items.get(pick));
        }
        return pickedItems;
    }
}
