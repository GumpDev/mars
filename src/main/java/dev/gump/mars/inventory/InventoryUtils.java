package dev.gump.mars.inventory;

import dev.gump.mars.Mars;
import dev.gump.mars.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public class InventoryUtils {
    public static Inventory CreateInventory(String name, int slots, ItemStack... items) {
        Inventory inv = Bukkit.createInventory(null, slots, Utils.colorize(name));
        for (int i = 0; i < items.length; i++) {
            if (items[i] == null)
                continue;
            inv.setItem(i, items[i]);
        }
        return inv;
    }

    public static GUI CreateGUI(String name, int slots, ItemStack... items) {
        Inventory inv = CreateInventory(name, slots, items);
        return new GUI(inv);
    }

    public static GUIPagination CreateGUIPagination(String title, String description, Material itemMaterial,
                                                   GUIPaginationEvents paginationEvents) {
        return new GUIPagination(title, description, itemMaterial, paginationEvents);
    }
    public static GUIPagination CreateGUIPagination(String title,
                                                    GUIPaginationEvents paginationEvents) {
        return new GUIPagination(title, "", null, paginationEvents);
    }

    public static GUIHeader CreateGUIHeader(Inventory inv) {
        return CreateGUIHeader(inv, null, null);
    }

    public static GUIHeader CreateGUIHeader(Inventory inv, ItemStack title) {
        return CreateGUIHeader(inv, title, null);
    }

    public static GUIHeader CreateGUIHeader(Inventory inv, GUIClickEvent clickEvent) {
        return CreateGUIHeader(inv, null, clickEvent);
    }

    public static GUIHeader CreateGUIHeader(Inventory inv, ItemStack title, GUIClickEvent clickEvent) {
        return new GUIHeader(inv, title, clickEvent);
    }



    public static GUIPagination CreateGUIPlayers(String title, List<String> playerDesc, GUIPlayersEvent playerSelectedEvent){
        return CreateGUIPlayers(title, "", null, playerDesc, playerSelectedEvent, event -> {event.getWhoClicked().closeInventory();});
    }
    public static GUIPagination CreateGUIPlayers(String title, GUIPlayersEvent playerSelectedEvent){
        return CreateGUIPlayers(title, "", null, Collections.emptyList(), playerSelectedEvent, event -> {event.getWhoClicked().closeInventory();});
    }
    public static GUIPagination CreateGUIPlayers(String title, GUIPlayersEvent playerSelectedEvent, GUIClickEvent backEvent){
        return CreateGUIPlayers(title, "", null, Collections.emptyList(), playerSelectedEvent, backEvent);
    }
    public static GUIPagination CreateGUIPlayers(String title, List<String> playerDesc, GUIPlayersEvent playerSelectedEvent, GUIClickEvent backEvent){
        return CreateGUIPlayers(title, "", null, playerDesc, playerSelectedEvent, backEvent);
    }
    public static GUIPagination CreateGUIPlayers(String title, String description, Material itemMaterial, List<String> playerDesc, GUIPlayersEvent playerSelectedEvent){
        return CreateGUIPlayers(title, description, itemMaterial, playerDesc, playerSelectedEvent, event -> {event.getWhoClicked().closeInventory();});
    }
    public static GUIPagination CreateGUIPlayers(String title, String description, Material itemMaterial, List<String> playerDesc, GUIPlayersEvent playerSelectedEvent, GUIClickEvent backEvent){
        return new GUIPagination(title, description, itemMaterial, new GUIPaginationEvents() {
            @Override
            public List<PageItem> onPageChange(int page) {
                List<PageItem> result = new ArrayList<>();
                List<Player> players = new ArrayList<>(Bukkit.getOnlinePlayers());
                for (Player player : players.subList((page - 1) * 54, Math.min(players.size(), page * 54))){
                    ItemStack head = Mars.items.createSkullByName(player.getName(), GUIDefaultMessages.getPlayerPrefix() + player.getDisplayName());
                    ItemMeta meta = head.getItemMeta();
                    meta.setLore(playerDesc);
                    head.setItemMeta(meta);
                    result.add(new PageItem(player.getUniqueId().toString(),head));
                }
                return result;
            }

            @Override
            public int getMaxItems() {
                return Bukkit.getOnlinePlayers().size();
            }

            @Override
            public void onClick(InventoryClickEvent event, String uuid) {
                event.getWhoClicked().closeInventory();
                Player player = Bukkit.getPlayer(UUID.fromString(uuid));
                playerSelectedEvent.OnPlayerSelected(player);
            }

            @Override
            public void onBack(InventoryClickEvent event) {
                backEvent.onClick(event);
            }
        });
    }
}
