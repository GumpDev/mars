package dev.gump.mars.items;

import dev.gump.mars.utils.ColorUtils;
import dev.gump.mars.utils.PersistentUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

public class ItemBuilder {

  private ItemStack itemStack = null;
  private final Material type;
  private int amount = 0;
  private String displayName = null;
  private List<String> lore = null;
  private Map<NamespacedKey, Object> persistentData = new HashMap<>();
  private Map<Enchantment, Integer> enchantments = new HashMap<>();

  protected ItemBuilder(Material type) {
    this.type = type;
  }

  protected ItemBuilder(ItemStack itemStack) {
    this.itemStack = itemStack;

    this.type = itemStack.getType();
    this.amount = itemStack.getAmount();

    for (final var enchant : itemStack.getEnchantments().keySet()) {
      this.enchantments.put(enchant, itemStack.getEnchantmentLevel(enchant));
    }

    final var itemMeta = itemStack.getItemMeta();

    this.displayName = itemMeta.getDisplayName();
    this.lore = itemMeta.getLore();
    final var persistent = itemMeta.getPersistentDataContainer();
    for (final var persistentKey : persistent.getKeys()) {
      this.persistentData.put(
        persistentKey,
        persistent.get(
          persistentKey, PersistentUtils.getPersistentDataType(persistent, persistentKey)));
    }
  }

  public static ItemBuilder builder(ItemStack itemStack) {
    return new ItemBuilder(itemStack);
  }

  public static ItemBuilder builder(Material type) {
    return new ItemBuilder(type);
  }

  public ItemBuilder amount(int amount) {
    this.amount = amount;
    return this;
  }

  public ItemBuilder displayName(String displayName) {
    this.displayName = displayName;
    return this;
  }

  public ItemBuilder lore(String lore) {
    if (this.lore == null) {
      this.lore = new ArrayList<>();
    }

    this.lore.add(lore);
    return this;
  }

  public ItemBuilder lore(int index, String lore) {
    if (this.lore == null) {
      this.lore = new ArrayList<>();
    }

    this.lore.set(index, lore);
    return this;
  }

  public ItemBuilder lore(List<String> lore) {
    this.lore = new ArrayList<>(lore);
    return this;
  }

  public ItemBuilder persistentData(Map<NamespacedKey, Object> persistentData) {
    this.persistentData = persistentData;
    return this;
  }

  public ItemBuilder persistentData(NamespacedKey persistentKey, Object value) {
    this.persistentData.put(persistentKey, value);
    return this;
  }

  public ItemBuilder enchantment(Enchantment enchantment, int level) {
    if (level < 0) {
      this.enchantments.remove(enchantment);
    } else {
      this.enchantments.put(enchantment, level);
    }
    return this;
  }

  public ItemBuilder enchantment(Map<Enchantment, Integer> enchantments) {
    this.enchantments = enchantments;
    return this;
  }

  // - Item Drop - COm tempo ou one time - outra classe ItemActions
  // - Item Give - Give to player - outra classe ItemActions

  public ItemStack build() {
    final var item = itemStack != null ? itemStack : new ItemStack(type);
    item.setAmount(amount);

    for (final var enchant : this.enchantments.keySet()) {
      item.addUnsafeEnchantment(enchant, this.enchantments.get(enchant));
    }

    final var meta = item.getItemMeta();

    if (displayName != null) {
      meta.setDisplayName(ColorUtils.colorize(displayName));
    }

    if (lore != null) {
      meta.setLore(ColorUtils.colorize(lore));
    }

    final var persistent = meta.getPersistentDataContainer();
    for (final var persistentKey : persistentData.keySet()) {
      final var persistentValue = persistentData.get(persistentKey);
      persistent.set(
        persistentKey, PersistentUtils.getPersistentDataType(persistentValue), persistentValue);
    }

    item.setItemMeta(meta);
    return item;
  }
}
