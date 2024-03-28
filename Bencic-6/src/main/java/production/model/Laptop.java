package production.model;

import java.math.BigDecimal;

public non-sealed class Laptop extends Item implements Technical {
    Integer years;



    public Laptop(String name,Long id, Category object, BigDecimal width, BigDecimal height, BigDecimal length,
                  BigDecimal productionCost, BigDecimal sellingPrice, Discount discountAmount, Integer years) {
        super(name,id, object, width, height, length, productionCost, sellingPrice, discountAmount);
        this.years = years;
    }

    public Integer getYears() {
        return years;
    }

    public void setYears(Integer years) {
        this.years = years;
    }

    public Integer durationOfGuarantee() {
        return years * 12;
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
