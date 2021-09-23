package dev.gump.mars.chat;

import io.papermc.paper.event.player.AsyncChatEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public interface ChatEvent {
    void OnSay(PlayerChatEvent event);
}
