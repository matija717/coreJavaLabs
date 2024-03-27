package production.util;

import production.model.Item;
import production.model.Store;

import java.util.List;

import static production.util.LoggerMethods.writeInConsoleWithLogger;

public class PrintUtil {
    public static void printNumberOfItemsInStores(List<Store> stores) {
        stores.forEach(store->writeInConsoleWithLogger
                ("Number of items in " + store.getName() + ": " + store.getItems().size()));
    }
    public static void printOutItemsWithDiscount(List<Item> items){
        writeInConsoleWithLogger("\nItems with discount!");
        items.forEach(item->writeInConsoleWithLogger("\n"+item.getName()));
    }
}
