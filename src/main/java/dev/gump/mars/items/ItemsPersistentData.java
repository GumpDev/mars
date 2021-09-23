package dev.gump.mars.items;

import dev.gump.mars.Mars;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.Locale;

public class ItemsPersistentData {
    public static <T> ItemStack setPersistentData(ItemStack item, PersistentDataType type, String key, T value){
        NamespacedKey nKey = new NamespacedKey(Mars.getPlugin(), key.toLowerCase(Locale.ROOT));
        ItemMeta meta = item.getItemMeta();
        meta.getPersistentDataContainer().set(nKey, type, value);
        item.setItemMeta(meta);
        return item;
    }
    public static Boolean hasPersistentData(ItemStack item, PersistentDataType type, String key){
        NamespacedKey nKey = new NamespacedKey(Mars.getPlugin(), key.toLowerCase(Locale.ROOT));
        return item.getItemMeta().getPersistentDataContainer().has(nKey, type);
    }
    public static <T> T getPersistentData(ItemStack item, PersistentDataType type, String key){
        NamespacedKey nKey = new NamespacedKey(Mars.getPlugin(), key.toLowerCase(Locale.ROOT));
        return (T) item.getItemMeta().getPersistentDataContainer().get(nKey, type);
    }
    public static ItemStack removePersistentData(ItemStack item, String key){
        NamespacedKey nKey = new NamespacedKey(Mars.getPlugin(), key.toLowerCase(Locale.ROOT));
        item.getItemMeta().getPersistentDataContainer().remove(nKey);
        return item;
    }
}
