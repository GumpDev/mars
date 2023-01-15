package dev.gump.mars;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class MarsTest {

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
  void pluginShouldSuccess() {
    Assertions.assertEquals(Mars.getPlugin(), plugin);
  }
}
