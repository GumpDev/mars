package dev.gump.mars.inventory;

import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.List;

public interface GUIPaginationEvents {
    List<PageItem> onPageChange(int page);

    int getMaxItems();

    void onClick(InventoryClickEvent event, String uuid);

    void onBack(InventoryClickEvent event);
}
