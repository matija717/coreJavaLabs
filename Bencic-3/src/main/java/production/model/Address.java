package production.model;
/**
 * The Address class represents a physical address with street, house number, city, and postal code.
 */
public class Address {
    private String street;
    private String houseNumber;
    private String city;
    private String postalCode;

    /**
     * Creates a new Address object with the provided details.
     *
     * @param street      The street name.
     * @param houseNumber The house number.
     * @param city        The city name.
     * @param postalCode  The postal code.
     */
    public Address(String street, String houseNumber, String city, String postalCode) {
        this.street = street;
        this.houseNumber = houseNumber;
        this.city = city;
        this.postalCode = postalCode;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}
