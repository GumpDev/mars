package dev.gump.mars.sound;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class SoundManager {
    public void play(Player player, Sound sound){
        SoundUtils.Play(player, sound);
    }
    public void play(Player player, Sound sound, int volume){
        SoundUtils.Play(player,sound,volume);
    }
    public void play(Player player, Sound sound, int volume, int pitch){
        SoundUtils.Play(player,sound,volume,pitch);
    }
    public void play(Player player, Sound sound, int volume, int pitch, boolean global){
        SoundUtils.Play(player,sound,volume,pitch, global);
    }

    public void play(Location location, Sound sound){
        SoundUtils.Play(location,sound);
    }
    public void play(Location location, Sound sound, int volume){
        SoundUtils.Play(location,sound,volume);
    }
    public void play(Location location, Sound sound, int volume, int pitch){
        SoundUtils.Play(location,sound,volume,pitch);
    }
}
