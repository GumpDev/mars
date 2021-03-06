package dev.gump.mars.time;

import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class Timer {
    public interface TimerCallback {
        void onTick(int tick);
        void onFinish();
        void onPaused();
    }

    private final int time, frequency;
    private final TimerCallback callback;
    private boolean paused = false;
    private int tick;
    private final Plugin plugin;

    public Timer(Plugin plugin, int time, int frequency, TimerCallback callback) {
        this.plugin = plugin;
        this.time = time;
        this.frequency = frequency;
        this.callback = callback;
    }

    public void Start(){
        paused = false;
        Tick();
    }

    public void Pause(){
        paused = true;
    }

    public void Stop(){
        paused = true;
        tick = 0;
    }

    public void Restart(){
        paused = false;
        tick = 0;
    }

    void Tick(){
        new BukkitRunnable(){
            @Override
            public void run() {
                if(paused)
                    callback.onPaused();
                else if(tick >= time)
                    callback.onFinish();
                else {
                    callback.onTick(time - tick);
                    tick += frequency;
                    Tick();
                }
            }
        }.runTaskLater(plugin, frequency);
    }
}
