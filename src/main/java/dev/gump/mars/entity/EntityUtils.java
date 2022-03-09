package dev.gump.mars.entity;

import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;

import java.util.UUID;

public class EntityUtils {
    protected static LivingEntity CreateEntity(Location location, EntityType entityType, String displayName, double maxHeath){
        LivingEntity entity = (LivingEntity) location.getWorld().spawnEntity(location, entityType);
        if(displayName != null && !displayName.isEmpty()){
            entity.setCustomNameVisible(true);
            entity.setCustomName(displayName);
        }
        if(maxHeath != 0)
            entity.setMaxHealth(maxHeath);
        return entity;
    }
    protected static LivingEntity CreateEntity(Location location, EntityType entityType, String displayName, boolean invunerable){
        LivingEntity entity = (LivingEntity) location.getWorld().spawnEntity(location, entityType);
        if(displayName != null && !displayName.isEmpty()){
            entity.setCustomNameVisible(true);
            entity.setCustomName(displayName);
        }
        entity.setInvulnerable(invunerable);
        return entity;
    }
    protected static LivingEntity CreateEntity(Location location, EntityType entityType, String displayName){
        return CreateEntity(location,entityType, displayName, false);
    }
    protected static LivingEntity CreateEntity(Location location, EntityType entityType){
        return CreateEntity(location, entityType, "", false);
    }

    protected static LivingEntity SetArmor(LivingEntity entity, double armor){
        return EntityAttribute.AddAttribute(entity, new EntityAttribute(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "generic.armor", armor, AttributeModifier.Operation.ADD_NUMBER)));
    }
    protected static LivingEntity SetMovimentSpeed(LivingEntity entity, double speed){
        return EntityAttribute.AddAttribute(entity, new EntityAttribute(Attribute.GENERIC_MOVEMENT_SPEED, new AttributeModifier(UUID.randomUUID(), "generic.movement_speed", speed, AttributeModifier.Operation.ADD_NUMBER)));
    }
    protected static LivingEntity SetAttackSpeed(LivingEntity entity, double speed){
        return EntityAttribute.AddAttribute(entity, new EntityAttribute(Attribute.GENERIC_ATTACK_SPEED, new AttributeModifier(UUID.randomUUID(), "generic.attack_speed", speed, AttributeModifier.Operation.ADD_NUMBER)));
    }
    protected static LivingEntity SetAttackDamage(LivingEntity entity, double damage){
        return EntityAttribute.AddAttribute(entity, new EntityAttribute(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), "generic.attack_damage", damage, AttributeModifier.Operation.ADD_NUMBER)));
    }

    protected static void SetDrop(LivingEntity entity, EntityLoot loot){
        EntityListener.dropHandlers.put(entity, loot);
    }
    protected static void SetDrop(String id, EntityLoot loot){
        EntityListener.dropIdHandlers.put(id, loot);
    }
}
