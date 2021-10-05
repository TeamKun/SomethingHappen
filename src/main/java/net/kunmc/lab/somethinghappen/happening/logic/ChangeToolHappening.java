package net.kunmc.lab.somethinghappen.happening.logic;

import net.kunmc.lab.somethinghappen.happening.HappeningConst;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

/**
 * - 対応する事件
 *   - 'upgradeTool'
 *   - 'downgradeTool'
 */
public class ChangeToolHappening extends Happening{
    public ChangeToolHappening(String name) {
        super(name);
    }

    public void beginHappening(List<Player> players) {
        players.forEach(p -> {
            for (int i = 0; i < p.getInventory().getContents().length;i++) {
                ItemStack tool = null;
                if (super.getName().equals(HappeningConst.UPGRADE_TOOL)) {
                    tool = upgradeTool(p.getInventory().getItem(i));
                } else if (super.getName().equals(HappeningConst.DOWNGRADE_TOOL)){
                    tool = downgradeTool(p.getInventory().getItem(i));
                }
                if (tool != null) p.getInventory().setItem(i, tool);
            }
        });
    }

    private ItemStack upgradeTool(ItemStack itemStack) {
        if (itemStack == null) return null;

        ItemStack tool = null;
        if (itemStack.getType() == Material.WOODEN_PICKAXE) {
            tool = new ItemStack(Material.STONE_PICKAXE);
        } else if (itemStack.getType() == Material.STONE_PICKAXE){
            tool = new ItemStack(Material.IRON_PICKAXE);
        } else if (itemStack.getType() == Material.IRON_PICKAXE){
            tool = new ItemStack(Material.DIAMOND_PICKAXE);
        } else if (itemStack.getType() == Material.DIAMOND_PICKAXE) {
            tool = new ItemStack(Material.NETHERITE_PICKAXE);
        } else if (itemStack.getType() == Material.WOODEN_AXE) {
            tool = new ItemStack(Material.STONE_AXE);
        } else if (itemStack.getType() == Material.STONE_AXE){
            tool = new ItemStack(Material.IRON_AXE);
        } else if (itemStack.getType() == Material.IRON_AXE){
            tool = new ItemStack(Material.DIAMOND_AXE);
        } else if (itemStack.getType() == Material.DIAMOND_AXE){
            tool = new ItemStack(Material.NETHERITE_AXE);
        } else if (itemStack.getType() == Material.WOODEN_HOE) {
            tool = new ItemStack(Material.STONE_HOE);
        } else if (itemStack.getType() == Material.STONE_HOE){
            tool = new ItemStack(Material.IRON_HOE);
        } else if (itemStack.getType() == Material.IRON_HOE){
            tool = new ItemStack(Material.DIAMOND_HOE);
        } else if (itemStack.getType() == Material.DIAMOND_HOE) {
            tool = new ItemStack(Material.NETHERITE_HOE);
        } else if (itemStack.getType() == Material.WOODEN_SHOVEL) {
            tool = new ItemStack(Material.STONE_SHOVEL);
        } else if (itemStack.getType() == Material.STONE_SHOVEL){
            tool = new ItemStack(Material.IRON_SHOVEL);
        } else if (itemStack.getType() == Material.IRON_SHOVEL){
            tool = new ItemStack(Material.DIAMOND_SHOVEL);
        } else if (itemStack.getType() == Material.DIAMOND_SHOVEL) {
            tool = new ItemStack(Material.NETHERITE_SHOVEL);
        }
        return tool;
    }

    private ItemStack downgradeTool(ItemStack itemStack) {
        if (itemStack == null) return null;

        ItemStack tool = null;
        if (itemStack.getType() == Material.STONE_PICKAXE){
            tool = new ItemStack(Material.WOODEN_PICKAXE);
        } else if (itemStack.getType() == Material.IRON_PICKAXE){
            tool = new ItemStack(Material.STONE_PICKAXE);
        } else if (itemStack.getType() == Material.DIAMOND_PICKAXE) {
            tool = new ItemStack(Material.IRON_PICKAXE);
        } else if (itemStack.getType() == Material.NETHERITE_PICKAXE) {
            tool = new ItemStack(Material.DIAMOND_PICKAXE);
        } else if (itemStack.getType() == Material.STONE_AXE){
            tool = new ItemStack(Material.WOODEN_AXE);
        } else if (itemStack.getType() == Material.IRON_AXE){
            tool = new ItemStack(Material.STONE_AXE);
        } else if (itemStack.getType() == Material.DIAMOND_AXE) {
            tool = new ItemStack(Material.IRON_AXE);
        } else if (itemStack.getType() == Material.NETHERITE_AXE) {
            tool = new ItemStack(Material.DIAMOND_AXE);
        } else if (itemStack.getType() == Material.STONE_SHOVEL){
            tool = new ItemStack(Material.WOODEN_SHOVEL);
        } else if (itemStack.getType() == Material.IRON_SHOVEL){
            tool = new ItemStack(Material.STONE_SHOVEL);
        } else if (itemStack.getType() == Material.DIAMOND_SHOVEL) {
            tool = new ItemStack(Material.IRON_SHOVEL);
        } else if (itemStack.getType() == Material.NETHERITE_SHOVEL) {
            tool = new ItemStack(Material.DIAMOND_SHOVEL);
        } else if (itemStack.getType() == Material.STONE_HOE){
            tool = new ItemStack(Material.WOODEN_HOE);
        } else if (itemStack.getType() == Material.IRON_HOE){
            tool = new ItemStack(Material.STONE_HOE);
        } else if (itemStack.getType() == Material.DIAMOND_HOE) {
            tool = new ItemStack(Material.IRON_HOE);
        } else if (itemStack.getType() == Material.NETHERITE_HOE) {
            tool = new ItemStack(Material.DIAMOND_HOE);
        }

        return tool;
    }
}
