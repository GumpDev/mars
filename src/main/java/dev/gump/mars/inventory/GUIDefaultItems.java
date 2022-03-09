package dev.gump.mars.inventory;

import dev.gump.mars.Mars;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class GUIDefaultItems {
    public static final String backB64= "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDc0NTZiNTA2ZWUzZGNhYjIxNWYzM2NiOTNhOWVkZDk3NDlmM2UyZmY4YTQ4OTM0MjZlMTc4NDY5MmRlOGI1In19fQ==";
    public static final String prevB64= "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzdhZWU5YTc1YmYwZGY3ODk3MTgzMDE1Y2NhMGIyYTdkNzU1YzYzMzg4ZmYwMTc1MmQ1ZjQ0MTlmYzY0NSJ9fX0=";
    public static final String nextB64 = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjgyYWQxYjljYjRkZDIxMjU5YzBkNzVhYTMxNWZmMzg5YzNjZWY3NTJiZTM5NDkzMzgxNjRiYWM4NGE5NmUifX19";
    public static final String errorB64 = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTdmOWM2ZmVmMmFkOTZiM2E1NDY1NjQyYmE5NTQ2NzFiZTFjNDU0M2UyZTI1ZTU2YWVmMGE0N2Q1ZjFmIn19fQ==";

    static ItemStack none = Mars.items.createItemStack(Material.BLACK_STAINED_GLASS_PANE, 1, " ");
    static ItemStack back = Mars.items.createSkullByBase64 (backB64, "&cBack");
    static ItemStack prev = Mars.items.createSkullByBase64 (prevB64,"&6Previous page");
    static ItemStack next = Mars.items.createSkullByBase64 (nextB64, "&6Next page");
    static ItemStack error = Mars.items.createSkullByBase64 (errorB64, "&c&lNo items");

    public static void setBack(ItemStack back) {
        GUIDefaultItems.back = back;
    }
    public static void setNext(ItemStack next) {
        GUIDefaultItems.next = next;
    }
    public static void setNone(ItemStack none) {
        GUIDefaultItems.none = none;
    }
    public static void setPrev(ItemStack prev) {
        GUIDefaultItems.prev = prev;
    }
    public static void setError(ItemStack error) { GUIDefaultItems.error = error; }

    public static void changeBackText(String back) {
        GUIDefaultItems.back = Mars.items.createSkullByBase64 (backB64, back);
    }
    public static void changeNextText(String next) {
        GUIDefaultItems.next = Mars.items.createSkullByBase64 (nextB64, next);
    }
    public static void changeNoneText(String none) {
        GUIDefaultItems.none = Mars.items.createItemStack(Material.BLACK_STAINED_GLASS_PANE, 1, none);
    }
    public static void changePrevText(String prev) {
        GUIDefaultItems.prev = Mars.items.createSkullByBase64 (prevB64, prev);
    }
    public static void changeErrorText(String error) {
        GUIDefaultItems.error = Mars.items.createSkullByBase64 (errorB64, error);
    }

    public static ItemStack getBack() {
        return back;
    }
    public static ItemStack getNext() {
        return next;
    }
    public static ItemStack getNone() {
        return none;
    }
    public static ItemStack getPrev() {
        return prev;
    }
    public static ItemStack getError() { return error; }
}
