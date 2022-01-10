package dev.gump.mars.inventory;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.*;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.*;

public class InventoryListener implements Listener {
    public static List<Inventory> itemsCanOut = new ArrayList<>();
    public static HashMap<Inventory, HashMap<ItemStack, GUIClickEvent>> itemActions = new HashMap<>();
    public static HashMap<Inventory,HashMap<Integer, GUIClickEvent>> slotActions = new HashMap<>();
    public static HashMap<Inventory,GUICloseEvent> closedActions = new HashMap<>();

    public InventoryListener(Plugin plugin){
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void OnInventoryClick(InventoryClickEvent event){
        Inventory inv = event.getClickedInventory();
        Inventory targetInv = event.getInventory();
        InventoryAction action = event.getAction();

        // Item actions
        if(itemActions.containsKey(inv) && itemActions.get(inv).containsKey(event.getCurrentItem()))
            itemActions.get(inv).get(event.getCurrentItem()).onClick(event);

        // Slot actions
        if(slotActions.containsKey(inv) && slotActions.get(inv).containsKey(event.getSlot()))
            slotActions.get(inv).get(event.getSlot()).onClick(event);

        // If the clicked inventory is a gui
        if(itemsCanOut.contains(inv))
            event.setCancelled(true);

        // If the recipient inventory is a gui
        if(itemsCanOut.contains(targetInv)){
            if (action == InventoryAction.MOVE_TO_OTHER_INVENTORY)
                event.setCancelled(true);

            if (action == InventoryAction.COLLECT_TO_CURSOR)
                event.setCancelled(true);
        }
    }

    @EventHandler
    public void OnInventoryDrag(InventoryDragEvent event){
        InventoryView view = event.getView();
        Iterator<Integer> it = event.getRawSlots().iterator();

        // If one of the final slots is a gui
        it.forEachRemaining(i -> {
            if (itemsCanOut.contains(view.getInventory(i)))
                event.setCancelled(true);
        });
    }

    @EventHandler
    public void OnInventoryClose(InventoryCloseEvent event){
        Inventory inv = event.getInventory();
        itemsCanOut.remove(inv);

        // Item actions
        if(itemActions.containsKey(inv))
            itemActions.remove(inv, itemActions.get(inv));

        // Slot actions
        if(slotActions.containsKey(inv))
            slotActions.remove(inv, slotActions.get(inv));

        // Close actions
        if(closedActions.containsKey(inv)) {
            closedActions.get(inv).onClose(event);
            closedActions.remove(inv, closedActions.get(inv));
        }
    }
}
