package dev.gump.mars.armor_stand;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.plugin.Plugin;

public class ArmorStandManager {
    ArmorStandListener listener;
    public void init(Plugin plugin){
        listener = new ArmorStandListener(plugin);
    }

    public ArmorStand createBalloon(Location location, String text, int ticks) { return ArmorStandUtils.createBalloon(location, text, ticks); }
}
