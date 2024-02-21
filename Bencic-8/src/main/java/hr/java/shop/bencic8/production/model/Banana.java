package hr.java.shop.bencic8.production.model;

import java.math.BigDecimal;

/**
 * The Banana class represents a type of item that is edible and extends the Item class.
 * It includes specific properties related to bananas, such as weight and calorie content.
 */
public class Banana extends Item implements Edible {
    private final Integer CALORIES_PER_KILO = 88;
    private BigDecimal weight;

    /**
     * Creates a new Banana object with the provided details.
     *
     * @param name           The name of the banana.
     * @param object         The category of the banana.
     * @param width          The width of the banana.
     * @param height         The height of the banana.
     * @param length         The length of the banana.
     * @param productionCost The cost of producing the banana.
     * @param sellingPrice   The price at which the banana is sold.
     * @param weight         The weight of the banana.
     * @param discountAmount The discount applied to the banana's selling price.
     */
    public Banana(String name,Long id, Category object, BigDecimal width,
                  BigDecimal height, BigDecimal length, BigDecimal productionCost,
                  BigDecimal sellingPrice,Discount discountAmount, BigDecimal weight) {
        super(name,id, object, width, height, length, productionCost, sellingPrice, discountAmount);
        this.weight = weight;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    /**
     * Calculates the kilocalories in the given weight of bananas based on a standard calorie value per kilogram.
     *
     * @param weight The weight of the bananas in kilograms.
     * @return The total kilocalories in the specified weight of bananas.
     */
    @Override
    public Integer calculateKilocalories(Integer weight) {
        return weight * CALORIES_PER_KILO;
    }

    /**
     * Calculates the total price of a given weight of bananas based on the price per kilogram.
     *
     * @param pricePerKilo The price per kilogram of bananas.
     * @param weight       The weight of the bananas in kilograms.
     * @return The total price for the specified weight of bananas.
     */
    @Override
    public BigDecimal calculatePrice(BigDecimal pricePerKilo, BigDecimal weight) {
        return pricePerKilo.multiply(weight);
    }
}
