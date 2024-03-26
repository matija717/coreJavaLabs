package production.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import production.model.*;

import java.util.List;
import java.util.Scanner;

import static production.sort.SortMethods.lambdaSortingItemsByVolume;
import static production.sort.SortMethods.normalSortingItemsByVolume;
import static production.util.FactoriesAndStoresInputUtil.factoriesInput;
import static production.util.FactoriesAndStoresInputUtil.storesInput;
import static production.util.InputMethods.categoriesInput;
import static production.util.ItemDataFilterSearchUtil.*;
import static production.util.ItemsInputUtil.articlesSelectionInput;
import static production.util.LoggerMethods.writeInConsoleWithLogger;

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

        logger.info("Sorting items by volume with lambda");
        lambdaSortingItemsByVolume(stores);
        logger.info("Sorting items by volume without lambda");
        normalSortingItemsByVolume(stores);
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
/* public static void main(String[] args) {
        logger.info("Program startup");
        Scanner scanner = new Scanner(System.in);
        List<Category> categories = categoriesInput(scanner);
        // Collect input data for various product types (Bananas, Kiwis, Laptops, Items)
        // and stores and factories that deal with these products.

        Articles articles = articlesSelectionAndInput(categories, scanner);
        itemsWithGreaterAverageVolumeLambdaCalculation(articles.getItems());
        itemsWithGreaterAverageVolumeWithoutLambdaCalculation(articles.getItems());

        FoodStore foodStore = new FoodStore<>("Tvornica", "www.foodara.com"
                , new LinkedHashSet<>(articles.getItems()), edibleItemsInputer(articles));
        foodStore.setItems(itemSortingWithLambda(foodStore.getItems()));
        itemSortingWithoutLambda(foodStore.getItems());

        TehnicalStore tehnicalStore = new TehnicalStore<>("Trgovina tehnicka", "www.tehnika.com"
                , new LinkedHashSet<>(articles.getItems()), technicalItemsInputer(articles));
        tehnicalStore.setItems(itemSortingWithLambda(tehnicalStore.getItems()));
        itemSortingWithoutLambda(tehnicalStore.getItems());
        try {
            allItemsSortingWithGreaterThanZeroDiscount(articles);
        } catch (NoItemException ex) {
            System.out.println(ex.getMessage());
        }


        cheapestAndMostExpensiveItemPerCategory(categories, articles);
        System.out.println(mostExpensiveAndCheapestItems(articles));

        List<Factory> factories = factoriesInput(scanner, articles.getItems());
        List<Store> stores = storesInput(scanner, articles.getItems());
        stores.add(tehnicalStore);
        stores.add(foodStore);
        stores.forEach(store ->
                System.out.println("Store:"
                        .concat(store.getName())
                        .concat(" Number of Items:")
                        .concat(String.valueOf(store.getItems().size()))));
        stores.forEach(store -> store
                .getItems()
                .forEach(Item::toString));
        //average item number
        int averageItemNumber = stores.stream()
                .mapToInt(store -> store.getItems().size())
                .sum();
        averageItemNumber = averageItemNumber / stores.size();
        int finalAverageItemNumber = averageItemNumber;
        List<Store> storesWithAboveAverageItems = stores.stream()
                .filter(store -> store.getItems().size() > finalAverageItemNumber)
                .toList();
        System.out.println(storesWithAboveAverageItems);

        System.out.println(findBiggestItemInFactories(factories));
        System.out.println(findCheapestItemInStores(stores));

        logger.info("Finding laptop with longest guarantee!");
        // Find and display information about the laptop with the longest guarantee.
        try {
            System.out.println(findLaptopWithLongestGuaranteeValue(articles.getLaptops()));
        } catch (NoItemException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println(mostExpensiveAndCheapestItems(articles));

        List<Item> listaNajjeftinijih = thereeMostCheapestItems(articles);
        listaNajjeftinijih.stream().forEach(Item::toString);
        threeMostExpensiveItems(articles, listaNajjeftinijih);

        bellowAndAboveAverageChecker(allItemsReturner(articles))

    }*/
