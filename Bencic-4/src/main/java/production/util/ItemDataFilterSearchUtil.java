package production.util;

import production.model.*;
import production.sort.ProductionSorter;


import java.util.*;

import java.util.stream.Collectors;

import static production.util.LoggerMethods.writeInConsoleWithLogger;

public class ItemDataFilterSearchUtil {
    public static Item foodWithMostCalories(List<Item> items) {
        Edible foodWithMostCalories = null;
        for (Item item : items) {
            if (item instanceof Edible edible) {
                if (foodWithMostCalories == null) {
                    foodWithMostCalories = edible;
                } else if ((edible).calculateKilocalories()
                        .compareTo((foodWithMostCalories).calculateKilocalories()) > 0)
                    foodWithMostCalories = edible;

            }
        }
        return (Item) foodWithMostCalories;
    }

    public static String findLaptopWithLongestGuaranteeValue(List<Item> laptops) {
        Laptop laptopWithLongestGuaranteeValue = null;
        for (Item l : laptops) {
            if (l instanceof Laptop laptop) {
                if (laptopWithLongestGuaranteeValue == null) {
                    laptopWithLongestGuaranteeValue = laptop;
                } else if (laptop.getYears() > (laptopWithLongestGuaranteeValue).getYears()) {
                    laptopWithLongestGuaranteeValue = (Laptop) l;
                }
            }
        }
        return laptopWithLongestGuaranteeValue == null ? "No laptops found" : "Laptop with longest guarantee value is "
                + laptopWithLongestGuaranteeValue.getName() + " with guarantee of "
                + (laptopWithLongestGuaranteeValue).getYears() + " months";
    }

    public static Item findMostExpensiveItem(List<Item> items) {
        Item mostExpensiveFood = null;
        for (Item item : items) {
            if (item instanceof Edible edible) {
                if (mostExpensiveFood == null) mostExpensiveFood = item;
                else if (edible.calculatePrice()
                        .compareTo(((Edible) mostExpensiveFood).calculatePrice()) > 0)
                    mostExpensiveFood = (Item) edible;
            }
        }
        return mostExpensiveFood;
    }

    public static String findMostCheapestAndMostExpensiveItemAmongTechnical(List<Item> allItems) {
        allItems = allItems.stream()
                .filter(Technical.class::isInstance)
                .collect(Collectors.toList());
        if (!allItems.isEmpty()) allItems.sort(new ProductionSorter());
        return "\nMost cheapest item within Edibles is "
                .concat(allItems.getFirst().getName())
                .concat(" and most expensive item is ")
                .concat(allItems.getLast().getName());
    }


    public static String findMostCheapestAndMostExpensiveItemFromEdible(List<Item> allItems) {
        allItems = allItems.stream()
                .filter(Edible.class::isInstance)
                .collect(Collectors.toList());
        if (!allItems.isEmpty()) allItems.sort(new ProductionSorter());
        return "\nMost cheapest item within Edibles is "
                .concat(allItems.getFirst().getName())
                .concat(" and most expensive item is ")
                .concat(allItems.getLast().getName());

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
    public static String findCheapestItem(List<Store> stores){
        Optional<Item> cheapestItem = stores.stream().flatMap(s -> s.getItems().stream()).
                min(Comparator.comparing(Item::getSellingPrice));
        return cheapestItem.map(item -> "\nCheapest item in store is " + item.getName() + " with cost of " +
                item.getSellingPrice() + " EUR").orElse("\nNo item found");
    }
    public static String findBiggestItem(List<Factory> factories){
        Optional<Item> biggestItem = factories.stream()
                .flatMap(f -> f.getItems()
                        .stream())
                .max(Comparator.comparing(Item::volumeOfItemCalculation));
        return biggestItem.map(item -> "\nBiggest item in among factories is "
                .concat(item.getName())
                .concat("with volume of ")
                .concat(item.volumeOfItemCalculation().toString())).orElse("No items found");
    }
}
