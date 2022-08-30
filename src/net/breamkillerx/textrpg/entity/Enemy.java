package net.breamkillerx.textrpg.entity;

import net.breamkillerx.textrpg.entity.control.PlayerEntity;
import net.breamkillerx.textrpg.exception.InvalidEntityIdException;

public class Enemy extends Entity {
    public int enemyId = 0;
    public int attack = 0;
    public int health = 0;
    public int lootMultiplier = 0;
    public String Enemy = "";
    public EnemyType type = EnemyType.UNDEFINED_ENEMY_TYPE;
    public Enemy(String Enemy,int attack,int health,int enemyId,int lootMultiplier){
        this.attack=attack;
        this.health=health;
        this.enemyId=enemyId;
        this.Enemy=Enemy;
        this.lootMultiplier=lootMultiplier;
    }
    public Enemy(String Enemy,int attack,int health,int enemyId,int lootMultiplier, EnemyType type){
        this.attack=attack;
        this.health=health;
        this.enemyId=enemyId;
        this.Enemy=Enemy;
        this.lootMultiplier=lootMultiplier;
        this.type = type;
    }
    public Enemy(String Enemy) {
        int level = PlayerEntity.playerAttributes.get("level");
        this.Enemy = Enemy;
        switch (Enemy) {
            default:
                throw new InvalidEntityIdException("Invalid enemy name passed to enemy constructor!");
            case "Ogre":
                enemyId = 4;
                health = level * 3 + 4;
                attack = level *2 +1;
                lootMultiplier = 2;
                type = EnemyType.OGRE;
                break;
            case "Slime":
                enemyId = 1;
                health = level +3;
                attack = level;
                lootMultiplier = 1;
                type = EnemyType.SLIME;
                break;
            case "Skeleton":
                enemyId = 3;
                health = level * 2 + 6;
                attack = level *2;
                lootMultiplier = 1;
                type = EnemyType.SKELETON;
                break;
            case "Zombie":
                enemyId = 2;
                health = level * 2 + 5;
                attack = level +1;
                lootMultiplier = 1;
                type = EnemyType.ZOMBIE;
                break;
            case "Litch":
                enemyId = 7;
                health = level * 4 + 5;
                attack = level *2 +7;
                lootMultiplier = 3;
                type = EnemyType.LITCH;
                break;
            case "Dragon":
                enemyId = 10;
                health = level *15;
                attack = level *10;
                lootMultiplier = 6;
                type = EnemyType.DRAGON;
                break;
            case "Undead Warrior":
                enemyId = 6;
                health = level *4 +3;
                attack = level *2 +5;
                lootMultiplier = 3;
                type = EnemyType.UNDEAD_WARRIOR;
                break;
            case "Viper":
                enemyId = 5;
                health = level *3 +7;
                attack = level *2 +3;
                lootMultiplier = 2;
                type = EnemyType.VIPER;
                break;
            case "Hydra":
                enemyId = 9;
                health = level *3 + 7;
                attack = level *3 +6;
                lootMultiplier = 4;
                type = EnemyType.HYDRA;
                break;
            case "Golem":
                enemyId = 8;
                health = level *6 + 6;
                attack = level *3 +4;
                lootMultiplier = 4;
                type = EnemyType.GOLEM;
                break;
        }
    }
    public Enemy() {}
}