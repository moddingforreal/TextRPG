package net.breamkillerx.textrpg.entity.control;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class PlayerEntity extends ControllableEntity {
    public HashMap<String, Integer> playerAttributes;
    private final Map<String, Integer> statistics = new HashMap<>();
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

    public int getStat(String id) {
        return statistics.getOrDefault(id, 0);
    }

    public void putStat(String id, int value) {
        statistics.put(id, value);
    }

    public void updateStat(String id, Function<Integer, Integer> updater) {
        statistics.put(id, updater.apply(getStat(id)));
    }

    public Map<String, Integer> getStatistics() {
        return statistics;
    }

    public int getAttribute(String name) {
        return playerAttributes.getOrDefault(name, 0);
    }

    public void putAttribute(String name, int value) {
        playerAttributes.put(name, value);
    }

    public int updateAttribute(String name, Function<Integer, Integer> updater) {
        return playerAttributes.put(name, updater.apply(getAttribute(name)));
    }

    public Map<String, Integer> getAttributes() {
        return playerAttributes;
    }

    @Override
    public void respawn() {
        super.respawn();
        initAttributes();
        inventory.clear();
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
