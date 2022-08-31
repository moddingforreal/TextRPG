package net.breamkillerx.textrpg.entity.control.inventory;

public record Item(ItemType type) {

    public static Item of(ItemType type) {
        return new Item(type);
    }

    public boolean isOf(ItemType type) {
        return this.type == type;
    }

}
