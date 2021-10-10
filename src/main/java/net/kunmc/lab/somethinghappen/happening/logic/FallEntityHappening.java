package net.kunmc.lab.somethinghappen.happening.logic;

import net.kunmc.lab.somethinghappen.Config;
import net.kunmc.lab.somethinghappen.SomethingHappen;
import net.kunmc.lab.somethinghappen.game.GameManager;
import net.kunmc.lab.somethinghappen.happening.HappeningConst;
import net.kunmc.lab.somethinghappen.happening.HappeningManager;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

/**
 * - 対応する事件
 *   - 'fallAnvil'
 *   - 'fallSand'
 *   - 'fallArrow'
 *   - 'fallMeteor'
 */
public class FallEntityHappening extends Happening {

    private BukkitTask task;

    public FallEntityHappening(String name, String title) {
        super(name, title);
    }

    public void continueHappening() {
        task = new BukkitRunnable() {
            @Override
            public void run() {
               HappeningManager.getHappeningTargetPlayers().forEach(p -> {
                   new BukkitRunnable() {
                        @Override
                        public void run() {
                            Location loc = p.getLocation();
                            double x = loc.getX() + GameManager.rand.nextInt(4) - 2;
                            double y = loc.getY() + 20;
                            double z = loc.getZ() + GameManager.rand.nextInt(4) - 2;
                            int num = Config.fallBlockNum;

                            if (FallEntityHappening.super.getName().equals(HappeningConst.FALL_METEOR))
                                num = 1;

                            for (int i = 0; i < num; i++) {
                                Location spawnLocation = new Location(p.getWorld(), (int)x + 0.5 , (int)y, (int)z + 0.5);
                                switch (FallEntityHappening.super.getName()) {
                                    case HappeningConst.FALL_ANVIL:
                                        FallingBlock anvilBlock = p.getWorld().spawnFallingBlock(spawnLocation, Material.ANVIL.createBlockData());
                                        anvilBlock.setDropItem(false);
                                        break;
                                    case HappeningConst.FALL_ARROW:
                                        Arrow arrow = p.getWorld().spawnArrow(spawnLocation, new Vector(0,0,0),1,0);
                                        arrow.setTicksLived(10*20);
                                        break;
                                    case HappeningConst.FALL_SAND:
                                        FallingBlock sandBlock = p.getWorld().spawnFallingBlock(spawnLocation, Material.SAND.createBlockData());
                                        sandBlock.setDropItem(false);
                                        break;
                                    case HappeningConst.FALL_METEOR:
                                        Fireball fireball = (Fireball) p.getWorld().spawnEntity(spawnLocation, EntityType.FIREBALL);
                                        Vector vector = loc.toVector().subtract(fireball.getLocation().toVector()).normalize();
                                        vector.multiply(0.5f);
                                        fireball.setDirection(vector);
                                        fireball.setTicksLived(10*20);
                                        break;
                                }
                            }
                        }
                    }.runTask(SomethingHappen.getPlugin());
                });
            }
        }.runTaskTimerAsynchronously(SomethingHappen.getPlugin(), 0, 20);
    }

    public void endHappening() {
        task.cancel();
    }
}
