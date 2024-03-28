package production.model;

import java.io.Serializable;
import java.util.Set;


public class Store extends NamedEntity implements Serializable {

    private String webAddress;
    private transient Set<Item> items;

    public Store(String name,Long id, String webAddress, Set<Item> items) {
        super(name, id);
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
