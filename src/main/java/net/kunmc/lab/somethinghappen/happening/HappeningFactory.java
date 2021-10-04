package net.kunmc.lab.somethinghappen.happening;

import net.kunmc.lab.somethinghappen.happening.logic.*;

public class HappeningFactory {
    public static Happening createHappening(String name){
        Happening happening = null;
        switch (name){
            case HappeningConst.SPAWN_PASSIVE_MOB:
            case HappeningConst.SPAWN_ZOMBIE:
            case HappeningConst.SPAWN_CLEEPER:
            case HappeningConst.SPAWN_ENDERMAN:
            case HappeningConst.SPAWN_BAT:
                happening = new SpawnMobHappening(name);
                break;
            case HappeningConst.FALL_ANVIL:
            case HappeningConst.FALL_ARROW:
            case HappeningConst.FALL_METEOR:
            case HappeningConst.FALL_SAND:
                happening = new FallEntityHappening(name);
                break;
            case HappeningConst.CHANGE_FOOT_BLOCK_MAGMA:
            case HappeningConst.CHANGE_FOOT_BLOCK_RANDOM:
            case HappeningConst.CHANGE_FOOT_BLOCK_TNT:
                happening = new ChangeFootBlockHappening(name);
                break;
            case HappeningConst.ADD_PLAYER_INVISIBLE:
            case HappeningConst.ADD_PLAYER_BLINDNESS:
            case HappeningConst.ADD_PLAYER_RANDOM_POTION:
            case HappeningConst.ADD_PLAYER_POISON:
            case HappeningConst.ADD_PLAYER_MOVE_SPEED_UP:
            case HappeningConst.ADD_PLAYER_MINING_SPEED_UP:
            case HappeningConst.ADD_PLAYER_JUMP_POWER_UP:
                happening = new AddPlayerEffectHappening(name);
                break;
            case HappeningConst.UPGRADE_TOOL:
            case HappeningConst.DOWNGRADE_TOOL:
                happening = new ChangeToolHappening(name);
                break;
            case HappeningConst.CLEAR_HOTBAR_ITEM:
            case HappeningConst.TRANS_HOTBAR_ITEM:
                happening = new ChangeHotbarHappening(name);
                break;
            default:
                // TODO 何もしない出来事を返すようにしておくか検討
                happening = new SpawnMobHappening(name);
        }
        return happening;
    }

    public static String getHappeningMessage(String name){
        String message = "";
        switch (name){
            case HappeningConst.SPAWN_PASSIVE_MOB:
                message = "パッシブモブが沸く";
                break;
            case HappeningConst.SPAWN_ZOMBIE:
                message = "ゾンビが沸く";
                break;
            case HappeningConst.SPAWN_CLEEPER:
                message = "クリーパーが沸く";
                break;
            case HappeningConst.SPAWN_ENDERMAN:
                message = "エンダーマンが沸く";
                break;
            case HappeningConst.SPAWN_BAT:
                message = "Bat入る";
                break;
        }
        return message;
    }
}
