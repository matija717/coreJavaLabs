package hr.java.shop.bencic8.utils;

import javafx.scene.control.Alert;

public class FxDialogUtil {
    public static Alert getAlert(Alert.AlertType alertType, String title, String headerText, String contentText) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        return alert;
    }
    public static void showSearchAlert(String name, String category) {
        Alert alert = getAlert(Alert.AlertType.INFORMATION, "Search information",
                "On given name: '" + name + "' and category '" + category + "' ", "Found");
        alert.showAndWait();
    }
    public static void showAddAlert(String name){
        Alert alert = getAlert(Alert.AlertType.INFORMATION, "Save information",
                "Choosen item: '" + name + "'", "Sucssesfully added to list");
        alert.showAndWait();
    }
    public static void showErrorAlert(String name){
        Alert alert = getAlert(Alert.AlertType.ERROR, "Input error in '"+name+"' class",
                "Data about new object are not saved", "Please fill up all needed fields");
        alert.showAndWait();
    }
}
