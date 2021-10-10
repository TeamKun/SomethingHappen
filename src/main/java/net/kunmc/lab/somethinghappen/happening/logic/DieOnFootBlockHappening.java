package net.kunmc.lab.somethinghappen.happening.logic;

import net.kunmc.lab.somethinghappen.SomethingHappen;
import net.kunmc.lab.somethinghappen.happening.HappeningManager;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class DieOnFootBlockHappening extends Happening{

    private BukkitTask task;

    public DieOnFootBlockHappening(String name, String title) {
        super(name, title);
    }

    public void continueHappening() {
        task = new BukkitRunnable() {
            @Override
            public void run() {
                HappeningManager.getHappeningTargetPlayers().forEach(p -> {
                    Block block = p.getLocation().getBlock().getRelative(BlockFace.DOWN);
                    if (isDeathBlock(block)) {
                        p.damage(1000);
                    }
                });
            }
        }.runTaskTimer(SomethingHappen.getPlugin(), 0, 5);
    }

    public void endHappening() {
        task.cancel();
    }

    private boolean isDeathBlock(Block block){
        if (block.getType() == Material.DIRT ||
                block.getType() == Material.GRASS_BLOCK ||
                block.getType() == Material.NETHERRACK) {
            return true;
        }
        return false;
    }
}
