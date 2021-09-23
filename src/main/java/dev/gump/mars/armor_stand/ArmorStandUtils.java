package dev.gump.mars.armor_stand;

import dev.gump.mars.Mars;
import dev.gump.mars.Utils;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;

public class ArmorStandUtils {
    public static ArmorStand createBalloon(Location location, String text, int ticks){
        location.setY(location.getY() - 1.5f);
        if(location.getWorld() == null) return null;
        ArmorStand armorStand = (ArmorStand) location.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
        armorStand.setVisible(false);
        armorStand.setCustomNameVisible(true);
        armorStand.setGravity(false);
        armorStand.setCustomName(Utils.colorize(text));
        armorStand.getPersistentDataContainer().set(ArmorStandConsts.armorStandKey, PersistentDataType.INTEGER, 1);
        if(ticks > 0)
            new BukkitRunnable(){
                @Override
                public void run() {
                    armorStand.remove();
                }
            }.runTaskLater(Mars.getPlugin(), ticks);
        return armorStand;
    }
}
