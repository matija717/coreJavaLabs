package production.util;

import production.model.Factory;
import production.model.Item;
import production.model.Store;

public class ComparisonUtil {
    public static Item findCheapestItem(Store[] stores) {
        Item cheapestItem = stores[0].getItems()[0];
        for (Store s : stores) {
            for (int i = 0; i < s.getItems().length; i++) {
                if (s.getItems()[0].equals(s.getItems()[i])) {
                    cheapestItem = s.getItems()[i];
                } else if (s.getItems()[i].getSellingPrice()
                        .compareTo(cheapestItem.getSellingPrice()) < 0) {
                    cheapestItem = getItemFromArray(s.getItems(), i);
                }
            }
        }
        return cheapestItem;
    }
    public static Item findBiggestItem(Factory[] factories) {
        Item biggestItem = factories[0].getItems()[0];
        for (Factory f : factories) {
            for (int i = 0; i < f.getItems().length; i++) {
                if (f.getItems()[0].equals(f.getItems()[i])) {
                    biggestItem = f.getItems()[i];
                } else if (biggestItem.volumeOfItemCalculation().
                        compareTo(f.getItems()[i].volumeOfItemCalculation()) < 0) {
                    biggestItem = getItemFromArray(f.getItems(), i);
                }
            }
        }
        return biggestItem;
    }
    private static Item getItemFromArray(Item[] items, int i){
        return items[i];
    }
}
