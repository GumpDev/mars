package dev.gump.mars.items;

import dev.gump.mars.utils.HeadUtils;
import dev.gump.mars.utils.StringUtils;
import java.util.UUID;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class HeadBuilder extends ItemBuilder {
  private String headValue = null;
  private UUID headUuid = null;

  private HeadBuilder() {
    super(Material.PLAYER_HEAD);
  }

  private HeadBuilder(ItemStack itemStack) {
    super(itemStack);
  }

  public HeadBuilder head(String str) {
    this.headUuid = null;
    this.headValue = str;
    return this;
  }

  public HeadBuilder head(UUID uuid) {
    this.headUuid = uuid;
    this.headValue = null;
    return this;
  }

  public ItemStack build() {
    final var itemStack = super.build();
    if (headUuid != null) return HeadUtils.itemWithUuid(itemStack, headUuid);

    if (headValue != null) {
      if (StringUtils.checkUrl(headValue)) return HeadUtils.itemWithUrl(itemStack, headValue);
      else if (StringUtils.checkBase64(headValue))
        return HeadUtils.itemWithBase64(itemStack, headValue);
      else return HeadUtils.itemWithName(itemStack, headValue);
    }

    return itemStack;
  }
}
