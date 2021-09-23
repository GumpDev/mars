package dev.gump.mars.items;

import dev.gump.mars.Utils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class ItemsUtil {
    public static Material GetMaterial(String material){
        return Material.matchMaterial(material);
    }

    public static ItemStack CreateItemStack(Material material){
        return CreateItemStack(material, 1, null, null);
    }
    public static ItemStack CreateItemStack(Material material, int quantity){
        return CreateItemStack(material, quantity, null, null);
    }
    public static ItemStack CreateItemStack(Material material, int quantity, String displayName){
        return CreateItemStack(material, quantity, displayName, null);

    }
    public static ItemStack CreateItemStack(Material material, int quantity, String displayName, List<String> lore){
        ItemStack item = new ItemStack(material, quantity);
        ItemMeta meta = item.getItemMeta();
        if(displayName != null && meta != null)
            meta.setDisplayName(Utils.colorize( displayName));
        if(lore != null && meta != null)
            meta.setLore(Utils.colorize(lore));
        item.setItemMeta(meta);
        return item;
    }
}
