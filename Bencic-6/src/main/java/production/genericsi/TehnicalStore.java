package production.genericsi;

import production.model.Item;
import production.model.Store;
import production.model.Technical;

import java.util.List;
import java.util.Set;

public class TehnicalStore<T extends Technical> extends Store {

    /**
     * Creates a new Store object with the provided details.
     *
     * @param name       The name of the store.
     * @param webAddress The web address or URL of the store.
     * @param items      An array of items available for sale in the store.
     */
    private final List<T> technicalItems;

    public TehnicalStore(String name, Long id, String webAddress, Set<Item> items, List<T> technicalItems) {
        super(name, id, webAddress, items);
        this.technicalItems = technicalItems;
    }

    public List<T> getTechnicalItems() {
        return technicalItems;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
