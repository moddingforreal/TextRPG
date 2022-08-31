package net.breamkillerx.textrpg.entity.control;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import net.breamkillerx.textrpg.entity.control.inventory.Inventory;

import java.util.HashMap;
import java.util.Map;

public class PlayerEntity extends ControllableEntity {
    public HashMap<String, Integer> playerAttributes;
    public boolean isAlive = true;
    public PlayerEntity() {
        initAttributes();
    }
    @SafeVarargs
    public PlayerEntity(Map.Entry<String, Integer>... entries) {
        this();
        for (Map.Entry<String, Integer> entry:entries) {
            playerAttributes.put(entry.getKey(), entry.getValue());
        }
    }
    private void initAttributes() {
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
    public void onRespawn() {
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
