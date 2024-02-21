package hr.java.shop.bencic7.production.enume;

public enum Cities {
    ZLATAR("49250","ZLATAR"),
    ZAGREB("10000","ZAGREB"),
    ZABOK("49210", "ZABOK"),
    KRAPINA("49000","KRAPINA"),
    SPLIT("21000","SPLIT");

    private String postalCode;
    private String city;

    Cities(String postalCode, String city) {

        this.postalCode = postalCode;
        this.city = city;
    }

    public String getCityName() {
        return city;
    }

    public String getPostalCode() {
        return postalCode;
    }
}
