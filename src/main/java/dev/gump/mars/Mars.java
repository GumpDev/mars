package dev.gump.mars;

import dev.gump.mars.armor_stand.ArmorStandManager;
import dev.gump.mars.chat.ChatManager;
import dev.gump.mars.interactions.InteractionsManager;
import dev.gump.mars.inventory.InventoryManager;
import dev.gump.mars.items.ItemsManager;
import dev.gump.mars.scoreboard.ScoreboardManager;
import dev.gump.mars.sound.SoundManager;
import dev.gump.mars.tab.TabManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import java.util.Arrays;
import java.util.List;

public final class Mars {
    static Plugin plugin;

    public static void setPlugin(Plugin plugin) {
        Mars.plugin = plugin;
    }
    public static Plugin getPlugin() {
        return plugin;
    }

    public static ArmorStandManager armorStand = new ArmorStandManager();
    public static ChatManager chat = new ChatManager();
    public static ItemsManager items = new ItemsManager();
    public static InteractionsManager interactions = new InteractionsManager();
    public static InventoryManager inventory = new InventoryManager();
    public static TabManager tab = new TabManager();
    public static SoundManager sound = new SoundManager();
    public static ScoreboardManager scoreboard = new ScoreboardManager();

    public static void init(Plugin plugin){
        setPlugin(plugin);
    }
    public static void init(Plugin plugin, Intent ...intents){
        List<Intent> intentList = Arrays.asList(intents);
        setPlugin(plugin);
        if(containIntent(Intent.ARMOR_STAND, intentList)) armorStand.init(plugin);
        if(containIntent(Intent.CHAT, intentList)) chat.init(plugin);
        if(containIntent(Intent.INTERACTIONS, intentList)) interactions.init(plugin);
        if(containIntent(Intent.INVENTORY, intentList)) inventory.init(plugin);
        if(containIntent(Intent.ITEMS, intentList)) items.init(plugin);
    }
    static boolean containIntent(Intent intent, List<Intent> intents){
        return intents.contains(Intent.ALL) || intents.contains(intent);
    }
}
