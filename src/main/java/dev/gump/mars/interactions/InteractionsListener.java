package dev.gump.mars.interactions;

import dev.gump.mars.Mars;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InteractionsListener implements Listener {
    public static HashMap<Entity, MobInteract> mobInteract = new HashMap<>();
    public static HashMap<ItemStack, ItemInteract> itemInteract = new HashMap<>();
    public static HashMap<Material, BlockInteract> blockInteract = new HashMap<>();
    public static List<Player> antiDoubleClick1 = new ArrayList<>();
    public static List<Player> antiDoubleClick2 = new ArrayList<>();
    public static List<Player> antiDoubleClick3 = new ArrayList<>();

    public InteractionsListener(Plugin plugin){
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    boolean CompareItem(ItemStack right, ItemStack compare){
        ItemStack item = new ItemStack(compare.getType(),right.getAmount());
        if(right.getItemMeta() != null){
            ItemMeta meta = compare.getItemMeta();
            assert meta != null;
            meta.setDisplayName(right.getItemMeta().getDisplayName());
            meta.setLore(right.getItemMeta().getLore());
            item.setItemMeta(meta);
        }
        return right.equals(item);
    }

    ItemInteract GetItem(ItemStack compare){
        if(compare == null) return null;
        ItemInteract retorno = null;
        for(ItemStack item : itemInteract.keySet()){
            if(CompareItem(item,compare)) {
                retorno = itemInteract.get(item);
                break;
            }
        }
        return retorno;
    }

    @EventHandler
    public void OnMobInteract(PlayerInteractAtEntityEvent event){
        if(antiDoubleClick1.contains(event.getPlayer())) return;
        if(mobInteract.containsKey(event.getRightClicked())) {
            mobInteract.get(event.getRightClicked()).OnClick(event);
            antiDoubleClick1.add(event.getPlayer());
            new BukkitRunnable() {
                @Override
                public void run() {
                    antiDoubleClick1.remove(event.getPlayer());
                }
            }.runTaskLater(Mars.getPlugin(), 10);
        }
    }

    @EventHandler
    public void OnMobDeath(EntityDeathEvent event){
        mobInteract.remove(event.getEntity());
    }

    @EventHandler
    public void OnInteract(PlayerInteractEvent event){
        ItemInteract action = GetItem(event.getItem());
        if(action != null) {
            if(antiDoubleClick2.contains(event.getPlayer())) return;
            action.OnClick(event);
            antiDoubleClick2.add(event.getPlayer());
            new BukkitRunnable() {
                @Override
                public void run() {
                    antiDoubleClick2.remove(event.getPlayer());
                }
            }.runTaskLater(Mars.getPlugin(), 10);
        }
        if(event.getClickedBlock() != null && blockInteract.containsKey(event.getClickedBlock().getType())) {
            if(antiDoubleClick3.contains(event.getPlayer())) return;
            blockInteract.get(event.getClickedBlock().getType()).OnClick(event);
            antiDoubleClick3.add(event.getPlayer());
            new BukkitRunnable() {
                @Override
                public void run() {
                    antiDoubleClick3.remove(event.getPlayer());
                }
            }.runTaskLater(Mars.getPlugin(), 10);
        }
    }
}
