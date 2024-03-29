package production.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import production.model.*;

import java.util.Scanner;

import static production.util.FactoriesAndStoresInputUtil.factoriesInput;
import static production.util.FactoriesAndStoresInputUtil.storesInput;

import static production.util.InputMethods.categoriesInput;
import static production.util.ItemDataFilterSearchUtil.*;
import static production.util.ItemsInputUtil.articlesSelectionInput;
import static production.util.LoggerMethods.writeInConsoleWithLogger;
import static production.util.LoggerMethods.writeInFIleWithLogger;

public class Main {
    public static final Logger logger = LoggerFactory.getLogger("Bencic-3.production.main.Main");
    public static void main(String[] args) {
        writeInFIleWithLogger("Program startup");
        logger.info("Yea");
        Scanner scanner = new Scanner(System.in);
        Category[] categories = categoriesInput(scanner);
        Item[] allItems = articlesSelectionInput(scanner, categories);
        Store[] stores = storesInput(scanner, allItems);
        Factory[] factories = factoriesInput(scanner, allItems);
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
        Item cheapestItem = findCheapestItem(stores);
        Item biggestItem = findBiggestItem(factories);
        writeInConsoleWithLogger("Article with biggest volume is " + biggestItem.getName());
        writeInConsoleWithLogger("Article with cheapest price is " + cheapestItem.getName());
    }
}
