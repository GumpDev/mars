package dev.gump.mars.entity;

import org.bukkit.event.entity.EntityDamageByEntityEvent;

public interface EntityHitEvent {
    void OnHit(EntityDamageByEntityEvent event);
}
