package production.util;

import production.model.*;

public class ItemDataFilterSearchUtil {
    public static Item foodWithMostCalories(Item[] items) {
        Edible foodWithMostCalories = null;
        for (Item item : items) {
            if (item instanceof Edible edible) {
                if (foodWithMostCalories == null) {
                    foodWithMostCalories =  edible;
                } else if ((edible).calculateKilocalories()
                        .compareTo(( foodWithMostCalories).calculateKilocalories()) > 0)
                    foodWithMostCalories = edible;

            }
        }
        return (Item) foodWithMostCalories;
    }

    public static String findLaptopWithLongestGuaranteeValue(Item[] laptops) {
        Laptop laptopWithLongestGuaranteeValue = null;
        for (Item l : laptops) {
            if (l instanceof Laptop laptop) {
                if (laptopWithLongestGuaranteeValue == null) {
                    laptopWithLongestGuaranteeValue = laptop;
                } else if (laptop.getYears() > ( laptopWithLongestGuaranteeValue).getYears()) {
                    laptopWithLongestGuaranteeValue = (Laptop) l;
                }
            }
        }
        return laptopWithLongestGuaranteeValue == null ? "No laptops found" : "Laptop with longest guarantee value is "
                + laptopWithLongestGuaranteeValue.getName() + " with guarantee of "
                + ( laptopWithLongestGuaranteeValue).getYears() + " months";
    }

    public static Item findMostExpensiveItem(Item[] items) {
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

    public static Item findBiggestItem(Factory[] factories) {
        Item biggestItem = factories[0].getItems()[0];
        for (Factory f : factories) {
            for (int i = 0; i < f.getItems().length; i++) {
                if (f.getItems()[0].equals(f.getItems()[i])) {
                    biggestItem = f.getItems()[i];
                } else if (biggestItem.volumeOfItemCalculation().
                        compareTo(f.getItems()[i].volumeOfItemCalculation()) < 0) {
                    biggestItem = getItemFromStoreOrFactory(f.getItems(), i);
                }
            }
        }
        return biggestItem;
    }


    public static Item findCheapestItem(Store[] stores) {
        Item cheapestItem = stores[0].getItems()[0];
        for (Store s : stores) {
            for (int i = 0; i < s.getItems().length; i++) {
                if (s.getItems()[0].equals(s.getItems()[i])) {
                    cheapestItem = s.getItems()[i];
                } else if (s.getItems()[i].getSellingPrice()
                        .compareTo(cheapestItem.getSellingPrice()) < 0) {
                    cheapestItem = getItemFromStoreOrFactory(s.getItems(), i);
                }
            }
        }
        return cheapestItem;
    }

    private static Item getItemFromStoreOrFactory(Item[] items, int i) {
        return items[i];
    }
}
