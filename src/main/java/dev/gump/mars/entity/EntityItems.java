package dev.gump.mars.entity;

import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;


public class EntityItems {
    private final ItemStack[] armor;
    private final ItemStack hand;
    private final ItemStack offHand;

    public EntityItems(ItemStack[] armor, ItemStack hand) {
        this.armor = armor;
        this.hand = hand;
        this.offHand = null;
    }
    public EntityItems(ItemStack[] armor, ItemStack hand, ItemStack offHand) {
        this.armor = armor;
        this.hand = hand;
        this.offHand = offHand;
    }
    public EntityItems(ItemStack[] armor) {
        this.armor = armor;
        this.hand = null;
        this.offHand = null;
    }
    public EntityItems(ItemStack hand, ItemStack offHand) {
        this.armor = null;
        this.hand = hand;
        this.offHand = offHand;
    }
    public EntityItems(ItemStack hand, ItemStack[] armor) {
        this.armor = armor;
        this.hand = hand;
        this.offHand = null;
    }
    public EntityItems(ItemStack hand, ItemStack offHand, ItemStack[] armor) {
        this.armor = armor;
        this.hand = hand;
        this.offHand = offHand;
    }

    public ItemStack[] getArmor() {
        return armor;
    }
    public ItemStack getHand() {
        return hand;
    }
    public ItemStack getOffhand() {
        return offHand;
    }

    public static EntityEquipment EquipEntity(EntityItems items, EntityEquipment entity){
        if(items.getArmor() != null)
            entity.setArmorContents(items.getArmor());
        if(items.getHand() != null)
            entity.setItem(EquipmentSlot.HAND, items.getHand());
        if(items.getOffhand() != null)
            entity.setItem(EquipmentSlot.OFF_HAND, items.getOffhand());
        return entity;
    }
}
