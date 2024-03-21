package production.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public non-sealed class Laptop extends Item implements Technical, Taxable {
    Integer years;
    BigDecimal tax;
    public Laptop(String name, Category object, BigDecimal width, BigDecimal height, BigDecimal length,
                  BigDecimal productionCost, BigDecimal sellingPrice, Discount discountAmount,Integer years,
                  int identifier, LocalDateTime createdLocalDateTime,Ingredient ingredient,BigDecimal tax) {
        super(name, object, width, height, length, productionCost, sellingPrice, discountAmount,
                identifier,createdLocalDateTime,ingredient);
        this.years=years;
        this.tax=tax;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public Integer getYears() {
        return years;
    }

    public void setYears(Integer years) {
        this.years = years;
    }

    public Integer durationOfGuarantee(Integer years) {
        return years*12;
    }

    @Override
    public BigDecimal taxCalculation(BigDecimal sellingPrice) {
        return sellingPrice.multiply(BigDecimal.valueOf(0.25));
    }
}
