package net.kunmc.lab.somethinghappen.happening.logic;

import net.kunmc.lab.somethinghappen.Config;
import net.kunmc.lab.somethinghappen.Somethinghappen;
import net.kunmc.lab.somethinghappen.happening.HappeningManager;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Bed;
import org.bukkit.block.data.type.Door;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.checkerframework.checker.units.qual.C;

public class ConvertBlockHappening extends Happening {
    private BukkitTask task;

    public ConvertBlockHappening(String name, String title) {
        super(name, title);
    }

    public void continueHappening() {
        task = new BukkitRunnable() {
            @Override
            public void run() {
                HappeningManager.getHappeningTargetPlayers().forEach(p -> {
                    double px = p.getLocation().getX();
                    double py = p.getLocation().getY();
                    double pz = p.getLocation().getZ();
                    for (int x = Config.convertBlockRange -1; x <= Config.convertBlockRange; x++) {
                        for (int y = Config.convertBlockRange - 1; y <= Config.convertBlockRange; y++) {
                            for (int z = Config.convertBlockRange - 1; z <= Config.convertBlockRange; z++) {
                                double dist = Math.sqrt(x * x + y * y + z * z);
                                if (dist > Config.convertBlockRange)
                                    continue;

                                Block currentBlock = new Location(p.getWorld(), px, py, pz).getBlock();
                                if (currentBlock.getType() == Material.AIR ||
                                        currentBlock.getBlockData() instanceof Bed ||
                                        currentBlock.getBlockData() instanceof Door) {
                                    currentBlock.setBlockData(Material.STONE.createBlockData());
                                }
                            }
                        }
                    }
                });
            }
        }.runTaskTimer(Somethinghappen.getPlugin(), 0, 5);
    }

    public void endHappening() {
        task.cancel();
    }
}
