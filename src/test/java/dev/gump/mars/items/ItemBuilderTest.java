package dev.gump.mars.items;

import static dev.gump.mars.helpers.ItemHelper.MOCKED_AMOUNT;
import static dev.gump.mars.helpers.ItemHelper.MOCKED_DISPLAY_NAME;
import static dev.gump.mars.helpers.ItemHelper.MOCKED_TYPE;
import static dev.gump.mars.helpers.ItemHelper.mockFullGenericItem;
import static dev.gump.mars.helpers.ItemHelper.mockPersistentDataItem;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import dev.gump.mars.MockPlugin;
import dev.gump.mars.utils.ColorUtils;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class ItemBuilderTest {

  private static ServerMock server;
  private static MockPlugin plugin;

  @BeforeAll
  public static void setUp() {
    server = MockBukkit.mock();
    plugin = MockBukkit.load(MockPlugin.class);
  }

  @AfterAll
  public static void tearDown() {
    MockBukkit.unmock();
  }

  @Test
  void newItemBuilderShouldSuccess() {
    ItemStack itemStack =
      new ItemBuilder(MOCKED_TYPE).amount(MOCKED_AMOUNT).displayName(MOCKED_DISPLAY_NAME).build();

    Assertions.assertEquals(MOCKED_TYPE, itemStack.getType());
    Assertions.assertEquals(MOCKED_AMOUNT, itemStack.getAmount());
    Assertions.assertEquals(
      ColorUtils.colorize(MOCKED_DISPLAY_NAME), itemStack.getItemMeta().getDisplayName());
    Assertions.assertEquals(
      0, itemStack.getItemMeta().getPersistentDataContainer().getKeys().size());
  }

  @Test
  void loadItemBuilderShouldSuccess() {
    final var mockedAmount = 5;

    ItemStack mockedItemStack = mockFullGenericItem();

    ItemStack itemStack =
      ItemBuilder.builder(mockedItemStack)
        .amount(mockedAmount)
        .enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2)
        .build();

    Assertions.assertEquals(mockedItemStack.getType(), itemStack.getType());
    Assertions.assertEquals(mockedAmount, itemStack.getAmount());
    Assertions.assertEquals(
      mockedItemStack.getItemMeta().getDisplayName(), itemStack.getItemMeta().getDisplayName());
    Assertions.assertEquals(
      mockedItemStack.getItemMeta().getLore().size(), itemStack.getItemMeta().getLore().size());
    Assertions.assertEquals(
      mockedItemStack.getItemMeta().getLore().get(0), itemStack.getItemMeta().getLore().get(0));
    Assertions.assertEquals(2, itemStack.getEnchantments().size());
    Assertions.assertEquals(
      2, itemStack.getEnchantments().get(Enchantment.PROTECTION_ENVIRONMENTAL));
    Assertions.assertEquals(2, itemStack.getEnchantments().get(Enchantment.PROTECTION_FIRE));
  }

  @Test
  void loreItemBuilderShouldSuccess() {
    final var mockedLore = Arrays.asList("test-lore-1", "&7test-lore-2");
    final var mockedNewLineLore = "&9test-lore-3";
    final var mockedEditedLineLore = "test-lore-4";

    final var itemStack =
      ItemBuilder.builder(Material.STONE)
        .lore(mockedLore)
        .lore(mockedNewLineLore)
        .lore(0, mockedEditedLineLore)
        .build();

    Assertions.assertEquals(3, itemStack.getItemMeta().getLore().size());
    Assertions.assertLinesMatch(
      ColorUtils.colorize(List.of(mockedEditedLineLore, mockedLore.get(1), mockedNewLineLore)),
      itemStack.getItemMeta().getLore());
  }

  @Test
  void persistentDataItemBuilderShouldSuccess() {
    final var mockedPersistentDataKey =
      new NamespacedKey(MockPlugin.getPlugin(), "persistent-data");
    final var mockedPersistentDataValue = 2;
    final var mockedPersistentDataKey2 =
      new NamespacedKey(MockPlugin.getPlugin(), "persistent-data2");
    final var mockedPersistentDataValue2 = 3;

    final var mockedItemStack =
      mockPersistentDataItem(mockedPersistentDataKey, mockedPersistentDataValue);

    final var itemStack =
      ItemBuilder.builder(mockedItemStack)
        .persistentData(mockedPersistentDataKey2, mockedPersistentDataValue2)
        .build();

    Assertions.assertEquals(
      mockedPersistentDataValue,
      itemStack
        .getItemMeta()
        .getPersistentDataContainer()
        .get(mockedPersistentDataKey, PersistentDataType.INTEGER));
    Assertions.assertEquals(
      mockedPersistentDataValue2,
      itemStack
        .getItemMeta()
        .getPersistentDataContainer()
        .get(mockedPersistentDataKey2, PersistentDataType.INTEGER));
  }

  @Test
  void setPersistentDataItemBuilderShouldSuccess() {
    final var mockedPersistentDataKey =
      new NamespacedKey(MockPlugin.getPlugin(), "persistent-data");
    final var mockedPersistentDataValue = 2;
    final Map<NamespacedKey, Object> mockedPersistentData =
      Map.of(mockedPersistentDataKey, mockedPersistentDataValue);

    final var itemStack =
      ItemBuilder.builder(Material.STONE).persistentData(mockedPersistentData).build();

    Assertions.assertEquals(
      mockedPersistentDataValue,
      itemStack
        .getItemMeta()
        .getPersistentDataContainer()
        .get(mockedPersistentDataKey, PersistentDataType.INTEGER));
  }

  @Test
  void setEnchantItemBuilderShouldSuccess() {
    final var mockedEnchantments =
      new HashMap<>(Map.of(Enchantment.PROTECTION_ENVIRONMENTAL, 2, Enchantment.KNOCKBACK, 5));

    final var itemStack =
      ItemBuilder.builder(Material.STONE)
        .enchantment(mockedEnchantments)
        .enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, -1)
        .enchantment(Enchantment.SILK_TOUCH, 0)
        .build();

    Assertions.assertEquals(2, itemStack.getEnchantments().size());
    Assertions.assertNotEquals(
      2, itemStack.getEnchantments().get(Enchantment.PROTECTION_ENVIRONMENTAL));
    Assertions.assertEquals(5, itemStack.getEnchantments().get(Enchantment.KNOCKBACK));
    Assertions.assertEquals(0, itemStack.getEnchantments().get(Enchantment.SILK_TOUCH));
  }
}
