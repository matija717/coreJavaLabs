package hr.java.shop.bencic8.utils;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

import java.math.BigDecimal;

public class InputCheckerUtil {
    public static void restrictToAlphabeticInput(TextField textField) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("^[a-zA-Z]*$")) {
                textField.setText(oldValue);
            }
        });
    }
    public static void restrictToIntegerInput(TextField textField) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                textField.setText(newValue.replaceAll("\\D", ""));
            }
        });
    }
    public static void restrictToBigDecimalInput(TextField textField) {
        final ChangeListener<String>[] listener = new ChangeListener[]{null};
        listener[0] = (ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!isValidBigDecimal(newValue)) {
                try {
                    // Disable the listener temporarily to avoid recursion
                    textField.textProperty().removeListener(listener[0]);

                    // Revert to the old value
                    textField.setText(oldValue);
                } catch (Exception e) {
                    // Handle the exception here, e.g., log it or perform any necessary actions
                    e.printStackTrace();
                } finally {
                    // Re-enable the listener after reverting the text
                    textField.textProperty().addListener(listener[0]);
                }
            }
        };

        textField.textProperty().addListener(listener[0]);
    }

    private static boolean isValidBigDecimal(String input) {
        try {
            new BigDecimal(input);
            return true; // Input can be parsed as BigDecimal
        } catch (NumberFormatException | NullPointerException e) {
            return false; // Input is not a valid BigDecimal
        }
    }
}
