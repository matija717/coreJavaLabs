package production.model;

import java.math.BigDecimal;

public record Discount(BigDecimal discountAmount) {
    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }
}
