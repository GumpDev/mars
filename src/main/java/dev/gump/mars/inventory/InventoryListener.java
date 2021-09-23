package dev.gump.mars.inventory;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InventoryListener implements Listener {
    public static List<Inventory> itemsCanOut = new ArrayList<>();

    public static HashMap<Inventory, HashMap<ItemStack, GUIClickEvent>> itemActions = new HashMap<>();
    public static HashMap<Inventory,HashMap<Integer, GUIClickEvent>> slotActions = new HashMap<>();
    public static HashMap<Inventory,GUICloseEvent> closedActions = new HashMap<>();

    public InventoryListener(Plugin plugin){
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void OnClickItemInventory(InventoryClickEvent event){
        if(itemActions.containsKey(event.getClickedInventory()) && itemActions.get(event.getClickedInventory()).containsKey(event.getCurrentItem()))
            itemActions.get(event.getClickedInventory()).get(event.getCurrentItem()).onClick(event);

        if(slotActions.containsKey(event.getClickedInventory()) && slotActions.get(event.getClickedInventory()).containsKey(event.getSlot()))
            slotActions.get(event.getClickedInventory()).get(event.getSlot()).onClick(event);

        if(itemsCanOut.contains(event.getClickedInventory()))
            event.setCancelled(true);
    }

    @EventHandler
    public void OnCloseInventory(InventoryCloseEvent event){
        itemsCanOut.remove(event.getInventory());

        if(itemActions.containsKey(event.getInventory()))
            itemActions.remove(event.getInventory(), itemActions.get(event.getInventory()));

        if(slotActions.containsKey(event.getInventory()))
            slotActions.remove(event.getInventory(), slotActions.get(event.getInventory()));

        if(closedActions.containsKey(event.getInventory())) {
            closedActions.get(event.getInventory()).onClose(event);
            closedActions.remove(event.getInventory(), closedActions.get(event.getInventory()));
        }
    }
}
