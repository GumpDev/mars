package dev.gump.mars.chat;

import dev.gump.mars.Utils;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class ChatMessage {
    private String message;
    private Sound sound;
    private ClickEvent clickEvent;
    private HoverEvent hoverEvent;

    public ChatMessage(String _msg, Sound _sound, ClickEvent _clickEvent, HoverEvent _hoverEvent){
        message = Utils.colorize( _msg);
        sound = _sound;
        clickEvent = _clickEvent;
        hoverEvent = _hoverEvent;
    }
    public ChatMessage(String _msg, Sound _sound, HoverEvent _hoverEvent){
        message = Utils.colorize( _msg);
        sound = _sound;
        hoverEvent = _hoverEvent;
    }
    public ChatMessage(String _msg, Sound _sound, ClickEvent _clickEvent){
        message = Utils.colorize( _msg);
        sound = _sound;
        clickEvent = _clickEvent;
    }
    public ChatMessage(String _msg, Sound _sound){
        message = Utils.colorize( _msg);
        sound = _sound;
    }

    public String getMessage(Player pl){ return message;}
    public Sound getSound(){
        return sound;
    }
    public ClickEvent getClickEvent() { return clickEvent; }
    public HoverEvent getHoverEvent() { return hoverEvent; }
}
