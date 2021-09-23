package dev.gump.mars.items;

import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemsAttribute {
    private org.bukkit.attribute.Attribute attribute;
    private AttributeModifier modifier;
    public ItemsAttribute(org.bukkit.attribute.Attribute attribute, AttributeModifier modifier) {
        this.attribute = attribute;
        this.modifier = modifier;
    }

    public org.bukkit.attribute.Attribute getAttribute(){
        return attribute;
    }
    public AttributeModifier getModifier(){
        return modifier;
    }

    protected static ItemStack AddAttribute(ItemStack item, ItemsAttribute... attributes){
        ItemMeta meta = item.getItemMeta();
        if(meta == null) return item;
        for(ItemsAttribute attribute : attributes)
            meta.addAttributeModifier(attribute.getAttribute(),attribute.getModifier());
        return item;
    }

    protected static ItemStack RemAttribute(ItemStack item, org.bukkit.attribute.Attribute... atributes){
        ItemMeta meta = item.getItemMeta();
        if(meta == null) return item;
        for(org.bukkit.attribute.Attribute atribute : atributes)
            meta.removeAttributeModifier(atribute);
        return item;
    }

    protected static ItemStack ClearAttributes(ItemStack item){
        ItemMeta meta = item.getItemMeta();
        if(meta == null) return item;
        if(meta.getAttributeModifiers() == null) return item;
        for(org.bukkit.attribute.Attribute atribute : meta.getAttributeModifiers().keySet())
            meta.removeAttributeModifier(atribute);
        return item;
    }
}
