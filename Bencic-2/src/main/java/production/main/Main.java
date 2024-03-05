package production.main;

import production.model.*;


import java.util.Scanner;

import static production.util.InputMethodsForItemAndCategories.categoriesInput;
import static production.util.ItemDataFilterSearchUtil.*;
import static production.util.ItemInputUtil.articlesSelectionInput;
import static production.util.StoreAndFactoryInputUtils.factoriesInput;
import static production.util.StoreAndFactoryInputUtils.storesInput;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Category[] categories = categoriesInput(scanner);

        Item[] allItems = articlesSelectionInput(scanner, categories);
        System.out.println(findLaptopWithLongestGuaranteeValue(allItems));
        Item foodWithMaxCalories = foodWithMostCalories(allItems);
        System.out.println("Food with most calories is " + foodWithMaxCalories.getName() +
                " with value of " + ((Edible) foodWithMaxCalories).calculateKilocalories() + " kcal");

        Item mostExpensiveItem = mostExpensiveFood(allItems);
        System.out.println("Most expensive food is " + mostExpensiveItem.getName() +
                " with cost of " + ((Edible) mostExpensiveItem).calculatePrice() + " EUR");

        Store[] stores = storesInput(scanner, allItems);
        Factory[] factories = factoriesInput(scanner, allItems);
        Item cheapestItem = findCheapestItem(stores);
        Item biggestItem = findBiggestItem(factories);

        System.out.println("Article with biggest volume is " + biggestItem.getName());
        System.out.println("Article with cheapest price is " + cheapestItem.getName());
    }
}
