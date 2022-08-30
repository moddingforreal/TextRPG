package net.breamkillerx.textrpg.entity.control;

import java.util.HashMap;
import java.util.Map;

public class PlayerEntity extends ControllableEntity {
    public static HashMap<String, Integer> playerAttributes;
    public static boolean isAlive = true;
    PlayerEntity() {}
    @SafeVarargs
    PlayerEntity(Map.Entry<String, Integer>... entries) {
        for (Map.Entry<String, Integer> entry:entries) {
            playerAttributes.put(entry.getKey(), entry.getValue());
        }
    }
    static {
        playerAttributes = new HashMap<>();
        playerAttributes.put("xp", 0);
        playerAttributes.put("level", 1);
        playerAttributes.put("baseHealth", 10);
        playerAttributes.put("baseAttack", 10);
        // playerAttributes.put("gold", 0);
        // playerAttributes.put("potions", 0);
        playerAttributes.put("currHealth", 10);
        playerAttributes.put("currAttack", 10);
    }
    public static void onRespawn() {
        isAlive = true;
        playerAttributes.put("xp", 0);
        playerAttributes.put("level", 1);
        playerAttributes.put("baseHealth", 10);
        playerAttributes.put("baseAttack", 10);
        // playerAttributes.put("gold", 0);
        // playerAttributes.put("potions", 0);
        playerAttributes.put("currHealth", 10);
        playerAttributes.put("currAttack", 10);
    }
}
