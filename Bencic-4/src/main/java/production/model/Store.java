package production.model;

import java.util.HashSet;
import java.util.Set;

/**
 * The Store class represents a retail store with a name, web address, and a list of items available for sale.
 */
public class Store extends NamedEntity {

    private String webAddress;
    private Set<Item> items;

    /**
     * Creates a new Store object with the provided details.
     *
     * @param name       The name of the store.
     * @param webAddress The web address or URL of the store.
     * @param items      An array of items available for sale in the store.
     */
    public Store(String name, String webAddress, Set<Item> items) {
        super(name);
        this.webAddress = webAddress;
        this.items = items;
    }

    @Override
    public String toString() {
        return "STORE IME : "+getName();
    }

    public String getWebAddress() {
        return webAddress;
    }

    public void setWebAddress(String webAddress) {
        this.webAddress = webAddress;
    }

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
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
