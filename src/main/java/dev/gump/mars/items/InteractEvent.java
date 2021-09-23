package dev.gump.mars.items;

import org.bukkit.event.player.PlayerInteractEvent;

public interface InteractEvent {
    void onInteractItem(PlayerInteractEvent event);
}
