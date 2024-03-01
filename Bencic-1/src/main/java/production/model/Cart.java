package production.model;

import java.time.LocalDateTime;

public class Cart {
    private String cartName;
    private Item[]  boughtStuff;
    private LocalDateTime timeOfBuying;

    public Cart(String cartName,Item[] boughtStuff, LocalDateTime timeOfBuying) {
        this.cartName=cartName;
        this.boughtStuff = boughtStuff;
        this.timeOfBuying = timeOfBuying;
    }

    public Item[] getBoughtStuff() {
        return boughtStuff;
    }

    public void setBoughtStuff(Item[] boughtStuff) {
        this.boughtStuff = boughtStuff;
    }

    public LocalDateTime getTimeOfBuying() {
        return timeOfBuying;
    }

    public void setTimeOfBuying(LocalDateTime timeOfBuying) {
        this.timeOfBuying = timeOfBuying;
    }

    public String getCartName() {
        return cartName;
    }

    public void setCartName(String cartName) {
        this.cartName = cartName;
    }
}
