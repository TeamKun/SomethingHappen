package net.kunmc.lab.somethinghappen.happening.logic;

import net.kunmc.lab.somethinghappen.happening.HappeningConst;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import java.util.List;

/**
 * - 対応する事件
 *   - 'clearHotBarItem'
 *   - 'transHotBarItem'
 */
public class ChangeHotbarHappening extends Happening {
    public ChangeHotbarHappening(String name) {
        super(name);
    }

    public void beginHappening(List<Player> players) {
        players.forEach(p -> {
            if (super.getName().equals(HappeningConst.CLEAR_HOTBAR_ITEM)) {
                ItemStack air = new ItemStack(Material.AIR, 1);
                for (int i = 0; i < 9; i++)
                    p.getInventory().setItem(i, air);
            } else if (super.getName().equals(HappeningConst.TRANS_HOTBAR_ITEM)) {
                Material[] materials = {Material.IRON_HELMET, Material.IRON_CHESTPLATE,
                        Material.IRON_LEGGINGS, Material.IRON_BOOTS, Material.IRON_SWORD,
                        Material.IRON_PICKAXE, Material.IRON_AXE, Material.IRON_SHOVEL,
                        Material.IRON_HOE};
                for (int i = 0; i < materials.length; i++) {
                    p.getInventory().setItem(i, new ItemStack(materials[i]));
                }
            }
        });
    }
}
