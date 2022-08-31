package net.breamkillerx.textrpg.entity.control;

import net.breamkillerx.textrpg.entity.Entity;
import net.breamkillerx.textrpg.entity.IPlayable;
import net.breamkillerx.textrpg.entity.control.inventory.Inventory;

public class ControllableEntity extends Entity implements IPlayable {
    public boolean isAlive = true;
    public Inventory inventory = new Inventory(16, 16);
    @Override
    public void die() {
        isAlive = false;
    }

    @Override
    public void respawn() {
        isAlive = true;
    }
}
