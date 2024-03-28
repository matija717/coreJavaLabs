package production.model;


import java.io.Serializable;
import java.util.Set;

public class Factory extends NamedEntity implements Serializable {

    private final Address address;
    private final Set<Item> items;

    public Factory(String name, Long id, Address address, Set<Item> items) {
        super(name, id);
        this.address = address;
        this.items = items;
    }


    public Address getAddress() {
        return address;
    }


    public Set<Item> getItems() {
        return items;
    }

    @Override
    public String toString() {
        return "Factory name: " + getName();
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


