package production.genericsi;

import production.model.Edible;
import production.model.Item;
import production.model.Store;

import java.util.List;
import java.util.Set;

public class FoodStore<T extends Edible> extends Store {
    /**
     * Creates a new Store object with the provided details.
     *
     * @param name       The name of the store.
     * @param webAddress The web address or URL of the store.
     * @param items      An array of items available for sale in the store.
     */
    private List<T> edibleItems;
    public FoodStore(String name,Long id, String webAddress, Set<Item> items, List<T> edibleItems) {
        super(name,id, webAddress, items);
        this.edibleItems=edibleItems;
    }

}
