package production.genericsi;

import production.model.Item;
import production.model.Store;
import production.model.Technical;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class TechnicalStore<T extends Technical> extends Store {
    private final List<T> technicalItems;

    public TechnicalStore(String name, String webAddress, Set<Item> items) {
        super(name, webAddress, items);
        this.technicalItems = new ArrayList<>();
    }
    public void addTechnicalItem(T item) {
        technicalItems.add(item);
    }
    public List<T> getTechnicalItems() {
        return technicalItems;
    }
}
