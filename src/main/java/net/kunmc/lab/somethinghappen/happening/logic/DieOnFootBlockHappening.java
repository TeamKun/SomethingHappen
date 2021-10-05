package net.kunmc.lab.somethinghappen.happening.logic;

import net.kunmc.lab.somethinghappen.Somethinghappen;
import net.kunmc.lab.somethinghappen.game.GameManager;
import net.kunmc.lab.somethinghappen.happening.HappeningConst;
import net.kunmc.lab.somethinghappen.happening.HappeningManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class DieOnFootBlockHappening extends Happening{

    private BukkitTask task;

    public DieOnFootBlockHappening(String name) {
        super(name);
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
        }.runTaskTimer(Somethinghappen.getPlugin(), 0, 5);
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
