package production.model;

import java.math.BigDecimal;

/**
 * The Banana class represents a type of item that is edible and extends the Item class.
 * It includes specific properties related to bananas, such as weight and calorie content.
 */
public class Banana extends Item implements Edible {
    private final BigDecimal CALORIES_PER_KILO = new BigDecimal("88");
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
    public Banana(String name, Category object, BigDecimal width,
                  BigDecimal height, BigDecimal length, BigDecimal productionCost,
                  BigDecimal sellingPrice, BigDecimal weight, Discount discountAmount) {
        super(name, object, width, height, length, productionCost, sellingPrice, discountAmount);
        this.weight = weight;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }


    @Override
    public BigDecimal calculateKilocalories() {
        return weight.multiply(CALORIES_PER_KILO);
    }


    @Override
    public BigDecimal calculatePrice() {
        return getSellingPrice().multiply(weight);
    }
}
