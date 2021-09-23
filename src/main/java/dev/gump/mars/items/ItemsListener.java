package dev.gump.mars.items;

import dev.gump.mars.Mars;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.inventory.InventoryPickupItemEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ItemsListener implements org.bukkit.event.Listener {
    public static List<Item> itemsCantPickup = new ArrayList<>();
    public static HashMap<Item, PickupEvent> dropActions = new HashMap<>();
    public static HashMap<String, DropEvent> dropItemActions = new HashMap<>();
    public static HashMap<String, InteractEvent> interactActions = new HashMap<>();
    public static List<Player> antiDoubleClick = new ArrayList<>();

    public ItemsListener(Plugin plugin){
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void OnItemDeath(EntityDeathEvent event){
        if(!(event.getEntity() instanceof Item)) return;
        Item item = (Item) event.getEntity();
        itemsCantPickup.remove(item);
        if(dropActions.containsKey(item))
            dropActions.remove(item,dropActions.get(item));
    }

    @EventHandler
    public void OnTryPickup(EntityPickupItemEvent event){
        if(dropActions.containsKey(event.getItem()))
            dropActions.get(event.getItem()).onTryPickup(event);
        if(itemsCantPickup.contains(event.getItem()))
            event.setCancelled(true);
    }

    @EventHandler
    public void OnTryPickupWithHopper(InventoryPickupItemEvent event){
        if(itemsCantPickup.contains(event.getItem()))
            event.setCancelled(true);
    }

    @EventHandler
    public void onTryDrop(PlayerDropItemEvent event){
        if(!Mars.items.hasPersistentData(event.getItemDrop().getItemStack(), PersistentDataType.STRING, ConstItem.marsDropEvent)) return;
        String value = Mars.items.getPersistentData(event.getItemDrop().getItemStack(), PersistentDataType.STRING, ConstItem.marsDropEvent);
        if(!dropItemActions.containsKey(value)) return;
        dropItemActions.get(value).onDrop(event);
    }

    @EventHandler
    public void OnPlayerInteract(PlayerInteractEvent event){
        if(event.getItem() == null) return;
        if(!Mars.items.hasPersistentData(event.getItem(), PersistentDataType.STRING, ConstItem.marsInteractEvent)) return;
        String value = Mars.items.getPersistentData(event.getItem(), PersistentDataType.STRING, ConstItem.marsInteractEvent);
        if(!interactActions.containsKey(value)) return;
        if(antiDoubleClick.contains(event.getPlayer())) return;
        interactActions.get(value).onInteractItem(event);
        antiDoubleClick.add(event.getPlayer());
        new BukkitRunnable(){
            @Override
            public void run() {
                antiDoubleClick.remove(event.getPlayer());
            }
        }.runTaskLater(Mars.getPlugin(), 10);
    }
}
