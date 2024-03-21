package production.model;

import java.math.BigDecimal;

public record Discount(BigDecimal discountAmount) {


    public Discount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    @Override
    public BigDecimal discountAmount() {
        return discountAmount;
    }
}
