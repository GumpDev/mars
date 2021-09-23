package dev.gump.mars.chat;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;

public class ChatListener implements Listener {
    public static HashMap<Player, ChatEvent> playerChatList = new HashMap<>();

    public ChatListener(Plugin plugin){
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerChat(PlayerChatEvent event){
        ChatEvent chatEvent = playerChatList.get(event.getPlayer());
        if(chatEvent == null) return;
        chatEvent.OnSay(event);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event){
        playerChatList.remove(event.getPlayer());
    }
}
