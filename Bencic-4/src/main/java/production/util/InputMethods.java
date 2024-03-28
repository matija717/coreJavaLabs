package production.util;

import production.exception.SameFileErrror;
import production.exception.SameItemNameRuntimeException;
import production.model.Category;
import production.model.Discount;
import production.model.Item;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static production.main.Main.consoleLogger;
import static production.main.Main.logger;
import static production.util.InputCheckerMethods.*;
import static production.util.LoggerMethods.writeInConsoleWithLogger;

public class InputMethods {
    private static final int NUMBER_OF_CATEGORIES = 3;

    static Item singleItemInput(Scanner scanner, List<Category> categories) {
        logger.error("Input item!");
        consoleLogger.info("\nName:");
        String name = scanner.nextLine();
        writeInConsoleWithLogger("Pick category from 1 to " + categories.size());
        for (int j = 0; j < categories.size(); j++) {
            writeInConsoleWithLogger("\n"+(j + 1) + ". " + categories.get(j).getName());
        }
        String messageForInput = "\nPick:";
        int categoryPick = integerInputMismatchChecker(scanner, messageForInput,
                1, categories.size()) - 1;
        messageForInput = "Width:";
        BigDecimal width = bigDecimalInputMismatchChecker(scanner, messageForInput);
        messageForInput = "Length:";
        BigDecimal length = bigDecimalInputMismatchChecker(scanner, messageForInput);
        messageForInput = "Height:";
        BigDecimal height = bigDecimalInputMismatchChecker(scanner, messageForInput);
        messageForInput = "Production cost:";
        BigDecimal productionCost = bigDecimalInputMismatchChecker(scanner, messageForInput);
        messageForInput = "Selling price:";
        BigDecimal sellingPrice = bigDecimalInputMismatchChecker(scanner, messageForInput);
        messageForInput = "Type discount amount in range from 0% to 100% without % sign:";
        BigDecimal discountAmount = bigDecimalInputMismatchChecker(scanner, messageForInput)
                .multiply(BigDecimal.valueOf(0.01));
        sellingPrice = sellingPrice.add(sellingPrice.negate().multiply(discountAmount));

        return new Item(name, categories.get(categoryPick), width, height,
                length, productionCost, sellingPrice, new Discount(discountAmount));
    }

    public static List <Category> categoriesInput(Scanner scanner) {
        logger.error("User is writing categories input");
        writeInConsoleWithLogger("Categories input!");
        List<Category> categories = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_CATEGORIES; i++) {
            if(i==0)writeInConsoleWithLogger("\n");
            writeInConsoleWithLogger( String.valueOf(i + 1).concat(".category"));
            categories.add(inputCategory(scanner, categories));
        }
        logger.error("Categories input done!");
        return categories;
    }

    private static Category inputCategory(Scanner scanner, List <Category> categories) {
        boolean continueLoop;
        String name = "";
        do {
            try {
                consoleLogger.info("\nName:");
                name = scanner.nextLine();
                continueLoop = sameNameChecker(name, categories);


            } catch (SameItemNameRuntimeException ex) {
                consoleLogger.info(ex.getMessage());
                logger.error("User input same name".concat(ex.getMessage()));
                continueLoop = true;

            }
        } while (continueLoop);


        String description = "";
        do {
            try {
                writeInConsoleWithLogger("Description:");
                description = scanner.nextLine();
                continueLoop = sameCategoryDescriptionInput(description, categories);
            } catch (SameFileErrror ex) {
                writeInConsoleWithLogger(ex.getMessage());
                logger.error(ex.getMessage());
                continueLoop = true;

            }
        } while (continueLoop);

        return new Category(description, name);

    }

}
