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
    public boolean addItem(ItemType type) {
        for (Stack content : contents) {
            if (content.item.type() == type) {
                if (content.itemAmountHeld + 1 <= content.maxAmountHeld) {
                    content.itemAmountHeld++;
                    update();
                    return true;
                }
            }
        }
        if (!(contents.size() == maxStacks)) {
            addStack(new Stack(maxStackSize, new Item(type), 1));
            update();
            return true;
        }
        update();
        return false;
    }
    // Negative values REMOVE that amount of items. Beware, buggy; Might behave unexpectedly
    public boolean addItem(ItemType type, int amount) {
        for (Stack content : contents) {
            if (content.item.type() == type) {
                if (content.itemAmountHeld + amount <= content.maxAmountHeld) {
                    content.itemAmountHeld += amount;
                    update();
                    return true;
                } else if (Math.ceil((double)amount/(double)maxStackSize) < maxStacks-contents.size()) {
                    int newstacks = (int)Math.ceil(((int)((double)amount/(double)maxStackSize)));
                    IntStream.rangeClosed(0, (newstacks+1)).forEach(x ->
                    {if (!(addStack(new Stack(maxStackSize, new Item(type), maxStackSize))))
                        throw new IllegalInventoryStateException("Stack overflow!");});

                }
            }
        }
        if (!(contents.size() == maxStacks)) {
            addStack(new Stack(maxStackSize, new Item(type), 1));
            update();
            return true;
        }
        update();
        return false;
    }
    // Adds and REMOVES items. This time safely but inefficiently.
    public boolean addItemsSafe(ItemType type, int amount) {
        boolean ret = true;
        for (int i = 0; i < amount; i++) {
            for (Stack content : contents) {
                if (content.item.type() == type) {
                    if (content.itemAmountHeld + 1 <= content.maxAmountHeld) {
                        content.itemAmountHeld++;
                    }
                }
            }
            if (!(contents.size() == maxStacks)) {
                addStack(new Stack(maxStackSize, new Item(type), 1));
                continue;
            }
            ret = false;
        }
        update();
        return ret;
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