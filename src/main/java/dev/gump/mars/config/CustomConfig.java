package dev.gump.mars.config;

import dev.gump.mars.Mars;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class CustomConfig extends YamlConfiguration {
    private File customConfigFile;
    private FileConfiguration customConfig;

    private void CustomConfig(String name) {
        customConfigFile = new File(Mars.getPlugin().getDataFolder(), name+".yml");
        if (!customConfigFile.exists()) {
            customConfigFile.getParentFile().mkdirs();
            Mars.getPlugin().saveResource("custom.yml", false);
        }

        try {
            this.load(customConfigFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }
}
