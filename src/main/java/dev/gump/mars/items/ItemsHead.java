package dev.gump.mars.items;

import dev.gump.mars.Utils;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.UUID;

public class ItemsHead {
    static ItemStack setDisplayName(ItemStack item, String displayName){
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(Utils.colorize( displayName));
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack CreateSkullByBase64(String base64, String displayName) {
        return setDisplayName(SkullCreator.itemFromBase64(base64), displayName);
    }
    public static ItemStack CreateSkullByURL(String url, String displayName) {
        return setDisplayName(SkullCreator.itemFromUrl(url), displayName);
    }
    public static ItemStack CreateSkullByUUID(UUID uuid, String displayName) {
        return setDisplayName(SkullCreator.itemFromUuid(uuid), displayName);
    }
    public static ItemStack CreateSkullByName(String name, String displayName) {
        return setDisplayName(SkullCreator.itemFromName(name), displayName);
    }
}
