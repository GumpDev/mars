package dev.gump.mars.entity;

import dev.gump.mars.Mars;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EntityListener implements Listener {
    public EntityListener(Plugin plugin){
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    public static HashMap<Entity, EntityHitEvent> hitHandlers = new HashMap<>();
    public static HashMap<Entity, EntityInteractionEvent> interactionHandlers = new HashMap<>();
    public static HashMap<Entity, EntityDeathEvent> deathHandlers = new HashMap<>();
    public static HashMap<Entity, EntityLoot> dropHandlers = new HashMap<>();
    public static HashMap<String, EntityHitEvent> hitIdHandlers = new HashMap<>();
    public static HashMap<String, EntityInteractionEvent> interactionIdHandlers = new HashMap<>();
    public static HashMap<String, EntityDeathEvent> deathIdHandlers = new HashMap<>();
    public static HashMap<String, EntityLoot> dropIdHandlers = new HashMap<>();


    private static List<Player> cooldown = new ArrayList<>();

    @EventHandler
    public void onHit2(EntityDamageByEntityEvent event){
        if(!Mars.entity.hasPersistentData(event.getEntity(), PersistentDataType.STRING, EntityConts.marsHitEvent)) return;
        String value = Mars.entity.getPersistentData(event.getEntity(), PersistentDataType.STRING, EntityConts.marsHitEvent);
        if(!hitIdHandlers.containsKey(value)) return;
        hitIdHandlers.get(value).OnHit(event);
    }
    @EventHandler
    public void onHit(EntityDamageByEntityEvent event){
        if(!hitHandlers.containsKey(event.getEntity())) return;
        hitHandlers.get(event.getEntity()).OnHit(event);
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onDeath(org.bukkit.event.entity.EntityDeathEvent event){
        if(deathHandlers.containsKey(event.getEntity())) {
            deathHandlers.get(event.getEntity()).OnDeath(event);
            deathHandlers.remove(event.getEntity(),deathHandlers.get(event.getEntity()));
        }
        if(hitHandlers.containsKey(event.getEntity()))
            hitHandlers.remove(event.getEntity(),hitHandlers.get(event.getEntity()));
        if(interactionHandlers.containsKey(event.getEntity()))
            interactionHandlers.remove(event.getEntity(),interactionHandlers.get(event.getEntity()));
        if(dropHandlers.containsKey(event.getEntity())) {
            event.getDrops().clear();
            for(ItemStack item : dropHandlers.get(event.getEntity()).getDrop())
                event.getDrops().add(item);
            dropHandlers.remove(event.getEntity(), dropHandlers.get(event.getEntity()));
        }
    }
    @EventHandler
    public void onDeath2(org.bukkit.event.entity.EntityDeathEvent event){
        if(!Mars.entity.hasPersistentData(event.getEntity(), PersistentDataType.STRING, EntityConts.marsDeathEvent)) return;
        String value = Mars.entity.getPersistentData(event.getEntity(), PersistentDataType.STRING, EntityConts.marsDeathEvent);
        if(!deathIdHandlers.containsKey(value)) return;
        deathIdHandlers.get(value).OnDeath(event);
    }
    @EventHandler(priority = EventPriority.HIGH)
    public void onDeath3(org.bukkit.event.entity.EntityDeathEvent event){
        if(!Mars.entity.hasPersistentData(event.getEntity(), PersistentDataType.STRING, EntityConts.marsDrop)) return;
        String value = Mars.entity.getPersistentData(event.getEntity(), PersistentDataType.STRING, EntityConts.marsDrop);
        if(!dropIdHandlers.containsKey(value)) return;
        event.getDrops().clear();
        for(ItemStack item : dropIdHandlers.get(value).getDrop())
            event.getDrops().add(item);
    }

    @EventHandler
    public void onInteract2(PlayerInteractAtEntityEvent event){
        if(cooldown.contains(event.getPlayer())) return;
        if(!Mars.entity.hasPersistentData(event.getRightClicked(), PersistentDataType.STRING, EntityConts.marsInteractEvent)) return;
        String value = Mars.entity.getPersistentData(event.getRightClicked(), PersistentDataType.STRING, EntityConts.marsInteractEvent);
        if(!interactionIdHandlers.containsKey(value)) return;
        interactionIdHandlers.get(value).OnInteract(event);

        cooldown.add(event.getPlayer());
        new BukkitRunnable() {
            @Override
            public void run() {
                cooldown.remove(event.getPlayer());
            }
        }.runTaskLater(Mars.getPlugin(), 10);
    }
    @EventHandler
    public void onInteract(PlayerInteractAtEntityEvent event){
        if(cooldown.contains(event.getPlayer())) return;
        if(!interactionHandlers.containsKey(event.getRightClicked())) return;
        interactionHandlers.get(event.getRightClicked()).OnInteract(event);

        cooldown.add(event.getPlayer());
        new BukkitRunnable() {
            @Override
            public void run() {
                cooldown.remove(event.getPlayer());
            }
        }.runTaskLater(Mars.getPlugin(), 10);
    }
}
