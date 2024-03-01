package production.main;

import production.model.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Scanner;

import static production.util.CartUtil.cartPrint;
import static production.util.CartUtil.cartsInput;
import static production.util.ComparisonUtil.findBiggestItem;
import static production.util.ComparisonUtil.findCheapestItem;
import static production.util.InputMethodsUtil.categoriesInput;
import static production.util.ItemInputUtil.itemsInput;
import static production.util.StoreAndFactoryInputUtil.factoriesInput;
import static production.util.StoreAndFactoryInputUtil.storesInput;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Category[] categories = categoriesInput(scanner);
        Item[] items = itemsInput(scanner, categories);
        Cart[] carts = cartsInput(scanner, items);
        cartPrint(carts);
        Store[] stores = storesInput(scanner, items);
        Factory[] factories = factoriesInput(scanner, items);


        Item cheapestItem = findCheapestItem(stores);
        Item biggestItem = findBiggestItem(factories);

        System.out.println("Article with biggest volume is " + biggestItem.getName());
        System.out.println("Article with cheapest price is " + cheapestItem.getName());
    }
}
