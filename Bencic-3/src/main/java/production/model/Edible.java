package production.model;

import java.math.BigDecimal;

/**
 * The Edible interface represents objects that can be consumed and provides methods to calculate
 * kilocalories and price based on weight and price per kilogram.
 */
public interface Edible {

    BigDecimal calculateKilocalories();


    BigDecimal calculatePrice();
}
