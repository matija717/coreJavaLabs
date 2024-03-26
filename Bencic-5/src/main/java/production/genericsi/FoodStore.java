package production.genericsi;

import production.model.Edible;
import production.model.Item;
import production.model.Store;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class FoodStore<T extends Edible> extends Store {

    private List<T> edibleItems;
    public FoodStore(String name, String webAddress, Set<Item> items) {
        super(name, webAddress, items);
        this.edibleItems=new ArrayList<>();
    }

    public List<T> getEdibleItems() {
        return edibleItems;
    }
    public void addEdibleItem(T item){
        edibleItems.add(item);
    }
}
