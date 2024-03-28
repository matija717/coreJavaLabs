package production.sort;

import production.model.Item;
import production.model.Store;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

import static production.util.LoggerMethods.writeInConsoleWithLogger;

public class SortMethods {
    private SortMethods(){
        throw new AssertionError("Sort methods class should not be instantiated");
    }
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
        writeInConsoleWithLogger("\nVolume sorting with lambda:"
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
        writeInConsoleWithLogger("\nVolume sorting without lambda:"
                .concat(String.valueOf(endTime - startTime)));
    }

    public static void lambdaSortingItemsAboveAverageVolume(List<Item> items) {
        long startTime = System.nanoTime();
        BigDecimal averageVolume = items
                .stream()
                .map(Item::volumeOfItemCalculation)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .divide(BigDecimal.valueOf(items.size()), RoundingMode.HALF_UP);
        List<Item> itemsAboveAveragePrice = items
                .stream()
                .filter(item -> (item.volumeOfItemCalculation()).compareTo(averageVolume) > 0)
                .toList();
        if(!itemsAboveAveragePrice.isEmpty()){
        writeInConsoleWithLogger(
                String.valueOf(itemsAboveAveragePrice.stream()
                        .map(Item::getSellingPrice)
                        .reduce(BigDecimal.ZERO, BigDecimal::add)
                        .divide(BigDecimal.valueOf(itemsAboveAveragePrice.size()), RoundingMode.HALF_UP)));}
        else {writeInConsoleWithLogger("Items above Average is empty");}
        long endTime = System.nanoTime();
        writeInConsoleWithLogger("\nLambda time for sorting items with greater average volume:"
                .concat(String.valueOf(endTime - startTime)));
    }

    public static void normalSortingItemsAboveAverageVolume(List<Item> items) {
        long startTime = System.nanoTime();
        BigDecimal averageSum = BigDecimal.ZERO;
        for (Item value : items) {
            averageSum = averageSum.add(value.volumeOfItemCalculation());
        }
        averageSum = averageSum.divide(BigDecimal.valueOf(items.size()), RoundingMode.HALF_UP);
        BigDecimal itemsAboveAveragePrice = BigDecimal.ZERO;
        for (Item item : items) {
            if ((item.volumeOfItemCalculation()).compareTo(averageSum) > 0) {
                itemsAboveAveragePrice = itemsAboveAveragePrice.add(item.getSellingPrice());
            }
        }
        long endTime = System.nanoTime();
        writeInConsoleWithLogger("\nTime for comparing volume without lambda is:" + (endTime - startTime));
    }

    public static void lambdaSortingStoresAboveAverageItemNumber(List<Store> stores) {
        long startTime = System.nanoTime();

        int totalItemNumber = stores.stream()
                .mapToInt(store -> store.getItems().size())
                .sum();
        int averageItemNumber = totalItemNumber / stores.size();
        List<Store> storesAboveAverage = stores.stream()
                .filter(store -> store.getItems().size() > averageItemNumber)
                .toList();

        long endTime = System.nanoTime();

        writeInConsoleWithLogger("\nTime to find out items above average item number with lambda took "
                .concat(String.valueOf(endTime - startTime)));

        writeInConsoleWithLogger("\nStores above average item number:");
        storesAboveAverage.forEach(store -> writeInConsoleWithLogger(store.getName()
                .concat(" ")));
    }
    public static void normalSortingStoresAboveAverageItemNumber(List<Store> stores) {
        long startTime = System.nanoTime();
        int totalItemNumber = 0;
        for (Store store : stores) {
            totalItemNumber += store.getItems().size();
        }
        int averageItemNumber = totalItemNumber / stores.size();
        List<Store> storesAboveAverage = new ArrayList<>();
        for (Store store : stores) {
            if (store.getItems().size() > averageItemNumber) {
                storesAboveAverage.add(store);
            }
        }
        stores.clear();
        stores.addAll(storesAboveAverage);

        long endTime = System.nanoTime();
        writeInConsoleWithLogger("\nTime to find out items above average item number without lambda took "
                .concat(String.valueOf(endTime - startTime)));
    }
    public static Optional<List<Item>> sortingItemsWithDiscountOut(List<Item> items){
        List<Item> filteredItems = items.stream()
                .filter(item -> item.getDiscountAmount().discountAmount().compareTo(BigDecimal.ZERO) > 0)
                .toList();

        return filteredItems.isEmpty() ? Optional.empty() : Optional.of(filteredItems);
    }

}