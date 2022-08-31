package net.breamkillerx.textrpg.entity.control.inventory;

public class Stack {
    public final int maxAmountHeld;

    public Stack() {
        this(0);
    }

    public Stack(int maxAmountContained) {
        maxAmountHeld = maxAmountContained;
    }

    public Stack(int maxAmountContained, Item itemContained) {
        maxAmountHeld = maxAmountContained;
        item = itemContained;
    }

    public Stack(int maxAmountContained, Item itemContained, int itemAmountContained) {
        maxAmountHeld = maxAmountContained;
        item = itemContained;
        this.itemAmountHeld = itemAmountContained;
    }

    public Item item;
    public int itemAmountHeld;
}
