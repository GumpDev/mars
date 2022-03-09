package dev.gump.mars.entity;

import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class EntityLoot {
    private List<ItemStack> drops = new ArrayList<>();
    private boolean absoluteDrop = false;

    public EntityLoot(List<ItemStack> drops) {
        this.drops = drops;
    }
    public EntityLoot(List<ItemStack> drops, boolean absoluteDrop) {
        this.drops = drops;
        this.absoluteDrop = absoluteDrop;
    }

    public List<ItemStack> getDrops() {
        return drops;
    }
    public boolean isAbsoluteDrop() {
        return absoluteDrop;
    }

    public List<ItemStack> getDrop(){
        if (absoluteDrop)
            return drops;
        else {
            List<ItemStack> result = new ArrayList<>();
            for(ItemStack item : getDrops()){
                if(Math.random() > 0.5) {
                    ItemStack item2 = item;
                    item2.setAmount((int) Math.ceil(Math.random() * item.getAmount()));
                    if(item2.getAmount() > 0)
                        result.add(item2);
                }
            }
            return result;
        }
    }
}
