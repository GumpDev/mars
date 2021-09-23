package dev.gump.mars.items;

import org.bukkit.event.player.PlayerDropItemEvent;

public interface DropEvent {
    void onDrop(PlayerDropItemEvent event);
}
