package net.kunmc.lab.somethinghappen.event;

import net.kunmc.lab.somethinghappen.Config;
import net.kunmc.lab.somethinghappen.game.GameManager;
import net.kunmc.lab.somethinghappen.happening.HappeningConst;
import net.kunmc.lab.somethinghappen.happening.HappeningManager;
import org.bukkit.entity.Mob;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class EntityEventHandler implements Listener {
    @EventHandler
    public void onEntitySpawn(EntitySpawnEvent event) {
        if (!GameManager.canEventProcess() ||
                !HappeningManager.currentHappening.getName().equals(HappeningConst.ADD_PLAYER_INVISIBLE) ||
                !(event.getEntity() instanceof Mob)) return;
        PotionEffect potionEffect = new PotionEffect(PotionEffectType.INVISIBILITY, Config.happeningSwitchTime * 20, 10);
        ((Mob) event.getEntity()).addPotionEffect(potionEffect);
    }
}
