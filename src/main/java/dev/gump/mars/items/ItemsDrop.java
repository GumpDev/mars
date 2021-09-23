package dev.gump.mars.items;

import dev.gump.mars.Mars;
import org.bukkit.Location;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class ItemsDrop {

    public static org.bukkit.entity.Item Spawn(ItemStack item, Location location){
        return Spawn(item,location,true);
    }
    public static org.bukkit.entity.Item Spawn(ItemStack item, Location location, Boolean canPickup){
        if(location.getWorld() == null) return null;
        org.bukkit.entity.Item itemEntity = location.getWorld().dropItemNaturally(location,item);
        if(!canPickup)
            ItemsListener.itemsCantPickup.add(itemEntity);
        return itemEntity;
    }
    public static org.bukkit.entity.Item SpawnAndRemove(ItemStack item, Location location, int ticks){
        if(location.getWorld() == null) return null;
        org.bukkit.entity.Item itemEntity = location.getWorld().dropItemNaturally(location,item);
        ItemsListener.itemsCantPickup.add(itemEntity);
        new BukkitRunnable(){
            @Override
            public void run() {
                ItemsListener.itemsCantPickup.remove(itemEntity);
                itemEntity.remove();
            }
        }.runTaskLater(Mars.getPlugin(),ticks);
        return itemEntity;
    }
}
