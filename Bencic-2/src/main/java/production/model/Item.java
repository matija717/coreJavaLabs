package production.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Item extends NamedEntity {

    private Category object;
    private BigDecimal width;
    private BigDecimal height;
    private BigDecimal length;
    private BigDecimal productionCost;
    private BigDecimal sellingPrice;
    private Discount discountAmount;
    private Ingredient ingredients;

    public Item(String name, Category object, BigDecimal width, BigDecimal height,
                BigDecimal length, BigDecimal productionCost, BigDecimal sellingPrice,
                Discount discountAmount, int identifier, LocalDateTime createdLocalDateTime, Ingredient ingredients) {
        super(name, identifier, createdLocalDateTime);
        this.object = object;
        this.width = width;
        this.height = height;
        this.length = length;
        this.productionCost = productionCost;
        this.sellingPrice = sellingPrice;
        this.discountAmount = discountAmount;
        this.ingredients = ingredients;
    }

    public BigDecimal volumeOfItemCalculation() {
        return height.multiply(height).multiply(width);
    }

    public Ingredient getIngredients() {
        return ingredients;
    }

    public Discount getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(Discount discountAmount) {
        this.discountAmount = discountAmount;
    }

    public Category getObject() {
        return object;
    }

    public void setObject(Category object) {
        this.object = object;
    }

    public BigDecimal getWidth() {
        return width;
    }

    public void setWidth(BigDecimal width) {
        this.width = width;
    }

    public BigDecimal getHeight() {
        return height;
    }

    public void setHeight(BigDecimal height) {
        this.height = height;
    }

    public BigDecimal getLength() {
        return length;
    }

    public void setLength(BigDecimal length) {
        this.length = length;
    }

    public BigDecimal getProductionCost() {
        return productionCost;
    }

    public void setProductionCost(BigDecimal productionCost) {
        this.productionCost = productionCost;
    }

    public BigDecimal getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(BigDecimal sellingPrice) {
        this.sellingPrice = sellingPrice;
    }
}
