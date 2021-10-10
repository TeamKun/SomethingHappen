package net.kunmc.lab.somethinghappen.happening.logic;

import net.kunmc.lab.somethinghappen.SomethingHappen;
import net.kunmc.lab.somethinghappen.game.GameManager;
import net.kunmc.lab.somethinghappen.happening.HappeningConst;
import net.kunmc.lab.somethinghappen.happening.HappeningManager;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.List;

/**
 * - 対応する事件
 *   - 'changeFootBlockRandom'
 *   - 'changeFootBlockMagma'
 *   - 'changeFootBlockTNT'
 */
public class ChangeFootBlockHappening extends Happening{

    private BukkitTask task;

    private List<Material> blockList = new ArrayList<>();

    public ChangeFootBlockHappening(String name, String title) {
        super(name, title);
        setRandomBlockData();
    }

    public void continueHappening() {
        task = new BukkitRunnable() {
            @Override
            public void run() {
                HappeningManager.getHappeningTargetPlayers().forEach(p -> {
                    Block block = p.getLocation().getBlock().getRelative(BlockFace.DOWN);
                    if (!shouldChangeBlock(block)) return;
                    BlockData blockData = null;
                    switch (ChangeFootBlockHappening.super.getName()) {
                        case HappeningConst.CHANGE_FOOT_BLOCK_RANDOM:
                            Object[] materials = blockList.toArray();
                            Object material = materials[GameManager.rand.nextInt(materials.length)];
                            blockData = ((Material) material).createBlockData();
                            break;
                        case HappeningConst.CHANGE_FOOT_BLOCK_MAGMA:
                            if (block.getType() == Material.MAGMA_BLOCK) return;
                            blockData = Material.MAGMA_BLOCK.createBlockData();
                            break;
                        case HappeningConst.CHANGE_FOOT_BLOCK_TNT:
                            if (block.getType() == Material.TNT) return;
                            blockData = Material.TNT.createBlockData();
                            break;
                    }
                    block.setBlockData(blockData);
                });
            }
        }.runTaskTimer(SomethingHappen.getPlugin(), 0, 5);
    }

    @Override
    public void endHappening() {
        task.cancel();
    }

    private void setRandomBlockData () {
        for(Material material: Material.values()) {
            if (material == Material.AIR || material == Material.END_PORTAL || material == Material.END_GATEWAY
                    || material == Material.NETHER_PORTAL
                    || !material.isBlock())
                continue;
            blockList.add(material);
        }
    }

    private boolean shouldChangeBlock (Block block) {
        Material material = block.getType();
        if (material == Material.AIR || material == Material.WATER || material == Material.LAVA)
            return false;
        return true;
    }
}
