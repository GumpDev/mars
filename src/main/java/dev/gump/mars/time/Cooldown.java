package dev.gump.mars.time;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

public class Cooldown {
    private HashMap<String, Integer> cooldowns = new HashMap<>();

    public Cooldown(Plugin plugin) {
        new BukkitRunnable(){
            @Override
            public void run() {
                for (String key : cooldowns.keySet()) {
                    if (cooldowns.get(key) > 0) {
                        cooldowns.put(key, cooldowns.get(key) - 1);
                    }else
                        cooldowns.remove(key);
                }
            }
        }.runTaskTimer(plugin, 0, 20);
    }

    public int isOnCooldown(Player player) {
        return cooldowns.getOrDefault(player.getUniqueId().toString(),-1);
    }

    public void addCooldown(Player player, int seconds) {
        cooldowns.put(player.getUniqueId().toString(), seconds);
    }
}
