package production.model;

import java.math.BigDecimal;

/**
 * The Banana class represents a type of item that is edible and extends the Item class.
 * It includes specific properties related to bananas, such as weight and calorie content.
 */
public class Banana extends Item implements Edible {
    private final BigDecimal CALORIES_PER_KILO = new BigDecimal("88");
    private BigDecimal weight;

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

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
