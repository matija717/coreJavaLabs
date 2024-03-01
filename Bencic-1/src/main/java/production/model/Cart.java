package production.model;

import java.time.LocalDateTime;

public class Cart {
    private Item[]  boughtStuff;
    private LocalDateTime timeOfBuying;

    public Cart(Item[] boughtStuff, LocalDateTime timeOfBuying) {

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
}
