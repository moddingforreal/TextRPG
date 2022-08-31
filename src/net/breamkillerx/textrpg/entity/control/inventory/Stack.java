package net.breamkillerx.textrpg.entity.control.inventory;

import com.fasterxml.jackson.annotation.JsonIgnore;
import net.breamkillerx.textrpg.util.Util;

public class Stack {
    public final int maxAmountHeld;
    public Item item;
    public int itemAmountHeld;

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

    public int add(int amount) {
        int amountToAdd = Util.clamp(-itemAmountHeld, amount, this.maxAmountHeld - this.itemAmountHeld);
        this.itemAmountHeld += amountToAdd;
        return amountToAdd;
    }

    @JsonIgnore
    public boolean isEmpty() {
        return itemAmountHeld == 0;
    }
}
