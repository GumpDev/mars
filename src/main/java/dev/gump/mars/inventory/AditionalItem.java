package dev.gump.mars.inventory;


import org.bukkit.inventory.ItemStack;

public class AditionalItem{
    ItemStack stack;
    GUIClickEvent action;
    public AditionalItem(ItemStack stack, GUIClickEvent action){
        this.stack = stack;
        this.action = action;
    }

    public ItemStack getStack() {
        return stack;
    }

    public void setStack(ItemStack stack) {
        this.stack = stack;
    }

    public GUIClickEvent getAction() {
        return action;
    }

    public void setAction(GUIClickEvent action) {
        this.action = action;
    }
}