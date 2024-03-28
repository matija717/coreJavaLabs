package production.util;

import production.model.*;
import production.sort.ProductionSorter;

import java.util.*;
import java.util.stream.Collectors;

import static production.util.LoggerMethods.writeInConsoleWithLogger;

public class ItemDataFilterSearchUtil {
    public static String foodWithMostCalories(List<Item> items) {
        Optional<Item> foodWithMostCalories = items.stream()
                .filter(Edible.class::isInstance)
                .map(Edible.class::cast)
                .max(Comparator.comparing(Edible::calculateKilocalories))
                .map(Item.class::cast);
        return foodWithMostCalories.map(item -> "\nMost expensive food is "
                .concat(item.getName())
                .concat(" with value of ")
                .concat(String.valueOf(((Edible) item).calculateKilocalories()))
                .concat(" kcal"))
                .orElse("No items found");
    }

    public static String findLaptopWithLongestGuaranteeValue(List<Item> laptops) {
        Optional<Laptop> laptopWithLongestGuaranteeValue = laptops.stream()
                .filter(Laptop.class::isInstance)
                .map(Laptop.class::cast)
                .max(Comparator.comparing(Laptop::getYears));

        return laptopWithLongestGuaranteeValue.isEmpty() ? "No laptops found" : "Laptop with longest guarantee value is "
                + laptopWithLongestGuaranteeValue.get().getName() + " with guarantee of "
                + (laptopWithLongestGuaranteeValue).get().getYears() + " months";
    }

    public static String findMostExpensiveEdibleItem(List<Item> items) {
        Optional<Item> mostExpensiveEdibleItem = items.stream()
                .filter(Edible.class::isInstance)
                .max(Comparator.comparing(Item::getSellingPrice));

        return mostExpensiveEdibleItem.map(item -> "\nMost expensive food is "
                .concat(item.getName())
                .concat(" with cost of ")
                .concat(String.valueOf(((Edible) item).calculatePrice()))
                .concat(" EUR")).orElse("No items found");
    }

    public static String findMostCheapestAndMostExpensiveItemAmongTechnical(List<Item> allItems) {
        allItems = allItems.stream()
                .filter(Technical.class::isInstance)
                .collect(Collectors.toList());
        if (!allItems.isEmpty()) allItems.sort(new ProductionSorter());
        return "Most cheapest item within Edibles is "
                .concat(allItems.getFirst().getName())
                .concat(" and most expensive item is ")
                .concat(allItems.getLast().getName());
    }


    public static String findMostCheapestAndMostExpensiveItemFromEdible(List<Item> allItems) {
        allItems = allItems.stream()
                .filter(Edible.class::isInstance)
                .collect(Collectors.toList());
        if (!allItems.isEmpty()) {
            allItems.sort(new ProductionSorter());
            return "\nMost cheapest item within Edibles is "
                    .concat(allItems.getFirst().getName())
                    .concat(" and most expensive item is ")
                    .concat(allItems.getLast().getName());
        } else return "\nNo items to sort";


    }

    public static void cheapestAndMostExpensiveItemPerCategory(List<Category> categories, List<Item> allItems) {
        Map<Category, List<Item>> categoryItemMap = new HashMap<>();

        for (Category category : categories) {
            categoryItemMap.put(category, new ArrayList<>());
            for (Item item : allItems) {
                if (item.getObject().equals(category)) {
                    categoryItemMap.get(category).add(item);
                }
            }
            writeInConsoleWithLogger(findCheapestAndMostExpensiveArticlePerCategory(category, categoryItemMap));
        }

    }

    private static String findCheapestAndMostExpensiveArticlePerCategory
            (Category category, Map<Category, List<Item>> itemsOfOneCategory) {
        itemsOfOneCategory.get(category).sort(new ProductionSorter());
        if (itemsOfOneCategory.get(category).isEmpty()) {
            return "\nThis "
                    .concat(category.getName())
                    .concat(" category contains no item");
        } else {
            return "\nMost expensive article in "
                    .concat(category.getName())
                    .concat(" category is ")
                    .concat(itemsOfOneCategory.get(category).getFirst().getName())
                    .concat("and the cheapest article is ")
                    .concat(itemsOfOneCategory.get(category).getLast().getName());
        }
    }

    public static String findCheapestItem(List<Store> stores) {
        Optional<Item> cheapestItem = stores.stream().flatMap(s -> s.getItems().stream()).
                min(Comparator.comparing(Item::getSellingPrice));
        return cheapestItem.map(item -> "\nCheapest item in store is "
                        .concat(item.getName())
                        .concat(" with cost of ")
                        .concat(String.valueOf(item.getSellingPrice()))
                        .concat(" EUR"))
                .orElse("\nNo item found");
    }

    public static String findBiggestItem(List<Factory> factories) {
        Optional<Item> biggestItem = factories.stream()
                .flatMap(f -> f.getItems()
                        .stream())
                .max(Comparator.comparing(Item::volumeOfItemCalculation));

        return biggestItem.map(item -> "\nBiggest item in store is "
                        .concat(item.getName())
                        .concat(" with volume of ")
                        .concat(String.valueOf(item.volumeOfItemCalculation())))
                .orElse("\nNo item found");
    }
}
