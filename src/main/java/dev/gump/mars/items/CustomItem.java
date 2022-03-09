package dev.gump.mars.items;

import com.sun.org.apache.bcel.internal.Const;
import dev.gump.mars.Mars;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class CustomItem {
    private final String uuid;
    private ItemStack itemStack;

    public CustomItem(String uuid, Material material){
        this.uuid = uuid;
        this.itemStack = Mars.items.setPersistentData(Mars.items.createItemStack(material), PersistentDataType.STRING, ConstItem.marsId , uuid);
    }
    public CustomItem(String uuid, Material material, String displayName){
        this.uuid = uuid;
        this.itemStack = Mars.items.setPersistentData(Mars.items.createItemStack(material, 1, displayName), PersistentDataType.STRING, ConstItem.marsId , uuid);
    }
    public CustomItem(String uuid, Material material, String displayName, List<String> lore){
        this.uuid = uuid;
        this.itemStack = Mars.items.setPersistentData(Mars.items.createItemStack(material, 1, displayName, lore), PersistentDataType.STRING, ConstItem.marsId , uuid);
    }

    public String getDisplayName() {
        return this.itemStack.getItemMeta().getDisplayName();
    }
    public List<String> getLore() {
        return this.itemStack.getItemMeta().getLore();
    }
    public Material getMaterial() {
        return this.itemStack.getType();
    }
    public String getUUID() {
        return uuid;
    }
    public Boolean is(ItemStack item){
        if(!Mars.items.hasPersistentData(item, PersistentDataType.STRING, ConstItem.marsId)) return false;
        return Mars.items.getPersistentData(item, PersistentDataType.STRING, ConstItem.marsId).equals(uuid);
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public void setDisplayName(String displayName) {
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(displayName);
        itemStack.setItemMeta(meta);
    }
    public void setLore(List<String> lore) {
        ItemMeta meta = itemStack.getItemMeta();
        meta.setLore(lore);
        itemStack.setItemMeta(meta);
    }

    public void enchant(ItemsEnchantment ...enchantment){
        this.itemStack = Mars.items.enchant(this.itemStack, enchantment);
    }
    public void unnchants(org.bukkit.enchantments.Enchantment ...enchantment){
        this.itemStack = Mars.items.unenchant(this.itemStack, enchantment);
    }
    public void clearEnchants(){
        this.itemStack = Mars.items.clearEnchants(this.itemStack);
    }

    public void addAttributes(ItemsAttribute ...attributes){
        this.itemStack = Mars.items.addAttribute(this.itemStack,attributes);
    }
    public void removeAttributes(Attribute ...attributes){
        this.itemStack = Mars.items.remAttribute(this.itemStack,attributes);
    }
    public void clearAttributes(){
        this.itemStack = Mars.items.clearAttributes(this.itemStack);
    }

    public void onInteractEvent(InteractEvent interactEvent){
        this.itemStack = Mars.items.onItemInteract(uuid, this.itemStack, interactEvent);
    }
    public void removeOnInteractEvent(){
        this.itemStack = Mars.items.removeOnItemInteract(this.itemStack);
    }

    public void spawn(Location location){
        Mars.items.spawn(this.itemStack, location);
    }
    public void spawn(Location location, boolean canPickup){
        Mars.items.spawn(this.itemStack, location, canPickup);
    }
    public void spawnAndRemove(Location location, int ticks){
        Mars.items.spawnAndRemove(this.itemStack, location, ticks);
    }

    public void onDrop(DropEvent dropEvent){
        this.itemStack = Mars.items.onDrop(uuid, this.itemStack, dropEvent);
    }
    public void removeOnDrop(){
        this.itemStack = Mars.items.removeOnDrop(this.itemStack);
    }

    public void canDrop(boolean canDrop){
        if(!canDrop)
            this.itemStack = Mars.items.onDrop(uuid, this.itemStack, event -> event.setCancelled(true));
        else
            this.itemStack = Mars.items.removeOnDrop(this.itemStack);
    }

    static final List<Character> symbols = Arrays.asList('A','B','C','D','E','F','G','H','I');
    public void setRecipe(ItemStack[] recipe){
        if(recipe.length > 9) return;
        NamespacedKey key = new NamespacedKey(Mars.getPlugin(),this.getUUID());
        if(Bukkit.getRecipe(key) != null) Bukkit.removeRecipe(key);
        ShapedRecipe shapedRecipe = new ShapedRecipe(key,this.itemStack);
        shapedRecipe.shape("ABC","DEF","GHI");
        for(int i = 0; i < recipe.length; i++)
            shapedRecipe.setIngredient(symbols.get(i),recipe[i] != null ? recipe[i] : new ItemStack(Material.AIR));
        Bukkit.getServer().addRecipe(shapedRecipe);
    }
}
