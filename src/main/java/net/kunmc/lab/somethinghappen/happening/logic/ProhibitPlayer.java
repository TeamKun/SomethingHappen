package net.kunmc.lab.somethinghappen.happening.logic;

import net.kunmc.lab.somethinghappen.Config;
import net.kunmc.lab.somethinghappen.Somethinghappen;
import net.kunmc.lab.somethinghappen.happening.HappeningConst;
import net.kunmc.lab.somethinghappen.happening.HappeningManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.List;

public class ProhibitPlayer extends Happening {
    private BukkitTask task;

    public ProhibitPlayer(String name, String title) {
        super(name, title);
    }

    public void continueHappening() {
        task = new BukkitRunnable() {
            @Override
            public void run() {

                List<String> procPlayerNames = new ArrayList<>();
                List<String> burnTargetPlayerNames = new ArrayList<>();

                if (HappeningManager.currentHappening.getName().equals(HappeningConst.PROHIBIT_MAN)) {
                    for (Player p : HappeningManager.getHappeningTargetPlayers()) {
                        String name = p.getName();
                        if (isMan(name)) {
                            procPlayerNames.add(name);
                        } else {
                            burnTargetPlayerNames.add(name);
                        }
                    }
                } else if (HappeningManager.currentHappening.getName().equals(HappeningConst.PROHIBIT_WOMAN)) {
                    for (Player p: HappeningManager.getHappeningTargetPlayers()) {
                        String name = p.getName();
                        if (isMan(name)) {
                            burnTargetPlayerNames.add(p.getName());
                        }
                    }
                    procPlayerNames.addAll(Config.womanPlayer);
                }
                System.out.println(procPlayerNames);
                System.out.println(burnTargetPlayerNames);

                for(String procPlayerName: procPlayerNames) {
                    Player procPlayer = Bukkit.getPlayer(procPlayerName);
                    if (procPlayer == null) continue;
                    double px = procPlayer.getLocation().getX();
                    double py = procPlayer.getLocation().getY();
                    double pz = procPlayer.getLocation().getZ();
                    for(String burnTargetPlayerName: burnTargetPlayerNames){
                        Player burnTargetPlayer = Bukkit.getPlayer(burnTargetPlayerName);
                        if (burnTargetPlayer == null) continue;
                        double opx = burnTargetPlayer.getLocation().getX();
                        double opy = burnTargetPlayer.getLocation().getY();
                        double opz = burnTargetPlayer.getLocation().getZ();
                        double diffX = px - opx;
                        double diffZ = pz - opz;
                        double absX = Math.abs(diffX);
                        double absZ = Math.abs(diffZ);

                        if (Math.abs(py - opy) < 1.0 && absX < 1.0 && absZ < 1.0) {
                            System.out.println(burnTargetPlayer.getName());
                            burnTargetPlayer.setFireTicks(100);
                        }
                    }
                }
            }
        }.runTaskTimer(Somethinghappen.getPlugin(), 0, 2);
    }

    public void endHappening() {
        task.cancel();
    }

    private boolean isMan(String name) {
        if (!Config.womanPlayer.contains(name) && !Config.nonbinaryPlayer.contains(name)) {
            return true;
        }
        return false;
    }
}