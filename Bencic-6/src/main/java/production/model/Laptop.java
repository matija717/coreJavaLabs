package production.model;

import java.math.BigDecimal;

/**
 * The Laptop class represents a type of non-sealed item that is a technical device, such as a laptop,
 * and extends the Item class. It includes specific properties related to laptops, such as the number of years of use.
 */
public non-sealed class Laptop extends Item implements Technical {
    Integer years;

    /**
     * Creates a new Laptop object with the provided details.
     *
     * @param name           The name of the laptop.
     * @param object         The category of the laptop.
     * @param width          The width of the laptop.
     * @param height         The height of the laptop.
     * @param length         The length of the laptop.
     * @param productionCost The cost of producing the laptop.
     * @param sellingPrice   The price at which the laptop is sold.
     * @param discountAmount The discount applied to the laptop's selling price.
     * @param years          The number of years the laptop has been in use.
     */
    public Laptop(String name,Long id, Category object, BigDecimal width, BigDecimal height, BigDecimal length,
                  BigDecimal productionCost, BigDecimal sellingPrice,Discount discountAmount,Integer years,Grade gradeValue) {
        super(name,id, object, width, height, length, productionCost, sellingPrice, discountAmount, gradeValue);
        this.years = years;
    }

    public Integer getYears() {
        return years;
    }

    public void setYears(Integer years) {
        this.years = years;
    }

    /**
     * Calculates the duration of the warranty or guarantee for the laptop in months based on the number of years.
     *
     * @param years The number of years for which the laptop is covered by the warranty or guarantee.
     * @return The duration of the warranty or guarantee in months.
     */
    public Integer durationOfGarante(Integer years) {
        return years * 12;
    }
}
