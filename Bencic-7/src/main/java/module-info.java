module hr.java.shop.bencic7 {
    requires javafx.controls;
    requires javafx.fxml;


    opens hr.java.shop.bencic7 to javafx.fxml;
    exports hr.java.shop.bencic7.controllers;
    opens hr.java.shop.bencic7.controllers to javafx.fxml;
}