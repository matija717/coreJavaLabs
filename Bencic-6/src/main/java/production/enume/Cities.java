package production.enume;

public enum Cities {
    ZLATAR("49250","ZLATAR"),
    ZAGREB("10000","ZAGREB"),
    ZABOK("49210", "ZABOK"),
    KRAPINA("49000","KRAPINA"),
    SPLIT("21000","SPLIT");

    private final String postalCode;
    private final String city;

    private Cities(String postalCode, String city) {

        this.postalCode = postalCode;
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public String getPostalCode() {
        return postalCode;
    }
}
