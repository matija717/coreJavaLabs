package production.util;

import production.model.*;

public class ItemDataFilterSearchUtil {
    public static Item foodWithMostCalories(Item[] items) {
        Item foodWithMostCalories = null;
        for (Item item : items) {
            if (item instanceof Edible) {
                if (foodWithMostCalories == null) foodWithMostCalories = item;

                else if (((Edible) item).calculateKilocalories()
                        .compareTo(((Edible) foodWithMostCalories).calculateKilocalories()) > 0)
                    foodWithMostCalories = item;

            }
        }
        return foodWithMostCalories;
    }

    public static String findLaptopWithLongestGuaranteeValue(Item[] laptops) {
        Item laptopWithLongestGuaranteeValue = null;
        for (Item l : laptops) {
            if (l instanceof Laptop laptop) {
                if (laptopWithLongestGuaranteeValue == null) {
                    laptopWithLongestGuaranteeValue = l;
                } else if (laptop.getYears() > ((Laptop) laptopWithLongestGuaranteeValue).getYears()) {
                    laptopWithLongestGuaranteeValue = laptop;
                }
            }

        }
        if (laptopWithLongestGuaranteeValue == null) return "No laptops found";
        else {
            return "Laptop with longest guarantee value is " + laptopWithLongestGuaranteeValue.getName()
                    + " with guarantee of " + ((Laptop) laptopWithLongestGuaranteeValue).getYears() + " months";
        }
    }

    public static Item mostExpensiveFood(Item[] items) {
        Item mostExpensiveFood = null;
        for (Item item : items) {
            if (item instanceof Edible edible) {
                if (mostExpensiveFood == null) mostExpensiveFood = item;
                else if (edible.calculatePrice()
                        .compareTo(((Edible) mostExpensiveFood).calculatePrice()) > 0) mostExpensiveFood = item;
            }
        }
        return mostExpensiveFood;
    }

    public static Item findBiggestItem(Factory[] factories) {
        Item biggestItem = factories[0].getItems()[0];
        for (Factory s : factories) {
            for (int i = 0; i < s.getItems().length; i++) {
                if (s.getItems()[0].equals(s.getItems()[i])) {
                    biggestItem = s.getItems()[i];
                } else if (biggestItem.volumeOfItemCalculation().
                        compareTo(s.getItems()[i].volumeOfItemCalculation()) < 0) {
                    biggestItem = s.getItems()[i];
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
                    cheapestItem = s.getItems()[i];
                }
            }
        }
        return cheapestItem;
    }
}
