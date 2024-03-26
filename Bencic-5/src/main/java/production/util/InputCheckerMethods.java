package production.util;

import production.exception.SameFileErrror;
import production.exception.SameItemException;
import production.exception.SameItemNameRuntimeException;
import production.model.Category;
import production.model.Item;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import static production.main.Main.logger;
import static production.util.LoggerMethods.writeInConsoleWithLogger;


public class InputCheckerMethods {

    public static boolean sameCategoryDescriptionInput(String description
            , List<Category> categories) throws SameFileErrror {
        for (Category category : categories) {
            if (category.getDescription().equals(description)) {
                logger.error("Same description input error");
                throw new SameFileErrror("Same description error\nPlease try again!");
            }
        }
        return false;
    }

    static boolean sameNameChecker(String name, List<Category> categories) {
        logger.error("Now checking for same name error");
        for (Category category : categories) {
            if (category.getName().equals(name)) {
                logger.error("Same description input error");
                throw new SameItemNameRuntimeException("Same category name error\nPlease try again!");

            }
        }

        return false;
    }

    private static boolean bigDecimalLessThanZeroChecker(BigDecimal input) {
        return input.compareTo(new BigDecimal("0")) > 0;
    }

    private static boolean integerInRangeChecker(Integer input, Integer maxNumber, Integer minNumber) {
        return !(input >= minNumber && input <= maxNumber);
    }

    public static int integerInputMismatchChecker(Scanner scanner, String message,
                                                  Integer minNumber, Integer maxNumber) {
        logger.info("Checking if input integer is right and in range");
        boolean continueLoop;
        Integer picker = null;
        do {
            try {
                writeInConsoleWithLogger("\n" + message);
                picker = scanner.nextInt();
                scanner.nextLine();
                if (!integerInRangeChecker(picker, maxNumber, minNumber)) {
                    continueLoop = false;
                } else {
                    writeInConsoleWithLogger("Please insert number which is right!");
                    logger.error("Wrong number input!");
                    continueLoop = true;
                }
            } catch (InputMismatchException ex) {
                logger.error("Wrong data input!");
                writeInConsoleWithLogger("Please input numeric data!");
                scanner.nextLine();
                continueLoop = true;
            }
        } while (continueLoop);
        return picker;
    }

    static BigDecimal bigDecimalInputMismatchChecker(Scanner scanner, String message) {
        boolean continueLoop;
        BigDecimal input = BigDecimal.valueOf(0.0);
        do {
            try {
                writeInConsoleWithLogger(message);
                input = scanner.nextBigDecimal();
                scanner.nextLine();

                if (bigDecimalLessThanZeroChecker(input)) {
                    continueLoop = false;

                } else {
                    if (!bigDecimalLessThanZeroChecker(input)) {
                        writeInConsoleWithLogger("\nPlease insert number greater than zero!");
                    }
                    continueLoop = true;
                }
            } catch (InputMismatchException ex) {
                writeInConsoleWithLogger("\nPlease input numeric data!");
                scanner.nextLine();
                continueLoop = true;
            }
        } while (continueLoop);
        return input;
    }

    public static boolean sameItemInputChecker(Set<Item> pickedItems, Item selectedItem) throws SameItemException {
        for (Item item : pickedItems) {
            if (item.equals(selectedItem)) {
                throw new SameItemException("You picked same item!\nPlease try to pick different item!");
            }
        }

        return false;
    }
}
