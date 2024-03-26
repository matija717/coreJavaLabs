package production.model;

import production.enume.Cities;


/**
 * The Address class represents a physical address with street, house number, city, and postal code.
 */
public class Address {
    private String street;
    private String houseNumber;
    private Cities city;

    public Address(String street, String houseNumber, Cities city) {
        this.street = street;
        this.houseNumber = houseNumber;
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
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
