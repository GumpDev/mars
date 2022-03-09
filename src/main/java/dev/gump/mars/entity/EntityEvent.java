package dev.gump.mars.entity;

import org.bukkit.entity.Entity;

public class EntityEvent {
    protected static void OnHit(Entity entity, EntityHitEvent event){
        EntityListener.hitHandlers.put(entity, event);
    }
    protected static void OnInteract(Entity entity, EntityInteractionEvent event){
        EntityListener.interactionHandlers.put(entity, event);
    }
    protected static void OnDeath(Entity entity, EntityDeathEvent event){
        EntityListener.deathHandlers.put(entity, event);
    }
    protected static void OnHit(String id, EntityHitEvent event){
        EntityListener.hitIdHandlers.put(id, event);
    }
    protected static void OnInteract(String id, EntityInteractionEvent event){
        EntityListener.interactionIdHandlers.put(id, event);
    }
    protected static void OnDeath(String id, EntityDeathEvent event){
        EntityListener.deathIdHandlers.put(id, event);
    }
}
