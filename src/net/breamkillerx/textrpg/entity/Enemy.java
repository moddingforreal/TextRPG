package net.breamkillerx.textrpg.entity;

import net.breamkillerx.textrpg.exception.InvalidEntityIdException;

public class Enemy extends Entity {
    public int attack;
    public int health;
    private final int lootMultiplier;
    private final EnemyType type;

    public Enemy(EnemyType type, int level) {
        this.type = type;
        this.health = type.getHealth(level);
        this.attack = type.getAttack(level);
        this.lootMultiplier = type.getLootMultiplier();
    }

    public int getLootMultiplier() {
        return lootMultiplier;
    }

    public String getKillStatName() {
        return type.killStatName;
    }

    public String getName() {
        return type.name;
    }

    public boolean isAlive() {
        return health > 0;
    }
}