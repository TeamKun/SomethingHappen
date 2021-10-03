package net.kunmc.lab.somethinghappen.task;

import net.kunmc.lab.somethinghappen.game.GameManager;
import net.kunmc.lab.somethinghappen.happening.HappeningManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class Task extends BukkitRunnable {
    private JavaPlugin plugin;

    public Task(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        //if (GameManager.runningMode == GameManager.GameMode.NEUTRAL)
        //    return;

        if (HappeningManager.shouldSwitch()) {
            HappeningManager.switchHappening();
        }

        // 一定時間間隔でハプニングを変更する処理
        HappeningManager.incrementTimer();
    }
}