package net.breamkillerx.textrpg.world;

import java.util.HashMap;

public final class World {
    public static HashMap<String, Integer> playerKills;
    static{
        playerKills = new HashMap<String, Integer>();
        playerKills.put("killsSlime", 0);
        playerKills.put("killsZombie", 0);
        playerKills.put("killsSkeleton", 0);
        playerKills.put("killsOgre", 0);
        playerKills.put("killsViper", 0);
        playerKills.put("killsLitch", 0);
        playerKills.put("killsGolem", 0);
        playerKills.put("killsHydra", 0);
        playerKills.put("killsDragon", 0);
        playerKills.put("killsUndeadWarrior", 0);
    }
    public static boolean quit = false;
}
