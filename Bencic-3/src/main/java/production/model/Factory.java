package production.model;

/**
 * The Factory class represents a manufacturing facility with a name, address, and a list of items produced.
 */
public class Factory extends NamedEntity {

    private Address address;
    private Item[] items;

    /**
     * Creates a new Factory object with the provided details.
     *
     * @param name    The name of the factory.
     * @param address The physical address of the factory.
     * @param items   An array of items produced by the factory.
     */
    public Factory(String name, Address address, Item[] items) {
        super(name);
        this.address = address;
        this.items = items;
    }


    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Item[] getItems() {
        return items;
    }

    public void setItems(Item[] items) {
        this.items = items;
    }
}
