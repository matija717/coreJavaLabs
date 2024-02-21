package production.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * The Discount record represents a discount amount applied to an item's price.
 */
public record Discount(BigDecimal discountAmount) implements Serializable {


    public  Discount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    @Override
    public boolean equals(Object obj) {
        return false;
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
