package dev.gump.mars.entity;

import org.bukkit.event.player.PlayerInteractAtEntityEvent;

public interface EntityInteractionEvent {
    void OnInteract(PlayerInteractAtEntityEvent event);
}
