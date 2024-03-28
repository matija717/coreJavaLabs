package production.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import production.model.*;
import production.util.PrintUtil;

import java.util.List;
import java.util.Optional;


import static production.sort.SortMethods.*;


import static production.util.FactoriesAndStoresFileInputUtil.*;
import static production.util.CategoryAndItemsInputUtils.*;
import static production.util.ItemDataFilterSearchUtil.*;

import static production.util.LoggerMethods.writeInConsoleWithLogger;
import static production.util.PrintUtil.printNumberOfItemsInStores;

public class Main {
    public static final Logger logger = LoggerFactory.getLogger("Bencic-6.production.main.Main");
    public static final Logger consoleLogger = LoggerFactory.getLogger("Bencic-6.production.main.MainConsole");

    public static void main(String[] args) {
        logger.error("Program startup");
        List<Category> categories = categoriesViaFileInput();
        List<Item> allItems = allItemsInput();
        List<Store> stores = storesInput(allItems);
        List<Address> addresses=addressInput();
        List<Factory> factories = factoriesInput(addresses,allItems);

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
        Optional<List<Item>> sortedItems = sortingItemsWithDiscountOut(allItems);
        sortedItems.ifPresent(PrintUtil::printOutItemsWithDiscount);
        printNumberOfItemsInStores(stores);

        logger.error("Finding laptop with longest guarantee!");
        writeInConsoleWithLogger(findLaptopWithLongestGuaranteeValue(allItems));

        logger.error("Finding food with most calories!");
        writeInConsoleWithLogger(foodWithMostCalories(allItems));

        logger.error("Finding most expensive food");
        writeInConsoleWithLogger(findMostExpensiveEdibleItem(allItems));

        logger.error("Finding cheapest and biggest item");
        cheapestAndMostExpensiveItemPerCategory(categories, allItems);

        writeInConsoleWithLogger(findMostCheapestAndMostExpensiveItemFromEdible(allItems));
        writeInConsoleWithLogger(findMostCheapestAndMostExpensiveItemAmongTechnical(allItems));


        writeInConsoleWithLogger(findCheapestItem(stores));
        writeInConsoleWithLogger(findBiggestItem(factories));
        serializeFactoriesAndStores(factories,stores);
    }
}
