package dev.gump.mars.chat;

import dev.gump.mars.Utils;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class ChatUtils {
    static HashMap<String, ChatMessage> chatMessages = new HashMap<>();
    static float volume = .5f;
    static float pitch = 1f;

    public static void registerChatMessage(String key, ChatMessage chatMessage){ chatMessages.put(key, chatMessage); }

    public static float getVolume() { return volume; }
    public static void setVolume(float _volume) { volume = _volume; }

    public static float getPitch() { return pitch; }
    public static void setPitch(float _pitch) { pitch = _pitch; }

    public static void Send(Player pl, String messageKey, String message){
        Send(pl, messageKey, message, false);
    }
    public static void Send(Player pl, String messageKey, String message, boolean supressSound){
        ChatMessage values = chatMessages.get(messageKey);
        if(values == null) return;
        TextComponent text = new TextComponent(values.getMessage(pl) + Utils.colorize(message));
        if(values.getClickEvent() != null) text.setClickEvent(values.getClickEvent());
        if(values.getHoverEvent() != null) text.setHoverEvent(values.getHoverEvent());
        pl.sendMessage(text);
        if(!supressSound && values.getSound() != null) pl.playSound(pl.getLocation(),values.getSound(),volume,pitch);
    }
    public static void Send(Player pl, String messageKey, HashMap<String, String> replacer){ Send(pl, messageKey, replacer,false); }
    public static void Send(Player pl, String messageKey, HashMap<String, String> replacer, boolean supressSound){
        ChatMessage values = chatMessages.get(messageKey);
        if(values == null) return;
        String msg = values.getMessage(pl);
        for(String key : replacer.keySet())
            msg = msg.replaceAll(key, Utils.colorize(replacer.get(key)));
        TextComponent text = new TextComponent(values.getMessage(pl) + Utils.colorize(msg));
        if(values.getClickEvent() != null) text.setClickEvent(values.getClickEvent());
        if(values.getHoverEvent() != null) text.setHoverEvent(values.getHoverEvent());
        pl.sendMessage(text);
        if(!supressSound && values.getSound() != null) pl.playSound(pl.getLocation(),values.getSound(),volume,1);
    }

    public static void OnSay(Player pl, ChatEvent event){ ChatListener.playerChatList.put(pl, event); }
    public static void RemoveEvent(Player pl){ ChatListener.playerChatList.remove(pl, ChatListener.playerChatList.get(pl)); }
}
