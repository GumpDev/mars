package dev.gump.mars.items;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import com.google.common.collect.ArrayListMultimap;
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
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

class ItemBuilderTest {
  private static ServerMock server;
  private static MockPlugin plugin;

  @Mock private ArrayListMultimap arrayListMultimap;

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
    final var mockedType = Material.STONE;
    final var mockedAmount = 5;
    final var mockedDisplayName = "&6test-name";

    ItemStack itemStack =
        new ItemBuilder(mockedType).amount(mockedAmount).displayName(mockedDisplayName).build();

    Assertions.assertEquals(mockedType, itemStack.getType());
    Assertions.assertEquals(mockedAmount, itemStack.getAmount());
    Assertions.assertEquals(
        ColorUtils.colorize(mockedDisplayName), itemStack.getItemMeta().getDisplayName());
    Assertions.assertEquals(
        0, itemStack.getItemMeta().getPersistentDataContainer().getKeys().size());
  }

  @Test
  void loadItemBuilderShouldSuccess() {
    final var mockedAmount = 5;

    ItemStack mockedItemStack = new ItemStack(Material.STONE, 1);
    mockedItemStack.addUnsafeEnchantment(Enchantment.PROTECTION_FIRE, 2);
    ItemMeta mockedItemMeta = mockedItemStack.getItemMeta();
    mockedItemMeta.setDisplayName("test-name");
    mockedItemMeta.setLore(Arrays.asList("test-lore"));
    mockedItemStack.setItemMeta(mockedItemMeta);

    ItemStack itemStack =
        new ItemBuilder(mockedItemStack)
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

    final var mockedItemStack = new ItemStack(Material.STONE, 1);
    final var mockedItemMeta = mockedItemStack.getItemMeta();
    mockedItemMeta
        .getPersistentDataContainer()
        .set(mockedPersistentDataKey, PersistentDataType.INTEGER, mockedPersistentDataValue);
    mockedItemStack.setItemMeta(mockedItemMeta);

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
