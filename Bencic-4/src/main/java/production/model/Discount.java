package production.model;

import java.math.BigDecimal;

public record Discount(BigDecimal discountAmount) {

    @Override
    public boolean equals(Object obj) {
        return false;
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
