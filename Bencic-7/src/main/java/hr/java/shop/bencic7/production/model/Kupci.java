package hr.java.shop.bencic7.production.model;

import java.util.List;

public class Kupci extends NamedEntity{
    /**
     * Creates a new NamedEntity object with the provided name.
     *
     * @param name The name of the entity.
     * @param id
     */
    private String surname;
    private List<Item> itemsForBuy;
    private Address address;
    private String dateOfBirth;
    public Kupci(String name, Long id,String surname,List<Item> itemsForBuy,Address address,String dateOfBirth) {
        super(name, id);
        this.surname=surname;
        this.itemsForBuy=itemsForBuy;
        this.address=address;
        this.dateOfBirth=dateOfBirth;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public List<Item> getItemsForBuy() {
        return itemsForBuy;
    }

    public void setItemsForBuy(List<Item> itemsForBuy) {
        this.itemsForBuy = itemsForBuy;
    }
}
