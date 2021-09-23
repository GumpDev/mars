package dev.gump.mars.items;

import org.bukkit.inventory.ItemStack;

public class ItemsEnchantment {
    private org.bukkit.enchantments.Enchantment enchantment;
    private int level = 1;
    public ItemsEnchantment(org.bukkit.enchantments.Enchantment enchantment){
        this.enchantment = enchantment;
    }
    public ItemsEnchantment(org.bukkit.enchantments.Enchantment enchantment, int level){
        this.enchantment = enchantment;
        this.level = level;
    }

    public org.bukkit.enchantments.Enchantment getEnchantment() {
        return enchantment;
    }
    public int getLevel() {
        return level;
    }

    public static ItemStack Enchant(ItemStack item, ItemsEnchantment... enchantments){
        for(ItemsEnchantment enchantment : enchantments)
            item.addUnsafeEnchantment(enchantment.getEnchantment(),enchantment.getLevel());
        return item;
    }
    public static ItemStack Unenchant(ItemStack item, org.bukkit.enchantments.Enchantment... enchantments){
        for(org.bukkit.enchantments.Enchantment enchantment : enchantments)
            item.removeEnchantment(enchantment);
        return item;
    }
    public static ItemStack ClearEnchants(ItemStack item){
        for(org.bukkit.enchantments.Enchantment enchantment : item.getEnchantments().keySet())
            item.removeEnchantment(enchantment);
        return item;
    }
}
