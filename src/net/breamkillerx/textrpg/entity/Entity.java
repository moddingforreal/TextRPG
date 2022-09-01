package net.breamkillerx.textrpg.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class Entity {
    @JsonProperty
    protected int health;

    public void setHealth(int healthIn) {
        this.health = Math.max(healthIn, 0);
    }

    public int getHealth() {
        return this.health;
    }

    public boolean isAlive() {
        return this.health > 0;
    }

    public int attackFor(int damage) {
        int prevHealth = this.health;
        this.health = Math.max(this.health - damage, 0);
        return prevHealth - health;
    }
}
