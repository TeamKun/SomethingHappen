package net.kunmc.lab.somethinghappen.happening;

import net.kunmc.lab.somethinghappen.happening.logic.*;

public class HappeningFactory {
    public static Happening createHappening(String name) {
        Happening happening = null;
        switch (name) {
            case HappeningConst.SPAWN_PASSIVE_MOB:
            case HappeningConst.SPAWN_ZOMBIE:
            case HappeningConst.SPAWN_CLEEPER:
            case HappeningConst.SPAWN_ENDERMAN:
            case HappeningConst.SPAWN_BAT:
                happening = new SpawnMobHappening(name, getHappeningMessage(name));
                break;
            case HappeningConst.FALL_ANVIL:
            case HappeningConst.FALL_ARROW:
            case HappeningConst.FALL_METEOR:
            case HappeningConst.FALL_SAND:
                happening = new FallEntityHappening(name, getHappeningMessage(name));
                break;
            case HappeningConst.CHANGE_FOOT_BLOCK_MAGMA:
            case HappeningConst.CHANGE_FOOT_BLOCK_RANDOM:
            case HappeningConst.CHANGE_FOOT_BLOCK_TNT:
                happening = new ChangeFootBlockHappening(name, getHappeningMessage(name));
                break;
            case HappeningConst.ADD_PLAYER_INVISIBLE:
            case HappeningConst.ADD_PLAYER_LEVITATION:
            case HappeningConst.ADD_PLAYER_BLINDNESS:
            case HappeningConst.ADD_PLAYER_RANDOM_POTION:
            case HappeningConst.ADD_PLAYER_POISON:
            case HappeningConst.ADD_PLAYER_MOVE_SPEED_UP:
            case HappeningConst.ADD_PLAYER_MINING_SPEED_UP:
            case HappeningConst.ADD_PLAYER_JUMP_POWER_UP:
                happening = new AddPlayerEffectHappening(name, getHappeningMessage(name));
                break;
            case HappeningConst.UPGRADE_TOOL:
            case HappeningConst.DOWNGRADE_TOOL:
                happening = new ChangeToolHappening(name, getHappeningMessage(name));
                break;
            case HappeningConst.CLEAR_HOTBAR_ITEM:
            case HappeningConst.TRANS_HOTBAR_ITEM:
                happening = new ChangeHotbarHappening(name, getHappeningMessage(name));
                break;
            case HappeningConst.TELEPORT_PLAYER:
            case HappeningConst.CHANGE_PLAYER_DIMENSION:
                happening = new TeleportPlayerHappening(name, getHappeningMessage(name));
                break;
            case HappeningConst.DIE_ON_FOOT_BLOCK:
                happening = new DieOnFootBlockHappening(name, getHappeningMessage(name));
                break;
            case HappeningConst.BE_ONE_HP:
                happening = new ChangePlayerHealthHappening(name, getHappeningMessage(name));
                break;
            case HappeningConst.SYNC_DEATH_RANDOM:
            case HappeningConst.SYNC_DEATH_EVERYONE:
                happening = new SyncDeathHappening(name, getHappeningMessage(name));
                break;
            case HappeningConst.BE_VEAGAN:
            case HappeningConst.BE_CARNIVORE:
            case HappeningConst.PROHIBIT_JUMP:
                happening = new EventHappening(name, getHappeningMessage(name));
                break;
            case HappeningConst.PROHIBIT_WATER:
                happening = new ProhibitWaterHappening(name, getHappeningMessage(name));
                break;
            case HappeningConst.CONVERT_BLOCK_TO_STONE:
            case HappeningConst.BE_SPONGE_BODY:
                happening = new ConvertBlockHappening(name, getHappeningMessage(name));
                break;
            case HappeningConst.PROHIBIT_MAN:
            case HappeningConst.PROHIBIT_WOMAN:
                happening = new ProhibitPlayer(name, getHappeningMessage(name));
                break;
            case HappeningConst.CHANGE_ADVENTURE_MODE:
                happening = new ChangeGameModeHappening(name, getHappeningMessage(name));
                break;
            default:
                // ????????????????????????????????????????????????
                happening = new SpawnMobHappening(HappeningConst.SPAWN_PASSIVE_MOB, getHappeningMessage(HappeningConst.SPAWN_PASSIVE_MOB));
        }
        return happening;
    }

    public static String getHappeningMessage(String name) {
        String message = "";
        switch (name) {
            case HappeningConst.SPAWN_PASSIVE_MOB:
                message = "??????????????????";
                break;
            case HappeningConst.SPAWN_ZOMBIE:
                message = "?????????";
                break;
            case HappeningConst.SPAWN_CLEEPER:
                message = "???????????????";
                break;
            case HappeningConst.SPAWN_ENDERMAN:
                message = "??????????????????";
                break;
            case HappeningConst.SPAWN_BAT:
                message = "Bat";
                break;
            case HappeningConst.FALL_ANVIL:
                message = "????????????";
                break;
            case HappeningConst.FALL_SAND:
                message = "?????????";
                break;
            case HappeningConst.FALL_ARROW:
                message = "?????????";
                break;
            case HappeningConst.FALL_METEOR:
                message = "????????????";
                break;
            case HappeningConst.CHANGE_FOOT_BLOCK_RANDOM:
                message = "????????????????????????????????????";
                break;
            case HappeningConst.CHANGE_FOOT_BLOCK_MAGMA:
                message = "?????????????????????????????????????????????";
                break;
            case HappeningConst.CHANGE_FOOT_BLOCK_TNT:
                message = "?????????TNT???????????????";
                break;
            case HappeningConst.ADD_PLAYER_INVISIBLE:
                message = "?????????";
                break;
            case HappeningConst.ADD_PLAYER_LEVITATION:
                message = "??????";
                break;
            case HappeningConst.ADD_PLAYER_BLINDNESS:
                message = "??????";
                break;
            case HappeningConst.ADD_PLAYER_RANDOM_POTION:
                message = "????????????????????????????????????";
                break;
            case HappeningConst.ADD_PLAYER_POISON:
                message = "???";
                break;
            case HappeningConst.ADD_PLAYER_MOVE_SPEED_UP:
                message = "?????????????????????";
                break;
            case HappeningConst.ADD_PLAYER_MINING_SPEED_UP:
                message = "?????????????????????";
                break;
            case HappeningConst.ADD_PLAYER_JUMP_POWER_UP:
                message = "????????????????????????";
                break;
            case HappeningConst.UPGRADE_TOOL:
                message = "??????????????????????????????";
                break;
            case HappeningConst.DOWNGRADE_TOOL:
                message = "??????????????????????????????";
                break;
            case HappeningConst.CLEAR_HOTBAR_ITEM:
                message = "?????????????????????????????????";
                break;
            case HappeningConst.TRANS_HOTBAR_ITEM:
                message = "???????????????????????????";
                break;
            case HappeningConst.TELEPORT_PLAYER:
                message = "???????????????????????????";
                break;
            case HappeningConst.CHANGE_PLAYER_DIMENSION:
                message = "????????????";
                break;
            case HappeningConst.DIE_ON_FOOT_BLOCK:
                message = "??????????????????????????????????????????????????????";
                break;
            case HappeningConst.BE_ONE_HP:
                message = "??????1";
                break;
            case HappeningConst.SYNC_DEATH_EVERYONE:
                message = "???????????????????????????";
                break;
            case HappeningConst.SYNC_DEATH_RANDOM:
                message = "?????????????????????????????????";
                break;
            case HappeningConst.BE_VEAGAN:
                message = "??????????????????";
                break;
            case HappeningConst.BE_CARNIVORE:
                message = "???????????????";
                break;
            case HappeningConst.PROHIBIT_WATER:
                message = "?????????????????????????????????";
                break;
            case HappeningConst.PROHIBIT_JUMP:
                message = "???????????????????????????";
                break;
            case HappeningConst.CONVERT_BLOCK_TO_STONE:
                message = "????????????????????????";
                break;
            case HappeningConst.BE_SPONGE_BODY:
                message = "?????????????????????";
                break;
            case HappeningConst.PROHIBIT_MAN:
                message = "??????????????????????????????";
                break;
            case HappeningConst.PROHIBIT_WOMAN:
                message = "??????????????????????????????";
                break;
            case HappeningConst.CHANGE_ADVENTURE_MODE:
                message = "??????????????????????????????";
                break;
        }
        return message;
    }
}
