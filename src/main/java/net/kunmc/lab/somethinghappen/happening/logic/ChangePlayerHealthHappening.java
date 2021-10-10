package net.kunmc.lab.somethinghappen.happening.logic;

import net.kunmc.lab.somethinghappen.happening.HappeningManager;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ChangePlayerHealthHappening extends Happening {
    public ChangePlayerHealthHappening(String name, String title) {
        super(name, title);
    }

    public void beginHappening(List<Player> players) {
        players.forEach(p -> {
            p.setMaxHealth(1);
            p.setHealth(1);
        });
    }

    public void endHappening() {
        HappeningManager.getHappeningTargetPlayers().forEach(p -> {
            endPlayerHappening(p);
        });
    }

    public void beginHappeningOnLoginOrRespawn(Player player) {
        List<Player> p = new ArrayList<>();
        p.add(player);
        beginHappening(p);
    }

    public void endPlayerHappening(Player player) {
        player.setMaxHealth(20);
    }
}
