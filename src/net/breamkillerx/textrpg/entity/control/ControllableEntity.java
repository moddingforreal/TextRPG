package net.breamkillerx.textrpg.entity.control;

import com.fasterxml.jackson.annotation.JsonIgnore;
import net.breamkillerx.textrpg.entity.Entity;
import net.breamkillerx.textrpg.entity.IPlayable;
import net.breamkillerx.textrpg.entity.control.inventory.Inventory;

public class ControllableEntity extends Entity implements IPlayable {
    public Inventory inventory = new Inventory(16, 16);
    @Override
    public void die() {
        setHealth(0);
    }

    @Override
    public void respawn() {
        setHealth(10);
        inventory.clear();
    }
}
