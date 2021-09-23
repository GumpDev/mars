package dev.gump.mars.interactions;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;

public class InteractionsUtils {
    public static void OnMobInteract(Entity entity, MobInteract action){
        InteractionsListener.mobInteract.put(entity, action);
    }
    public static void OnItemInteract(ItemStack item, ItemInteract action){
        InteractionsListener.itemInteract.put(item, action);
    }
    public static void OnBlockInteract(Material material, BlockInteract action){
        InteractionsListener.blockInteract.put(material, action);
    }
}
