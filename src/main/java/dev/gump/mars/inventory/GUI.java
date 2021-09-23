package dev.gump.mars.inventory;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class GUI {
    private Inventory inv;

    public GUI(Inventory inv) {
        InventoryListener.itemsCanOut.add(inv);
        InventoryListener.slotActions.put(inv, new HashMap<>());
        InventoryListener.itemActions.put(inv, new HashMap<>());
        this.inv = inv;
    }

    public void setItem(int slot, ItemStack stack, GUIClickEvent action) {
        inv.setItem(slot, stack);
        OnClick(slot, action);
    }

    public void OnClick(int slot, GUIClickEvent action) {
        InventoryListener.slotActions.get(this.inv).put(slot, action);
    }
    public void OnClick(ItemStack item, GUIClickEvent action) {
        InventoryListener.itemActions.get(this.inv).put(item, action);
    }
    public void OnClose(GUICloseEvent action) {
        InventoryListener.closedActions.put(this.inv, action);
    }

    public Inventory getInventory() {
        return inv;
    }

    public void openInventory(Player player){
        player.openInventory(this.getInventory());
    }
}
