package dev.gump.mars.chat;

import dev.gump.mars.interactions.InteractionsListener;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;

public class ChatManager {
    ChatListener listener;
    public void init(Plugin plugin){
        listener = new ChatListener(plugin);
    }

    public void registerChatMessage(String key, ChatMessage chatMessage){ ChatUtils.registerChatMessage(key, chatMessage); }

    public float getVolume() { return ChatUtils.volume; }
    public void setVolume(float _volume) { ChatUtils.volume = _volume; }

    public float getPitch() { return ChatUtils.pitch; }
    public void setPitch(float _pitch) { ChatUtils.pitch = _pitch; }

    public void send(Player pl, String messageKey, String message){
        ChatUtils.Send(pl, messageKey, message, false);
    }
    public void send(Player pl, String messageKey, String message, boolean supressSound){ ChatUtils.Send(pl, messageKey, message, supressSound); }
    public void send(Player pl, String messageKey, HashMap<String, String> replacer){ ChatUtils.Send(pl, messageKey, replacer); }
    public void send(Player pl, String messageKey, HashMap<String, String> replacer, boolean supressSound){ ChatUtils.Send(pl, messageKey, replacer, supressSound); }

    public void onSay(Player pl, ChatEvent event){ ChatUtils.OnSay(pl, event); }
    public void removeEvent(Player pl){ ChatUtils.RemoveEvent(pl); }
}
