package net.kunmc.lab.somethinghappen.happening.logic;

import net.kunmc.lab.somethinghappen.Config;
import net.kunmc.lab.somethinghappen.game.GameManager;
import net.kunmc.lab.somethinghappen.happening.HappeningConst;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * - 対応する事件
 * - 'teleportPlayer'
 * - 'changePlayerDimension'
 */
public class TeleportPlayerHappening extends Happening {
    private final String overWorld = "world";
    private final String nether = "world_nether";
    private final String end = "world_the_end";

    public TeleportPlayerHappening(String name, String title) {
        super(name, title);
    }

    public void beginHappening(List<Player> players) {
        players.forEach(p -> {
            if (super.getName().equals(HappeningConst.TELEPORT_PLAYER)) {
                Location location = p.getLocation();
                location.setX(location.getX() + GameManager.rand.nextInt(Config.teleportRange * 2) - Config.teleportRange);
                location.setZ(location.getZ() + GameManager.rand.nextInt(Config.teleportRange * 2) - Config.teleportRange);
                p.teleport(location);
            } else if (super.getName().equals(HappeningConst.CHANGE_PLAYER_DIMENSION)) {
                Location location = p.getLocation();
                if (location.getY() > 115) {
                    location.setY(115);
                }
                location.setWorld(Bukkit.getWorld(getNextDimension(p.getWorld().getName())));
                p.teleport(location);
                p.playSound(location, Sound.ENTITY_ENDERMAN_TELEPORT, 1.0f, 1.0f);
            }
        });
    }

    private String getNextDimension(String dimension) {
        switch (dimension) {
            case overWorld:
                return nether;
            case nether:
            case end:
                return overWorld;
        }
        return overWorld;
    }
}