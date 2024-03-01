module hr.java.shop.bencic8 {
    requires javafx.controls;
    requires javafx.fxml;


    opens hr.java.shop.bencic8 to javafx.fxml;
    exports hr.java.shop.bencic8;
}