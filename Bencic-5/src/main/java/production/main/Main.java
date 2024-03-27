package production.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import production.model.*;
import production.util.PrintUtil;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import static production.sort.SortMethods.*;
import static production.util.FactoriesAndStoresInputUtil.factoriesInput;
import static production.util.FactoriesAndStoresInputUtil.storesInput;
import static production.util.InputMethods.categoriesInput;
import static production.util.ItemDataFilterSearchUtil.*;
import static production.util.ItemsInputUtil.articlesSelectionInput;
import static production.util.LoggerMethods.writeInConsoleWithLogger;
import static production.util.PrintUtil.printNumberOfItemsInStores;

public class Main {
    public static final Logger logger = LoggerFactory.getLogger("Bencic-5.production.main.Main");
    public static final Logger consoleLogger = LoggerFactory.getLogger("Bencic-5.production.main.MainConsole");

    public static void main(String[] args) {
        logger.error("Program startup");
        Scanner scanner = new Scanner(System.in);
        List<Category> categories = categoriesInput(scanner);
        List<Item> allItems = articlesSelectionInput(scanner, categories);
        List<Store> stores = storesInput(scanner, allItems);
        List<Factory> factories = factoriesInput(scanner, allItems);

        logger.info("Sorting items by volume");
        lambdaSortingItemsByVolume(stores);
        normalSortingItemsByVolume(stores);

        logger.info("Sorting items above average volume");
        lambdaSortingItemsAboveAverageVolume(allItems);
        normalSortingItemsAboveAverageVolume(allItems);

        logger.info("Sorting stores above average item number");
        lambdaSortingStoresAboveAverageItemNumber(stores);
        normalSortingStoresAboveAverageItemNumber(stores);

        logger.info("Sorting items with discount");
        Optional<List<Item>>sortedItems=sortingItemsWithDiscountOut(allItems);
        sortedItems.ifPresent(PrintUtil::printOutItemsWithDiscount);
        printNumberOfItemsInStores(stores);

        logger.error("Finding laptop with longest guarantee!");
        writeInConsoleWithLogger(findLaptopWithLongestGuaranteeValue(allItems));

        logger.error("Finding food with most calories!");
        Item mostCalorieItem = foodWithMostCalories(allItems);
        writeInConsoleWithLogger("Food with most calories is " + mostCalorieItem.getName() +
                " with value of " + ((Edible) mostCalorieItem).calculateKilocalories() + " kcal");

        logger.error("Finding most expensive food");
        Item expensiveItem = findMostExpensiveItem(allItems);
        writeInConsoleWithLogger("Most expensive food is " + expensiveItem.getName() +
                " with cost of " + ((Edible) expensiveItem).calculatePrice() + " EUR");

        logger.error("Finding cheapest and biggest item");
        cheapestAndMostExpensiveItemPerCategory(categories, allItems);

        writeInConsoleWithLogger(findMostCheapestAndMostExpensiveItemFromEdible(allItems));
        writeInConsoleWithLogger(findMostCheapestAndMostExpensiveItemAmongTechnical(allItems));

        writeInConsoleWithLogger(findCheapestItem(stores));
        writeInConsoleWithLogger(findBiggestItem(factories));
    }
}
