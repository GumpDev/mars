package dev.gump.mars.helpers;

import java.util.Arrays;
import java.util.List;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public class ItemHelper {
  public static final Material MOCKED_TYPE = Material.STONE;
  public static final int MOCKED_AMOUNT = 5;
  public static final String MOCKED_DISPLAY_NAME = "&6test-name";
  public static final List<String> MOCKED_LORE = Arrays.asList("test-lore-1", "&7test-lore-2");
  public static final Enchantment MOCKED_ENCHANTMENT = Enchantment.PROTECTION_FIRE;
  public static final int MOCKED_ENCHANTMENT_LEVEL = 2;

  public static ItemStack mockGenericItem() {
    final var itemStack = new ItemStack(MOCKED_TYPE, MOCKED_AMOUNT);
    final var itemMeta = itemStack.getItemMeta();
    itemMeta.setDisplayName(MOCKED_DISPLAY_NAME);
    itemStack.setItemMeta(itemMeta);
    return itemStack;
  }

  public static ItemStack mockFullGenericItem() {
    ItemStack itemStack = new ItemStack(MOCKED_TYPE, MOCKED_AMOUNT);
    itemStack.addUnsafeEnchantment(MOCKED_ENCHANTMENT, MOCKED_ENCHANTMENT_LEVEL);
    ItemMeta itemMeta = itemStack.getItemMeta();
    itemMeta.setDisplayName(MOCKED_DISPLAY_NAME);
    itemMeta.setLore(MOCKED_LORE);
    itemStack.setItemMeta(itemMeta);
    return itemStack;
  }

  public static ItemStack mockPersistentDataItem(
      final NamespacedKey namespacedKey, final int value) {
    final var itemStack = new ItemStack(MOCKED_TYPE, MOCKED_AMOUNT);
    final var itemMeta = itemStack.getItemMeta();
    itemMeta.getPersistentDataContainer().set(namespacedKey, PersistentDataType.INTEGER, value);
    itemStack.setItemMeta(itemMeta);
    return itemStack;
  }
}
