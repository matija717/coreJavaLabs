package hr.java.shop.bencic7.production.model;

import java.math.BigDecimal;

/**
 * The Edible interface represents objects that can be consumed and provides methods to calculate
 * kilocalories and price based on weight and price per kilogram.
 */
public interface Edible {
    /**
     * Calculate the kilocalories in a given weight of an edible item.
     *
     * @param weight The weight of the edible item in kilograms.
     * @return The total kilocalories in the specified weight of the item.
     */
    Integer calculateKilocalories(Integer weight);

    /**
     * Calculate the total price for a given weight of an edible item based on the price per kilogram.
     *
     * @param pricePerKilo The price per kilogram of the edible item.
     * @param weight       The weight of the edible item in kilograms.
     * @return The total price for the specified weight of the item.
     */
    BigDecimal calculatePrice(BigDecimal pricePerKilo, BigDecimal weight);
}
