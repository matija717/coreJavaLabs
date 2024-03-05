package production.model;

import java.time.LocalDateTime;

public class Store extends NamedEntity {

    private String webAddress;
    private Item[] items;

    public Store(String name, String webAddress, Item[] items,int identifier, LocalDateTime createdLocalDateTime) {
        super(name,identifier,createdLocalDateTime);
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
