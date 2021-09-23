package dev.gump.mars.inventory;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.Collections;
import java.util.List;

public class InventoryManager {
    InventoryListener listener;
    public void init(Plugin plugin){
        listener = new InventoryListener(plugin);
    }

    public final Inventory createInventory(String name, int slots, ItemStack... items) {
        return InventoryUtils.CreateInventory(name, slots, items);
    }

    public final GUI createGUI(String name, int slots, ItemStack... items) {
        return InventoryUtils.CreateGUI(name, slots, items);
    }

    public final GUIPagination createGUIPagination(String title, String description, Material itemMaterial,
                                                   GUIPaginationEvents paginationEvents) {
        return InventoryUtils.CreateGUIPagination(title, description, itemMaterial, paginationEvents);
    }
    public final GUIPagination createGUIPagination(String title, GUIPaginationEvents paginationEvents) {
        return InventoryUtils.CreateGUIPagination(title, paginationEvents);
    }

    public final GUIHeader createGUIHeader(Inventory inv) {
        return InventoryUtils.CreateGUIHeader(inv);
    }

    public final GUIHeader createGUIHeader(Inventory inv, ItemStack title) {
        return InventoryUtils.CreateGUIHeader(inv, title );
    }

    public final GUIHeader createGUIHeader(Inventory inv, GUIClickEvent clickEvent) {
        return InventoryUtils.CreateGUIHeader(inv, clickEvent);
    }

    public final GUIHeader createGUIHeader(Inventory inv, ItemStack title, GUIClickEvent clickEvent) {
        return InventoryUtils.CreateGUIHeader(inv, title, clickEvent);
    }

    public GUIPagination createGUIPlayers(String title, List<String> playerDesc, GUIPlayersEvent playerSelectedEvent){
        return InventoryUtils.CreateGUIPlayers(title, playerDesc, playerSelectedEvent);
    }
    public GUIPagination createGUIPlayers(String title, GUIPlayersEvent playerSelectedEvent){
        return InventoryUtils.CreateGUIPlayers(title, playerSelectedEvent);
    }
    public GUIPagination createGUIPlayers(String title, GUIPlayersEvent playerSelectedEvent, GUIClickEvent backEvent){
        return InventoryUtils.CreateGUIPlayers(title, playerSelectedEvent, backEvent);
    }
    public GUIPagination createGUIPlayers(String title, List<String> playerDesc, GUIPlayersEvent playerSelectedEvent, GUIClickEvent backEvent){
        return InventoryUtils.CreateGUIPlayers(title, playerDesc, playerSelectedEvent, backEvent);
    }
    public GUIPagination createGUIPlayers(String title, String description, Material itemMaterial, List<String> playerDesc, GUIPlayersEvent playerSelectedEvent){
        return InventoryUtils.CreateGUIPlayers(title, description, itemMaterial, playerDesc, playerSelectedEvent);
    }
    public GUIPagination createGUIPlayers(String title, String description, Material itemMaterial, List<String> playerDesc, GUIPlayersEvent playerSelectedEvent, GUIClickEvent backEvent){
        return InventoryUtils.CreateGUIPlayers(title, description, itemMaterial, playerDesc, playerSelectedEvent, backEvent);
    }
}
