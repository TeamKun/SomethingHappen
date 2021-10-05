package net.kunmc.lab.somethinghappen.happening.logic;

import net.kunmc.lab.somethinghappen.Config;
import net.kunmc.lab.somethinghappen.game.GameManager;
import net.kunmc.lab.somethinghappen.happening.HappeningConst;
import org.bukkit.Bukkit;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;
import java.util.List;

public class AddPlayerEffectHappening extends Happening {
    public AddPlayerEffectHappening(String name) {
        super(name);
    }

    // TODO: ランダムポーションの時の挙動をなんとかする
    private PotionEffectType currentPotionEffectType;
    public void beginHappening(List<Player> players) {
        PotionEffect potionEffect = null;
        switch (super.getName()) {
            case HappeningConst.ADD_PLAYER_INVISIBLE:
                potionEffect = new PotionEffect(PotionEffectType.INVISIBILITY, Config.happeningSwitchTime*20,10);
                break;
            case HappeningConst.ADD_PLAYER_BLINDNESS:
                potionEffect = new PotionEffect(PotionEffectType.BLINDNESS, Config.happeningSwitchTime*20,1);
                for (int i = 0; i < Bukkit.getWorlds().size(); i++){
                    PotionEffect finalPotionEffect1 = potionEffect;
                    Bukkit.getWorlds().get(i).getEntities().forEach(e -> {
                        if (!(e instanceof Mob))return;

                        ((Mob) e).addPotionEffect(finalPotionEffect1);
                    });
                }
                break;
            case HappeningConst.ADD_PLAYER_RANDOM_POTION:
                Object[] effects = Arrays.stream(PotionEffectType.values()).toArray();
                Object effect = effects[GameManager.rand.nextInt(effects.length)];
                potionEffect = new PotionEffect((PotionEffectType) effect, Config.happeningSwitchTime*20,3);
                break;
            case HappeningConst.ADD_PLAYER_POISON:
                potionEffect = new PotionEffect(PotionEffectType.POISON, Config.happeningSwitchTime*20,10);
                break;
            case HappeningConst.ADD_PLAYER_MOVE_SPEED_UP:
                potionEffect = new PotionEffect(PotionEffectType.SPEED, Config.happeningSwitchTime*20,10);
                break;
            case HappeningConst.ADD_PLAYER_MINING_SPEED_UP:
                potionEffect = new PotionEffect(PotionEffectType.FAST_DIGGING, Config.happeningSwitchTime*20,10);
                break;
            case HappeningConst.ADD_PLAYER_JUMP_POWER_UP:
                potionEffect = new PotionEffect(PotionEffectType.JUMP, Config.happeningSwitchTime*20,10);
                break;
        }
        PotionEffect finalPotionEffect = potionEffect;
        currentPotionEffectType = potionEffect.getType();
        players.forEach(p -> {
            p.addPotionEffect(finalPotionEffect);
        });
    }

    public void endHappening() {
        Bukkit.getOnlinePlayers().forEach(p -> {
            p.removePotionEffect(currentPotionEffectType);
        });
    }
}
