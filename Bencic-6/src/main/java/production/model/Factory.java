package production.model;

import java.util.Set;

/**
 * The Factory class represents a manufacturing facility with a name, address, and a list of items produced.
 */
public class Factory extends NamedEntity {

    private Address address;
    private Set<Item> items;

    /**
     * Creates a new Factory object with the provided details.
     *
     * @param name    The name of the factory.
     * @param address The physical address of the factory.
     * @param items   An array of items produced by the factory.
     */
    public Factory(String name, Long id, Address address, Set<Item> items) {
        super(name, id);
        this.address = address;
        this.items = items;
    }


    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Set<Item> getItems() {
        return items;
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


