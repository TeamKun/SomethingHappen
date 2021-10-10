package net.kunmc.lab.somethinghappen;

import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Config {
    public static int happeningSwitchTime;
    public static int nextHappeningShowTime;
    public static Map<String, Boolean> happenings = new HashMap<>();
    public static int spawnMobNum;
    public static int fallBlockNum;
    public static int teleportRange;
    public static int convertBlockRange;
    public static List<String> womanPlayer = new ArrayList<>();
    public static List<String> nonbinaryPlayer = new ArrayList<>();

    public static void loadConfig(boolean isReload) {

        SomethingHappen plugin = SomethingHappen.getPlugin();

        plugin.saveDefaultConfig();

        if (isReload) {
            plugin.reloadConfig();
        }

        FileConfiguration config = plugin.getConfig();

        happeningSwitchTime = config.getInt("happeningSwitchTime");
        nextHappeningShowTime = config.getInt("nextHappeningShowTime");
        List<String> tmpHappenings = config.getStringList("happening");
        for (String happening: tmpHappenings) {
            happenings.put(happening, true);
        }
        spawnMobNum = config.getInt("spawnMobNum");
        fallBlockNum = config.getInt("fallBlockNum");
        teleportRange = config.getInt("teleportRange");
        convertBlockRange = config.getInt("convertBlockRange");
        womanPlayer = config.getStringList("womanPlayer");
        nonbinaryPlayer = config.getStringList("nonbinaryPlayer");
    }
}
