package production.sort;

import production.model.Item;
import production.model.Store;

import java.util.*;
import java.util.stream.Collectors;

import static production.util.LoggerMethods.writeInConsoleWithLogger;

public class SortMethods {
    public static void lambdaSortingItemsByVolume(List<Store> stores) {
        long startTime = System.nanoTime();
        stores.forEach(store ->
                store.setItems(
                        store.getItems()
                                .stream()
                                .sorted(Comparator.comparing(Item::volumeOfItemCalculation))
                                .collect(Collectors.toCollection(LinkedHashSet::new)))
        );
        long endTime = System.nanoTime();
        writeInConsoleWithLogger("Volume sorting with lambda:"
                .concat(String.valueOf(endTime - startTime)));

    }

    public static void normalSortingItemsByVolume(List<Store> stores) {
        long startTime = System.nanoTime();
        for (Store store : stores) {
            Set<Item> items = store.getItems();

            List<Item> itemList = new ArrayList<>(items);
            itemList.sort(Comparator.comparing(Item::volumeOfItemCalculation));

            LinkedHashSet<Item> sortedItems = new LinkedHashSet<>(itemList);
            store.setItems(sortedItems);
        }
        long endTime = System.nanoTime();
        writeInConsoleWithLogger("Volume sorting without lambda:"
                .concat(String.valueOf(endTime - startTime)));
    }
}
