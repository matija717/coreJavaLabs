package hr.java.shop.bencic7.production.model;

import java.math.BigDecimal;

/**
 * The Item class represents an object with a name, category, dimensions, production cost, selling price,
 * and an optional discount amount.
 */
public class Item extends NamedEntity {

    private Category object;
    private BigDecimal width;
    private BigDecimal height;
    private BigDecimal length;
    private BigDecimal productionCost;
    private BigDecimal sellingPrice;
    private Discount discountAmount;

    /**
     * Creates a new Item object with the provided details.
     *
     * @param name           The name of the item.
     * @param object         The category of the item.
     * @param width          The width of the item.
     * @param height         The height of the item.
     * @param length         The length of the item.
     * @param productionCost The cost of producing the item.
     * @param sellingPrice   The price at which the item is sold.
     * @param discountAmount The discount applied to the item's selling price.
     */
    public Item(String name, Long id, Category object, BigDecimal width, BigDecimal height,
                BigDecimal length, BigDecimal productionCost, BigDecimal sellingPrice,
                Discount discountAmount) {
        super(name, id);
        this.object = object;
        this.width = width;
        this.height = height;
        this.length = length;
        this.productionCost = productionCost;
        this.sellingPrice = sellingPrice;
        this.discountAmount = discountAmount;
    }

    public Discount getDiscountAmount() {
        return discountAmount;
    }

    @Override
    public String toString() {
        return "Item:".concat(getName());
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

    public static BigDecimal volumeOfItemCalculation(Item item) {
        return item.getHeight().multiply(item.getLength().multiply(item.getWidth()));
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
