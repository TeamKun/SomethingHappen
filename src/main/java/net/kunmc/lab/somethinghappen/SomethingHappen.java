package net.kunmc.lab.somethinghappen;

import net.kunmc.lab.somethinghappen.command.CommandConst;
import net.kunmc.lab.somethinghappen.command.CommandController;
import net.kunmc.lab.somethinghappen.event.EntityEventHandler;
import net.kunmc.lab.somethinghappen.event.PlayerEventHandler;
import net.kunmc.lab.somethinghappen.task.Task;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

public final class SomethingHappen extends JavaPlugin {
    private static SomethingHappen plugin;

    // 全体を管理するタイマー
    private BukkitTask task;

    public static SomethingHappen getPlugin() {
        return plugin;
    }

    @Override
    public void onEnable() {
        plugin = this;
        getServer().getPluginManager().registerEvents(new PlayerEventHandler(), plugin);
        getServer().getPluginManager().registerEvents(new EntityEventHandler(), plugin);
        task = new Task(plugin).runTaskTimer(this, 0, 1);
        Config.loadConfig(false);
        getCommand(CommandConst.MAIN).setExecutor(new CommandController());

        getLogger().info("SomethingHappen is enabled");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("SomethingHappen Plugin is disabled");
    }
}
