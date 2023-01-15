package dev.gump.mars.time;

import java.util.HashMap;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class Cooldown {

  private final HashMap<String, Integer> cooldowns = new HashMap<>();

  public Cooldown(Plugin plugin) {
    init(plugin, 20);
  }

  public Cooldown(Plugin plugin, int period) {
    init(plugin, period);
  }

  private void init(Plugin plugin, int period) {
    new BukkitRunnable() {
      @Override
      public void run() {
        for (String key : cooldowns.keySet()) {
          if (cooldowns.get(key) > 0) {
            cooldowns.put(key, cooldowns.get(key) - 1);
          } else {
            cooldowns.remove(key);
          }
        }
      }
    }.runTaskTimer(plugin, 0, period);
  }

  public boolean is(String target) {
    return cooldowns.containsKey(target);
  }

  public int get(String target) {
    return cooldowns.getOrDefault(target, -1);
  }

  public void add(String target, int time) {
    cooldowns.put(target, time);
  }
}
