package net.kunmc.lab.somethinghappen.happening.logic;

import net.kunmc.lab.somethinghappen.Config;
import net.kunmc.lab.somethinghappen.game.GameManager;
import net.kunmc.lab.somethinghappen.happening.HappeningConst;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * - 対応する事件
 * - 'spawnPassiveMob'
 * - 'spawnZombie'
 * - 'spawnCleeper'
 * - 'spawnEnderman'
 * - 'spawnBat'
 */
public class SpawnMobHappening extends Happening {
    public SpawnMobHappening(String name, String title) {
        super(name, title);
    }

    public void beginHappening(List<Player> players) {
        players.forEach(p -> {
            Location loc = p.getLocation();
            double x = loc.getX();
            double y = loc.getY() + 3;
            double z = loc.getZ();
            for (int i = 0; i < Config.spawnMobNum; i++) {
                // 周辺の-5~-10, 5~10くらいにspawnする
                int shiftSpawnX = 5 + GameManager.rand.nextInt(5);
                int shiftSpawnZ = 5 + GameManager.rand.nextInt(5);
                if (GameManager.rand.nextInt(2) == 1) shiftSpawnX *= -1;
                if (GameManager.rand.nextInt(2) == 1) shiftSpawnZ *= -1;
                Location spawnLocation = new Location(p.getWorld(), x + shiftSpawnX, y, z + shiftSpawnZ);
                EntityType entityType = null;
                switch (super.getName()) {
                    case HappeningConst.SPAWN_PASSIVE_MOB:
                        int r = GameManager.rand.nextInt(5);
                        switch (r) {
                            case 0:
                                entityType = EntityType.COW;
                                break;
                            case 1:
                                entityType = EntityType.PIG;
                                break;
                            case 2:
                                entityType = EntityType.CHICKEN;
                                break;
                            case 3:
                                entityType = EntityType.SHEEP;
                                break;
                            case 4:
                                entityType = EntityType.HORSE;
                                break;
                        }
                        break;
                    case HappeningConst.SPAWN_ZOMBIE:
                        entityType = EntityType.ZOMBIE;
                        break;
                    case HappeningConst.SPAWN_CLEEPER:
                        entityType = EntityType.CREEPER;
                        break;
                    case HappeningConst.SPAWN_ENDERMAN:
                        entityType = EntityType.ENDERMAN;
                        break;
                    case HappeningConst.SPAWN_BAT:
                        entityType = EntityType.BAT;
                        break;
                }
                if (entityType != null) {
                    p.getWorld().spawnEntity(spawnLocation, entityType);
                }
            }
        });
    }
}
