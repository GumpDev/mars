package dev.gump.mars.interactions;

import dev.gump.mars.items.ItemsListener;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class InteractionsManager {
    InteractionsListener listener;
    public void init(Plugin plugin){
        listener = new InteractionsListener(plugin);
    }

    public void onMobInteract(Entity entity, MobInteract action){ InteractionsUtils.OnMobInteract(entity, action); }
    public void onItemInteract(ItemStack item, ItemInteract action){InteractionsUtils.OnItemInteract(item, action);}
    public void onBlockInteract(Material material, BlockInteract action){InteractionsUtils.OnBlockInteract(material, action);}
}
