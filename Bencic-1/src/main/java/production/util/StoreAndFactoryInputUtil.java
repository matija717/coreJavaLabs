package production.util;


import production.model.Factory;
import production.model.Item;
import production.model.Store;

import java.util.Scanner;

import static production.util.InputMethodsUtil.singleFactoryInput;
import static production.util.InputMethodsUtil.singleStoreInput;

public class StoreAndFactoryInputUtil {
    private static final Integer NUMBER_OF_FACTORIES = 2,NUMBER_OF_STORES=2;
    public static Factory[] factoriesInput(Scanner scanner, Item[] items) {
        System.out.println("Factories input!");
        Factory[] factories = new Factory[NUMBER_OF_FACTORIES];
        for (int i = 0; i < factories.length; i++) {
            System.out.println((i + 1) + ". factory");
            factories[i] = singleFactoryInput(scanner, items);

        }
        return factories;
    }
    public static Store[] storesInput(Scanner scanner, Item[] items) {
        System.out.println("Stores input!");
        Store[] stores = new Store[NUMBER_OF_STORES];
        for (int i = 0; i < stores.length; i++) {
            System.out.println((i + 1) + ". store");
            stores[i] = singleStoreInput(scanner, items);

        }
        return stores;
    }
}
