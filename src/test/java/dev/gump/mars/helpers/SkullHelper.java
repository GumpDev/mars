package dev.gump.mars.helpers;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class SkullHelper {

  public static ItemStack mockPlayerHead(String playerName) {
    final var mockedItemStack = new ItemStack(Material.PLAYER_HEAD, 1);
    final var mockedSkullMeta = (SkullMeta) mockedItemStack.getItemMeta();
    mockedSkullMeta.setOwner(playerName);
    mockedItemStack.setItemMeta(mockedSkullMeta);
    return mockedItemStack;
  }
}
