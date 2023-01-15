package dev.gump.mars.items;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mockStatic;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import be.seeseemelk.mockbukkit.entity.PlayerMock;
import dev.gump.mars.MockPlugin;
import dev.gump.mars.helpers.SkullHelper;
import dev.gump.mars.utils.SkullUtils;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.inventory.meta.SkullMeta;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

class SkullBuilderTest {

  private static ServerMock server;
  private static MockPlugin plugin;
  private static List<PlayerMock> players = new ArrayList<>();

  @BeforeAll
  public static void setUp() {
    server = MockBukkit.mock();
    plugin = MockBukkit.load(MockPlugin.class);
    players = List.of(server.addPlayer(), server.addPlayer(), server.addPlayer());
  }

  @AfterAll
  public static void tearDown() {
    MockBukkit.unmock();
  }

  @Test
  void createHeadWithUUIDShouldSuccess() {
    final var mockedUUID = players.get(2).getUniqueId();
    final var itemStack = SkullBuilder.builder().skull(mockedUUID).build();
    final var itemMeta = (SkullMeta) itemStack.getItemMeta();

    Assertions.assertEquals(itemMeta.getOwningPlayer().getName(), players.get(2).getName());
  }

  @Test
  void createHeadWithNameShouldSuccess() {
    final var mockedBase64 =
        "ewogICJ0aW1lc3RhbXAiIDogMTY3MzcxMjI1MzUzMiwKICAicHJvZmlsZUlkIiA6ICI2ZGRiN2Y5YzJhMDg0MTYwYmVlNGIxMzZhZGQ2OWMxNSIsCiAgInByb2ZpbGVOYW1lIiA6ICJNcl9TaXB1bGkiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjkxYWUwYWU5NTgwYzE1MWQ5ODExYWU5MGYwMGFlNzU3MjRiOTgxZGRmMzI0YjQwYTczY2M2YTM3NDk5ZTVjYSIKICAgIH0KICB9Cn0=";
    final var mockedName = "skull-name";

    try (MockedStatic<SkullUtils> mocked = mockStatic(SkullUtils.class)) {
      mocked
          .when(() -> SkullUtils.itemWithBase64(any(), eq(mockedBase64)))
          .thenReturn(SkullBuilder.builder().displayName(mockedName).build());

      final var itemStack = SkullBuilder.builder().skull(mockedBase64).build();
      Assertions.assertEquals(itemStack.getItemMeta().getDisplayName(), mockedName);
    }
  }

  @Test
  void createHeadWithUrlShouldSuccess() {
    final var mockedUrl =
        "http://textures.minecraft.net/texture/b0ea98e6f972b74d190a3c31362e1e928192d3c1e09ca694ba5b4a6dbc8da7f0";
    final var mockedName = "skull-name";

    try (MockedStatic<SkullUtils> mocked = mockStatic(SkullUtils.class)) {
      mocked
          .when(() -> SkullUtils.itemWithUrl(any(), eq(mockedUrl)))
          .thenReturn(SkullBuilder.builder().displayName(mockedName).build());

      final var itemStack = SkullBuilder.builder().skull(mockedUrl).build();
      Assertions.assertEquals(itemStack.getItemMeta().getDisplayName(), mockedName);
    }
  }

  @Test
  void loadHeadShouldSuccess() {
    final var mockedPlayerName = players.get(1).getName();
    final var mockedItemStack = SkullHelper.mockPlayerHead(mockedPlayerName);

    final var itemStack = SkullBuilder.builder(mockedItemStack).build();
    final var skullMeta = (SkullMeta) itemStack.getItemMeta();
    Assertions.assertEquals(skullMeta.getOwner(), mockedPlayerName);
  }
}
