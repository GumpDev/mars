package dev.gump.mars;

import dev.gump.mars.config.Intent;
import java.util.Arrays;
import java.util.List;
import org.bukkit.plugin.Plugin;

public final class Mars {
  static Plugin plugin;

  public static void setPlugin(Plugin plugin) {
    Mars.plugin = plugin;
  }

  public static Plugin getPlugin() {
    return plugin;
  }

  public static void init(Plugin plugin) {
    setPlugin(plugin);
  }

  public static void init(Plugin plugin, Intent... intents) {
    List<Intent> intentList = Arrays.asList(intents);
    setPlugin(plugin);
  }

  static boolean containIntent(Intent intent, List<Intent> intents) {
    return intents.contains(Intent.ALL) || intents.contains(intent);
  }
}
