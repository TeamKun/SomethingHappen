package net.kunmc.lab.somethinghappen.happening.logic;

import net.kunmc.lab.somethinghappen.happening.HappeningManager;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ChangeGameModeHappening extends Happening {
    public ChangeGameModeHappening(String name, String title) {
        super(name, title);
    }

    public void beginHappening(List<Player> players) {
        players.forEach(p -> {
            p.setGameMode(GameMode.ADVENTURE);
        });
    }

    public void endHappening() {
        HappeningManager.getHappeningTargetPlayers().forEach(p -> {
            p.setGameMode(GameMode.SURVIVAL);
        });
    }

    public void beginHappeningOnLoginOrRespawn(Player player) {
        List<Player> p = new ArrayList<>();
        p.add(player);
        beginHappening(p);
    }

    public void endPlayerHappening(Player player) {
        player.setGameMode(GameMode.SURVIVAL);
    }
}
