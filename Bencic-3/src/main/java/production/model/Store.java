package production.model;

/**
 * The Store class represents a retail store with a name, web address, and a list of items available for sale.
 */
public class Store extends NamedEntity {

    private String webAddress;
    private Item[] items;

    /**
     * Creates a new Store object with the provided details.
     *
     * @param name       The name of the store.
     * @param webAddress The web address or URL of the store.
     * @param items      An array of items available for sale in the store.
     */
    public Store(String name, String webAddress, Item[] items) {
        super(name);
        this.webAddress = webAddress;
        this.items = items;
    }


    public String getWebAddress() {
        return webAddress;
    }

    public void setWebAddress(String webAddress) {
        this.webAddress = webAddress;
    }

    public Item[] getItems() {
        return items;
    }

    public void setItems(Item[] items) {
        this.items = items;
    }
}
