package production.model;

import java.math.BigDecimal;

public class Kiwi extends Item implements Edible {

    private BigDecimal weight;

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public Kiwi(String name,Long id, Category object, BigDecimal width,
                BigDecimal height, BigDecimal length, BigDecimal productionCost,
                BigDecimal sellingPrice, BigDecimal weight, Discount discountAmount) {
        super(name,id, object, width, height, length, productionCost, sellingPrice, discountAmount);
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
        return new BigDecimal("88").multiply(weight);
    }
    @Override
    public BigDecimal calculatePrice() {
        return getSellingPrice().multiply(weight);
    }
}
