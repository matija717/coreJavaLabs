package production.model;

import java.math.BigDecimal;

/**
 * The Kiwi class represents a type of item that is edible and extends the Item class.
 * It includes specific properties related to kiwis, such as weight and calorie content.
 */
public class Kiwi extends Item implements Edible {

    private final Integer CALORIES_PER_KILO = 88;
    private BigDecimal weight;

    /**
     * Creates a new Kiwi object with the provided details.
     *
     * @param name           The name of the kiwi.
     * @param object         The category of the kiwi.
     * @param width          The width of the kiwi.
     * @param height         The height of the kiwi.
     * @param length         The length of the kiwi.
     * @param productionCost The cost of producing the kiwi.
     * @param sellingPrice   The price at which the kiwi is sold.
     * @param weight         The weight of the kiwi.
     * @param discountAmount The discount applied to the kiwi's selling price.
     */
    public Kiwi(String name, Long id, Category object, BigDecimal width,
                BigDecimal height, BigDecimal length, BigDecimal productionCost,
                BigDecimal sellingPrice, Discount discountAmount, BigDecimal weight,Grade gradeValue) {
        super(name, id, object, width, height, length, productionCost, sellingPrice, discountAmount,gradeValue);
        this.weight = weight;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    /**
     * Calculates the kilocalories in a given weight of kiwis based on a standard calorie value per kilogram.
     *
     * @param weight The weight of the kiwis in kilograms.
     * @return The total kilocalories in the specified weight of kiwis.
     */
    @Override
    public Integer calculateKilocalories(Integer weight) {
        return CALORIES_PER_KILO * weight;
    }

    /**
     * Calculates the total price of a given weight of kiwis based on the price per kilogram.
     *
     * @param pricePerKilo The price per kilogram of kiwis.
     * @param weight       The weight of the kiwis in kilograms.
     * @return The total price for the specified weight of kiwis.
     */
    @Override
    public BigDecimal calculatePrice(BigDecimal pricePerKilo, BigDecimal weight) {
        return pricePerKilo.multiply(weight);
    }
}
