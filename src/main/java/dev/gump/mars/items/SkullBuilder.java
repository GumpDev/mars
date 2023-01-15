package dev.gump.mars.items;

import dev.gump.mars.utils.SkullUtils;
import dev.gump.mars.utils.StringUtils;
import java.util.UUID;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class SkullBuilder extends ItemBuilder {
  private String skull = null;

  private SkullBuilder() {
    super(Material.PLAYER_HEAD);
  }

  private SkullBuilder(ItemStack itemStack) {
    super(itemStack);
  }

  public static SkullBuilder builder() {
    return new SkullBuilder();
  }

  public static SkullBuilder builder(final ItemStack itemStack) {
    return new SkullBuilder(itemStack);
  }

  public SkullBuilder skull(String str) {
    this.skull = str;
    return this;
  }

  public SkullBuilder skull(UUID uuid) {
    this.skull = uuid.toString();
    return this;
  }

  public ItemStack build() {
    final var itemStack = super.build();

    if (skull == null) return itemStack;
    if (StringUtils.checkUUID(skull))
      return SkullUtils.itemWithUuid(itemStack, UUID.fromString(skull));
    else if (StringUtils.checkUrl(skull)) return SkullUtils.itemWithUrl(itemStack, skull);
    else return SkullUtils.itemWithBase64(itemStack, skull);
  }
}
