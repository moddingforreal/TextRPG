package net.breamkillerx.textrpg.entity.control.inventory;

import net.breamkillerx.textrpg.exception.IllegalInventoryStateException;

import java.util.ArrayList;
import java.util.stream.IntStream;

public class Inventory {
    private final ArrayList<Stack> contents;
    public int maxStackSize;
    public int maxStacks;
    public Inventory(int maxStackSize, int maxStacks) {
        contents = new ArrayList<>(maxStacks);
        this.maxStacks = maxStacks;
        this.maxStackSize = maxStackSize;
    }

    public Inventory() {
        this(0, 0);
    }

    // Adds and removes items
    public int addItems(ItemType type, int amount) {
        int amountAdded = 0;
        // insert items into existing stacks or remove from existing stacks
        for (Stack stack : this.contents) {
            if (stack.item.isOf(type)) {
                amountAdded += stack.add(amount - amountAdded);
                if (amount - amountAdded == 0)
                   break;
            }
        }
        // create new stacks if filling existing stacks didn't satisfy the amount
        while (amount - amountAdded > 0 && this.contents.size() < this.maxStacks) {
            int newStackAmount = Math.min(amount - amountAdded, this.maxStackSize);
            this.contents.add(new Stack(this.maxStackSize, Item.of(type), newStackAmount));
            amountAdded += newStackAmount;
        }
        this.update();
        return amountAdded;
    }
    public boolean addStack(Stack stack) {
        if (!(contents.size() == maxStacks)) {
            contents.add(stack);
            return true;
        }
        return false;
    }
    public ArrayList<Stack> getContents() {
        return contents;
    }
    public Stack get(int index) {
        return contents.get(index);
    }

    public void clear() {
        contents.clear();
    }

    public int getAmount(ItemType type) {
        int count = 0;
        for (Stack content : contents) {
            if (content.item.type() == type) count += content.itemAmountHeld;
        }
        return count;
    }
    public int update() {
        int removedCount = 0;
        for (int i = 0; i < contents.size(); i++) {
            if (contents.get(i).itemAmountHeld == 0) {
                contents.remove(i);
                i--;
                removedCount++;
            }
        }
        return removedCount;
    }
}