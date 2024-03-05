package production.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Kiwi extends Item implements Edible{

    private final BigDecimal CALORIES_PER_KILO = new BigDecimal("88");
    private BigDecimal weight;

    public Kiwi(String name, Category object, BigDecimal width,
                  BigDecimal height, BigDecimal length, BigDecimal productionCost,
                  BigDecimal sellingPrice, BigDecimal weight,Discount discountAmount
    ,int identifier, LocalDateTime createdLocalDateTime,Ingredient ingredient) {
        super(name, object, width, height, length, productionCost, sellingPrice,
                discountAmount,identifier,createdLocalDateTime,ingredient);
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
