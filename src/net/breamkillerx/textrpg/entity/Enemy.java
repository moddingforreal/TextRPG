package net.breamkillerx.textrpg.entity;

import net.breamkillerx.textrpg.exception.InvalidEntityIdException;

public class Enemy extends Entity {
    public int attack;
    private final int lootMultiplier;
    private final EnemyType type;

    public Enemy(EnemyType type, int level) {
        this.type = type;
        this.setHealth(type.getHealth(level));
        this.attack = type.getAttack(level);
        this.lootMultiplier = type.getLootMultiplier();
    }

    public int getLootMultiplier() {
        return this.lootMultiplier;
    }

    public String getKillStatName() {
        return this.type.killStatName;
    }

    public String getName() {
        return this.type.name;
    }

    public int getAttack() {
        return this.attack;
    }
}