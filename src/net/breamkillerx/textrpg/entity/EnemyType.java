package net.breamkillerx.textrpg.entity;

public enum EnemyType {
    OGRE("Ogre", "KillsOgre", 3, 4, 2, 1, 2),
    SLIME("Slime", "KillsSlime", 1, 3, 1, 0, 1),
    SKELETON("Skeleton", "KillsSkeleton", 2, 6, 2, 0, 1),
    ZOMBIE("Zombie", "KillsZombie", 2, 5, 1, 1, 1),
    LICH("Lich", "killsLich", 4, 5, 2, 7, 3),
    DRAGON("Dragon", "killsDragon", 1, 15, 1, 10, 6),
    UNDEAD_WARRIOR("Undead Warrior", "killsUndeadWarrior", 4, 3, 2, 5, 3),
    VIPER("Viper", "killsViper", 3, 7, 2, 3, 2),
    HYDRA("Hydra", "killsHydra", 3, 7, 3, 6, 4),
    GOLEM("Golem", "killsGolem", 6, 6, 3, 4, 4),
    UNDEFINED_ENEMY_TYPE;
    
    String name, killStatName;
    int healthMultiplier, healthConstant, attackMultiplier, attackConstant, lootMultiplier;

    EnemyType() {

    }

    EnemyType(String name, String killStatName, int healthMultiplier, int healthConstant, int attackMultiplier, int attackConstant, int lootMultiplier) {
        this.name = name;
        this.killStatName = killStatName;
        this.healthMultiplier = healthMultiplier;
        this.healthConstant = healthConstant;
        this.attackMultiplier = attackMultiplier;
        this.attackConstant = attackConstant;
        this.lootMultiplier = lootMultiplier;
    }
    
    public int getHealth(int level) {
        return level * healthMultiplier + healthConstant;
    }
    
    public int getAttack(int level) {
        return  level * attackMultiplier + attackConstant;
    }
    
    public int getLootMultiplier() {
        return lootMultiplier;
    }
}
