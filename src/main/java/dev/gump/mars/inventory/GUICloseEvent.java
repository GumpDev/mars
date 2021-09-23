package dev.gump.mars.inventory;

import org.bukkit.event.inventory.InventoryCloseEvent;

public interface GUICloseEvent {
    void onClose(InventoryCloseEvent event);
}
