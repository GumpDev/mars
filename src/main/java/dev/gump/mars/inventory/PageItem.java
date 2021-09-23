package dev.gump.mars.inventory;

import org.bukkit.inventory.ItemStack;

public class PageItem {
    public String id;
    public ItemStack stack;
    public PageItem(String id, ItemStack stack){
        this.stack = stack;
        this.id = id;
    }
}
