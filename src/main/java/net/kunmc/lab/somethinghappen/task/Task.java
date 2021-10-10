package net.kunmc.lab.somethinghappen.task;

import net.kunmc.lab.somethinghappen.game.GameManager;
import net.kunmc.lab.somethinghappen.happening.HappeningManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import static net.kunmc.lab.somethinghappen.happening.HappeningManager.setNextHappening;

public class Task extends BukkitRunnable {
    private JavaPlugin plugin;

    public Task(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        if (GameManager.runningMode == GameManager.GameMode.NEUTRAL)
            return;

        if (HappeningManager.shouldSwitch()) {
            HappeningManager.switchHappening();
        }

        if (!HappeningManager.updatedNextHappening()) setNextHappening();
        if (HappeningManager.shouldShowNextHappening()) HappeningManager.showNextHappening();

        HappeningManager.incrementTimer();
    }
}