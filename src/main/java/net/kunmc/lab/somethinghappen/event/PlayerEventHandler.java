package net.kunmc.lab.somethinghappen.event;

import com.destroystokyo.paper.event.player.PlayerJumpEvent;
import net.kunmc.lab.somethinghappen.game.GameManager;
import net.kunmc.lab.somethinghappen.happening.HappeningConst;
import net.kunmc.lab.somethinghappen.happening.HappeningManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PlayerEventHandler implements Listener {
    /**
     * - 'syncDeathEveryone'
     * - 'syncDeathRandom'
     *
     * @param event
     */
    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        if (!GameManager.canEventProcess()) return;

        if (HappeningManager.currentHappening.getName().equals(HappeningConst.SYNC_DEATH_RANDOM)) {
            Object[] players = HappeningManager.getHappeningTargetPlayers().stream()
                    .filter(e -> !e.isDead()).collect(Collectors.toList()).toArray();
            Object player = players[GameManager.rand.nextInt(players.length)];
            ((Player) player).damage(1000, event.getEntity());
        } else if (HappeningManager.currentHappening.getName().equals(HappeningConst.SYNC_DEATH_EVERYONE)) {
            HappeningManager.getHappeningTargetPlayers().stream()
                    .filter(e -> !e.isDead()).forEach(p -> {
                p.damage(1000, event.getEntity());
            });
        }
    }

    @EventHandler
    public void onItemConsume(PlayerItemConsumeEvent event) {
        if (!GameManager.canEventProcess()) return;
        ItemStack item = event.getItem();

        if (HappeningManager.currentHappening.getName().equals(HappeningConst.BE_VEAGAN) && isLifeFood(item)) {
            event.setCancelled(true);

            Player p = event.getPlayer();
            item.setAmount(item.getAmount() - 1);
            p.getInventory().setItemInMainHand(item);
            p.damage(4);
            p.setFoodLevel(Math.max(0, p.getFoodLevel() - 4));
        } else if (HappeningManager.currentHappening.getName().equals(HappeningConst.BE_CARNIVORE) && !isLifeFood(item)) {
            event.setCancelled(true);

            Player p = event.getPlayer();
            item.setAmount(item.getAmount() - 1);
            p.getInventory().setItemInMainHand(item);
            p.damage(4);
            p.setFoodLevel(Math.max(0, p.getFoodLevel() - 4));
        }
    }

    @EventHandler
    public void onJump(PlayerJumpEvent event) {
        if (!GameManager.canEventProcess()) return;
        Player p = event.getPlayer();
        if (p.isInWater() || !HappeningManager.currentHappening.getName().equals(HappeningConst.PROHIBIT_JUMP)) {
            return;
        }
        p.damage(1000);
    }

    private boolean isLifeFood(ItemStack item) {
        Material type = item.getType();
        // 肉、魚、卵
        if (type == Material.COOKED_BEEF ||
                type == Material.BEEF ||
                type == Material.COOKED_CHICKEN ||
                type == Material.CHICKEN ||
                type == Material.COOKED_MUTTON ||
                type == Material.MUTTON ||
                type == Material.COOKED_PORKCHOP ||
                type == Material.PORKCHOP ||
                type == Material.COOKED_RABBIT ||
                type == Material.RABBIT ||
                type == Material.COOKED_COD ||
                type == Material.COD ||
                type == Material.COOKED_SALMON ||
                type == Material.SALMON ||
                type == Material.TROPICAL_FISH ||
                type == Material.PUFFERFISH ||
                type == Material.CAKE ||
                type == Material.PUMPKIN_PIE ||
                type == Material.RABBIT_STEW ||
                type == Material.ROTTEN_FLESH
        ) {
            return true;
        }

        return false;
    }

    @EventHandler
    public void onPlayerLogin(PlayerJoinEvent event) {
        if (!GameManager.canEventProcess()) return;
        HappeningManager.currentHappening.beginHappeningOnLoginOrRespawn(event.getPlayer());
    }

    @EventHandler
    public void onPlayerLogout(PlayerQuitEvent event) {
        if (!GameManager.canEventProcess()) return;
        HappeningManager.currentHappening.endPlayerHappening(event.getPlayer());
    }

    @EventHandler public void onPlayerRespawn(PlayerRespawnEvent event){
        if (!GameManager.canEventProcess()) return;
        HappeningManager.currentHappening.beginHappeningOnLoginOrRespawn(event.getPlayer());
    }
}
