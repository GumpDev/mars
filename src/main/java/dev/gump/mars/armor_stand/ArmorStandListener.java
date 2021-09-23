package dev.gump.mars.armor_stand;

import org.bukkit.entity.ArmorStand;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class ArmorStandListener implements Listener {
    public ArmorStandListener(Plugin plugin){
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void OnArmorStandInteract(PlayerInteractAtEntityEvent event){
        if(!(event.getRightClicked() instanceof ArmorStand)) return;
        if(!event.getRightClicked().getPersistentDataContainer().has(ArmorStandConsts.armorStandKey, PersistentDataType.INTEGER)) return;
        event.setCancelled(true);
    }

    @EventHandler
    public void OnArmorStandHit(EntityDamageEvent event){
        if(!(event.getEntity() instanceof ArmorStand)) return;
        if(!event.getEntity().getPersistentDataContainer().has(ArmorStandConsts.armorStandKey, PersistentDataType.INTEGER)) return;
        event.setCancelled(true);
    }
}