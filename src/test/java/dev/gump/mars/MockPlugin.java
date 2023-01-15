package dev.gump.mars;

import java.io.File;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.JavaPluginLoader;

public class MockPlugin extends JavaPlugin {

  static Plugin plugin;

  public MockPlugin() {
    super();
  }

  protected MockPlugin(
      JavaPluginLoader loader, PluginDescriptionFile descriptionFile, File dataFolder, File file) {
    super(loader, descriptionFile, dataFolder, file);
  }

  @Override
  public void onEnable() {
    plugin = this;
    Mars.init(this);
  }

  @Override
  public void onDisable() {}

  public static Plugin getPlugin() {
    return plugin;
  }
}
