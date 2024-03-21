package production.model;

import java.math.BigDecimal;

/**
 * The Discount record represents a discount amount applied to an item's price.
 */
public record Discount(BigDecimal dA) {


    /**
     * Returns the discount amount.
     *
     * @return The discount amount as a BigDecimal.
     */
    public BigDecimal discountAmount() {
        return dA;
    }
}
