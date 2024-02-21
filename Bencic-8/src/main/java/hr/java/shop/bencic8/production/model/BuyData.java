package hr.java.shop.bencic8.production.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class BuyData extends NamedEntity{
    private List<Item> items=new ArrayList<>();
    private BigDecimal cost;

    public BuyData(String name, Long id, List<Item> items, BigDecimal cost) {
        super(name, id);
        this.items = items;
        this.cost=cost;

    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
