package dev.gump.mars.entity;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffectType;

public class EntityManager {
    EntityListener listener;
    public void init(Plugin plugin){
        listener = new EntityListener(plugin);
    }

    //Attribute
    public LivingEntity addAttribute(LivingEntity entity, EntityAttribute... attributes){
        return EntityAttribute.AddAttribute(entity, attributes);
    }
    public LivingEntity remAttribute(LivingEntity entity, EntityAttribute... attributes){
        return EntityAttribute.RemAttribute(entity, attributes);
    }

    //DataPersistent
    public <T> Entity setPersistentData(Entity entity, PersistentDataType type, String key, T value){
        return EntityDataPersistent.SetPersistentData(entity, type, key, value);
    }
    public Boolean hasPersistentData(Entity entity, PersistentDataType type, String key){
        return EntityDataPersistent.HasPersistentData(entity, type, key);
    }
    public <T> T getPersistentData(Entity entity, PersistentDataType type, String key){
        return EntityDataPersistent.GetPersistentData(entity, type, key);
    }
    public Entity removePersistentData(Entity entity, String key){
        return EntityDataPersistent.RemovePersistentData(entity, key);
    }

    //Items
    public EntityEquipment equipEntity(EntityEquipment entity, EntityItems items){
        return EntityItems.EquipEntity(items, entity);
    }

    //Potion
    public LivingEntity addPotion(LivingEntity entity, EntityPotion ...potions){
        return EntityPotion.AddPotion(entity, potions);
    }
    public LivingEntity remPotion(LivingEntity entity, PotionEffectType...potions){
        return EntityPotion.RemPotion(entity, potions);
    }
    public LivingEntity clearPotions(LivingEntity entity){
        return EntityPotion.ClearPotions(entity);
    }

    //Entity
    public LivingEntity createEntity(Location location, EntityType entityType, String displayName, double maxHeath){
        return EntityUtils.CreateEntity(location, entityType, displayName, maxHeath);
    }
    public LivingEntity createEntity(Location location, EntityType entityType, String displayName, boolean invunerable){
        return EntityUtils.CreateEntity(location, entityType, displayName, invunerable);
    }
    public LivingEntity createEntity(Location location, EntityType entityType, String displayName){
        return EntityUtils.CreateEntity(location,entityType, displayName);
    }
    public LivingEntity createEntity(Location location, EntityType entityType){
        return EntityUtils.CreateEntity(location, entityType);
    }
    public LivingEntity setArmor(LivingEntity entity, double armor){
        return EntityUtils.SetArmor(entity, armor);
    }
    public LivingEntity setMovimentSpeed(LivingEntity entity, double speed){
        return EntityUtils.SetMovimentSpeed(entity, speed);
    }
    public LivingEntity setAttackSpeed(LivingEntity entity, double speed){
        return EntityUtils.SetAttackSpeed(entity, speed);
    }
    public LivingEntity setAttackDamage(LivingEntity entity, double damage){
        return EntityUtils.SetAttackDamage(entity, damage);
    }

    //Events
    public void onHit(Entity entity, EntityHitEvent event){
        EntityEvent.OnHit(entity, event);
    }
    public void onInteract(Entity entity, EntityInteractionEvent event){
        EntityEvent.OnInteract(entity, event);
    }
    public void onDeath(Entity entity, EntityDeathEvent event){
        EntityEvent.OnDeath(entity, event);
    }
    public void onHit(String id, EntityHitEvent event){
        EntityEvent.OnHit(id, event);
    }
    public void onInteract(String id, EntityInteractionEvent event){
        EntityEvent.OnInteract(id, event);
    }
    public void onDeath(String id, EntityDeathEvent event){
        EntityEvent.OnDeath(id, event);
    }

    //Drops
    public void setDrop(LivingEntity entity, EntityLoot loot){
        EntityUtils.SetDrop(entity, loot);
    }
    public void setDrop(String id, EntityLoot loot){
        EntityUtils.SetDrop(id, loot);
    }
}
