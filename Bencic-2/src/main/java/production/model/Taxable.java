package production.model;

import java.math.BigDecimal;

public sealed interface Taxable permits Laptop  {
    BigDecimal taxCalculation(BigDecimal sellingPrice);
}
