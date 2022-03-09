package dev.gump.mars.entity;

import dev.gump.mars.Mars;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.EntityEquipment;

import java.util.ArrayList;
import java.util.List;

public class CustomEntity {
    private final String id;
    private final EntityType type;
    private String displayName = null;
    private float health = 0, armor = 0, movimentSpeed = 0, attackSpeed = 0, damage = 0;

    private List<EntityPotion> potions = new ArrayList<>();
    private List<EntityAttribute> attributes = new ArrayList<>();
    private EntityItems items;

    public String getId() {
        return id;
    }
    public EntityType getType() {
        return type;
    }
    public String getDisplayName() {
        return displayName;
    }
    public float getHealth() {
        return health;
    }
    public List<EntityPotion> getPotions() {
        return potions;
    }
    public List<EntityAttribute> getAttributes() {
        return attributes;
    }
    public EntityItems getItems() {
        return items;
    }
    public float getArmor() {
        return armor;
    }
    public float getMovimentSpeed() {
        return movimentSpeed;
    }
    public float getAttackSpeed() {
        return attackSpeed;
    }
    public float getDamage() {
        return damage;
    }

    public CustomEntity(String id, EntityType type) {
        this.id = id;
        this.type = type;
    }
    public CustomEntity(String id, EntityType type, String displayName) {
        this.id = id;
        this.type = type;
        this.displayName = displayName;
    }
    public CustomEntity(String id, EntityType type, String displayName, float health) {
        this.id = id;
        this.type = type;
        this.displayName = displayName;
        this.health = health;
    }
    public CustomEntity(String id, EntityType type, String displayName, float health, float armor) {
        this.id = id;
        this.type = type;
        this.displayName = displayName;
        this.health = health;
        this.armor = armor;
    }
    public CustomEntity(String id, EntityType type, String displayName, float health, float armor, float movimentSpeed) {
        this.id = id;
        this.type = type;
        this.displayName = displayName;
        this.health = health;
        this.armor = armor;
        this.movimentSpeed = movimentSpeed;
    }
    public CustomEntity(String id, EntityType type, String displayName, float health, float armor, float movimentSpeed, float attackSpeed) {
        this.id = id;
        this.type = type;
        this.displayName = displayName;
        this.health = health;
        this.armor = armor;
        this.movimentSpeed = movimentSpeed;
        this.attackSpeed = attackSpeed;
    }
    public CustomEntity(String id, EntityType type, String displayName, float health, float armor, float movimentSpeed, float attackSpeed, float damage) {
        this.id = id;
        this.type = type;
        this.displayName = displayName;
        this.health = health;
        this.armor = armor;
        this.movimentSpeed = movimentSpeed;
        this.attackSpeed = attackSpeed;
        this.damage = damage;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
    public void setHealth(float health) {
        this.health = health;
    }
    public void setArmor(float armor) {
        this.armor = armor;
    }
    public void setMovimentSpeed(float movimentSpeed) {
        this.movimentSpeed = movimentSpeed;
    }
    public void setAttackSpeed(float attackSpeed) {
        this.attackSpeed = attackSpeed;
    }
    public void setDamage(float damage) {
        this.damage = damage;
    }
    public void setPotions(List<EntityPotion> potions) {
        this.potions = potions;
    }
    public void setAttributes(List<EntityAttribute> attributes) {
        this.attributes = attributes;
    }
    public void setItems(EntityItems items) {
        this.items = items;
    }
    public void setDrops(EntityLoot entityLoot) {
        Mars.entity.setDrop(getId(), entityLoot);
    }

    public void onHit(EntityHitEvent event){
        Mars.entity.onHit(this.getId(), event);
    }
    public void onDeath(EntityDeathEvent event){
        Mars.entity.onDeath(this.getId(), event);
    }
    public void onInteract(EntityInteractionEvent event){
        Mars.entity.onInteract(this.getId(), event);
    }

    public void spawn(Location loc){
        Entity entity = loc.getWorld().spawnEntity(loc, getType());
        if(!(entity instanceof LivingEntity)) return;
        LivingEntity livingEntity = (LivingEntity) entity;
        if(displayName!=null) {
            livingEntity.setCustomNameVisible(true);
            livingEntity.setCustomName(displayName);
        }
        if(health!=0) {
            livingEntity.setMaxHealth(health);
            livingEntity.setHealth(health);
        }
        if(armor!=0)
            Mars.entity.setArmor(livingEntity, armor);
        if(movimentSpeed!=0)
            Mars.entity.setMovimentSpeed(livingEntity, movimentSpeed);
        if(attackSpeed!=0)
            Mars.entity.setAttackSpeed(livingEntity, attackSpeed);
        if(damage!=0)
            Mars.entity.setAttackDamage(livingEntity, damage);
        if(!potions.isEmpty())
            for(EntityPotion pot : potions)
                Mars.entity.addPotion(livingEntity, pot);
        if(!attributes.isEmpty())
            for(EntityAttribute attribute : attributes)
                Mars.entity.addAttribute(livingEntity, attribute);
        if(!(entity instanceof EntityEquipment)) return;
        if(items != null)
            Mars.entity.equipEntity((EntityEquipment) livingEntity,items);
    }
}
