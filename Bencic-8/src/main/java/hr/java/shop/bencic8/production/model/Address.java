package hr.java.shop.bencic8.production.model;

import hr.java.shop.bencic8.production.enume.*;

/**
 * The Address class represents a physical address with street, house number, city, and postal code.
 */
public class Address extends NamedEntity {
    private String houseNumber;
    private Cities city;


    /**
     * Creates a new Address object with the provided details.
     *
     * @param houseNumber The house number.
     * @param city        The city name.
     */
    public Address(Long id,String name, String houseNumber, Cities city) {
        super(name,id);
        this.houseNumber = houseNumber;
        this.city = city;
    }





    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }


    public Cities getCity() {
        return city;
    }

    public void setCity(Cities city) {
        this.city = city;
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
