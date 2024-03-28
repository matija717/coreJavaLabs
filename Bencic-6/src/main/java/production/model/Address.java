package production.model;

import production.enume.Cities;

public class Address extends NamedEntity {

    private String houseNumber;
    private Cities city;

    public Address(String street, Long id, String houseNumber, Cities city) {
        super(street, id);
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
