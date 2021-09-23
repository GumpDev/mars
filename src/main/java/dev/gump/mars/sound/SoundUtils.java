package dev.gump.mars.sound;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class SoundUtils {
    public static void Play(Player player, Sound sound){
        Play(player,sound,1,1,false);
    }
    public static void Play(Player player, Sound sound, int volume){
        Play(player,sound,volume,1, false);
    }
    public static void Play(Player player, Sound sound, int volume, int pitch){
        Play(player,sound,volume,pitch,false);
    }
    public static void Play(Player player, Sound sound, int volume, int pitch, boolean global){
        if(global)
            Play(player.getLocation(),sound,volume,pitch);
        else
            player.playSound(player.getLocation(),sound,volume,pitch);
    }

    public static void Play(Location location, Sound sound){
        Play(location,sound,1,1);
    }
    public static void Play(Location location, Sound sound, int volume){
        Play(location,sound,volume,1);
    }
    public static void Play(Location location, Sound sound, int volume, int pitch){
        if(location.getWorld() == null) return;
        location.getWorld().playSound(location,sound,volume,pitch);
    }
}
