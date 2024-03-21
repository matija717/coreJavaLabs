package production.model;

import java.math.BigDecimal;

/**
 * The Kiwi class represents a type of item that is edible and extends the Item class.
 * It includes specific properties related to kiwis, such as weight and calorie content.
 */
public class Kiwi extends Item implements Edible {

    private final BigDecimal CALORIES_PER_KILO = new BigDecimal("88");
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
    public Kiwi(String name, Category object, BigDecimal width,
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
        return CALORIES_PER_KILO.multiply(weight);
    }

    @Override
    public BigDecimal calculatePrice() {
        return getSellingPrice().multiply(weight);
    }
}
