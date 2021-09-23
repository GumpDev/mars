package dev.gump.mars.items;

import org.bukkit.event.entity.EntityPickupItemEvent;

public interface PickupEvent {
    void onTryPickup(EntityPickupItemEvent event);
}
