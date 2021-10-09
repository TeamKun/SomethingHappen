package net.kunmc.lab.somethinghappen.happening.logic;

import net.kunmc.lab.somethinghappen.Somethinghappen;
import net.kunmc.lab.somethinghappen.happening.HappeningManager;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class ProhibitWaterHappening extends Happening {
    private BukkitTask task;

    public ProhibitWaterHappening(String name, String title) {
        super(name, title);
    }

    public void continueHappening() {
        task = new BukkitRunnable() {
            @Override
            public void run() {
                HappeningManager.getHappeningTargetPlayers().forEach(p -> {
                    if (p.isInWater()){
                        p.damage(1000);
                    }
                    //Location loc = p.getLocation();
                    //if (loc.getBlock().getType() == Material.WATER)
                    //{
                    //    p.damage(1000);
                    //}
                });
            }
        }.runTaskTimer(Somethinghappen.getPlugin(), 0, 2);
    }

    public void endHappening() {
        task.cancel();
    }
}
