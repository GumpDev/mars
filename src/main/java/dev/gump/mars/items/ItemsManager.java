package dev.gump.mars.items;

import dev.gump.mars.Mars;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

import java.util.List;
import java.util.UUID;

public class ItemsManager {
    ItemsListener listener;
    public void init(Plugin plugin){
        Bukkit.resetRecipes();
        listener = new ItemsListener(plugin);
    }

    //Attribute
    public ItemStack addAttribute(ItemStack item, ItemsAttribute... attributes){ return ItemsAttribute.AddAttribute(item, attributes); }
    public ItemStack remAttribute(ItemStack item, org.bukkit.attribute.Attribute... atributes){ return ItemsAttribute.RemAttribute(item, atributes); }
    public ItemStack clearAttributes(ItemStack item){ return ItemsAttribute.ClearAttributes(item); }

    //Drop
    public org.bukkit.entity.Item spawn(ItemStack item, Location location){ return ItemsDrop.Spawn(item,location); }
    public org.bukkit.entity.Item spawn(ItemStack item, Location location, Boolean canPickup){ return ItemsDrop.Spawn(item,location, canPickup); }
    public org.bukkit.entity.Item spawnAndRemove(ItemStack item, Location location, int ticks){ return ItemsDrop.SpawnAndRemove(item, location, ticks); }

    //Enchantment
    public ItemStack enchant(ItemStack item, ItemsEnchantment... enchantments){ return ItemsEnchantment.Enchant(item, enchantments); }
    public ItemStack unenchant(ItemStack item, org.bukkit.enchantments.Enchantment... enchantments){ return ItemsEnchantment.Unenchant(item, enchantments); }
    public ItemStack clearEnchants(ItemStack item){ return ItemsEnchantment.ClearEnchants(item); }

    //Events
    public void onTryPickup(org.bukkit.entity.Item item, PickupEvent dropEvent){ ItemsEvent.OnTryPickup(item, dropEvent); }
    public ItemStack onItemInteract(String id, ItemStack item, InteractEvent interactEvent) { return ItemsEvent.OnItemInteract(id, item, interactEvent); }
    public ItemStack onDrop(String id, ItemStack item, DropEvent dropEvent) { return ItemsEvent.OnDrop(id, item, dropEvent); }
    public void removeOnTryPickup(Item item){
        ItemsEvent.RemoveOnTryPickup(item);
    }
    public ItemStack removeOnItemInteract(ItemStack item){
        return ItemsEvent.RemoveOnItemInteract(item);
    }
    public ItemStack removeOnDrop(ItemStack item){
        return ItemsEvent.RemoveOnDrop(item);
    }

    //Item
    public Material getMaterial(String material){ return ItemsUtil.GetMaterial(material); }
    public ItemStack createItemStack(Material material){ return ItemsUtil.CreateItemStack(material); }
    public ItemStack createItemStack(Material material, int quantity){ return ItemsUtil.CreateItemStack(material, quantity ); }
    public ItemStack createItemStack(Material material, int quantity, String displayName){ return ItemsUtil.CreateItemStack(material, quantity, displayName); }
    public ItemStack createItemStack(Material material, int quantity, String displayName, List<String> lore){ return ItemsUtil.CreateItemStack(material, quantity, displayName, lore); }

    //Head
    public ItemStack createSkullByBase64(String base64, String displayName) { return ItemsHead.CreateSkullByBase64(base64, displayName); }
    public ItemStack createSkullByURL(String url, String displayName) { return ItemsHead.CreateSkullByURL(url, displayName); }
    public ItemStack createSkullByUUID(UUID uuid, String displayName) { return ItemsHead.CreateSkullByUUID(uuid, displayName); }
    public ItemStack createSkullByName(String name, String displayName) { return ItemsHead.CreateSkullByName(name, displayName); }

    //PersistentData
    public <T> ItemStack setPersistentData(ItemStack item, PersistentDataType type, String key, T value){
        return ItemsPersistentData.setPersistentData(item,type,key,value);
    }
    public Boolean hasPersistentData(ItemStack item, PersistentDataType type, String key){
        return ItemsPersistentData.hasPersistentData(item,type,key);
    }
    public <T> T getPersistentData(ItemStack item, PersistentDataType type, String key){
        return ItemsPersistentData.getPersistentData(item,type,key);
    }
    public ItemStack removePersistentData(ItemStack item, String key){
        return ItemsPersistentData.removePersistentData(item, key);
    }
}
