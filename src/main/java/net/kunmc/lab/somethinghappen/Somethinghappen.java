package net.kunmc.lab.somethinghappen;

import net.kunmc.lab.somethinghappen.event.PlayerEventHandler;
import net.kunmc.lab.somethinghappen.task.Task;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

public final class Somethinghappen extends JavaPlugin {
    private static Somethinghappen plugin;

    // 全体を管理するタイマー
    private BukkitTask task;

    public static Somethinghappen getPlugin() {
        return plugin;
    }

    @Override
    public void onEnable() {
        plugin = this;
        getServer().getPluginManager().registerEvents(new PlayerEventHandler(), plugin);
        task = new Task(plugin).runTaskTimer(this, 0, 1);
        Config.loadConfig(false);

        getLogger().info("Somethinghappen is enabled");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("Somethinghappen Plugin is disabled");
    }
}
