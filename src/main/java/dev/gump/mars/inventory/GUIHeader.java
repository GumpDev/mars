package dev.gump.mars.inventory;

import dev.gump.mars.Mars;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

public class GUIHeader extends GUI{
    ItemStack title = null;
    GUIClickEvent clickAction = null;

    public GUIClickEvent getClickAction() {
        return clickAction;
    }
    public ItemStack getTitle() {
        return title;
    }

    final ItemStack back = GUIDefaultItems.getBack();
    final ItemStack none = GUIDefaultItems.getNone();

    public GUIHeader(Inventory inv, ItemStack title, GUIClickEvent clickAction) {
        super(inv);
        InventoryListener.itemsCanOut.add(inv);
        InventoryListener.slotActions.put(inv, new HashMap<>());
        InventoryListener.itemActions.put(inv, new HashMap<>());
        this.title = title;
        this.clickAction = clickAction;
        createInventory();
    }

    public void createInventory() {
        if (this.clickAction != null)
            this.setItem(0, back, event -> {
                GUIHeader guiHeader = this;
                new BukkitRunnable(){
                    @Override
                    public void run() {
                        guiHeader.getClickAction().onClick(event);
                    }
                }.runTaskLater(Mars.getPlugin(), 5);
            });

        if (this.title != null)
            getInventory().setItem(4, this.title);

        for (int i = 0; i < 9; i++)
            if (getInventory().getItem(i) == null)
                getInventory().setItem(i, none);
    }
}
