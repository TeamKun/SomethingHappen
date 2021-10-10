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
            default:
                // TODO 何もしない出来事を返すようにしておくか検討
                happening = new SpawnMobHappening(name, getHappeningMessage(name));
        }
        return happening;
    }

    public static String getHappeningMessage(String name) {
        String message = "";
        switch (name) {
            case HappeningConst.SPAWN_PASSIVE_MOB:
                message = "パッシブモブ";
                break;
            case HappeningConst.SPAWN_ZOMBIE:
                message = "ゾンビ";
                break;
            case HappeningConst.SPAWN_CLEEPER:
                message = "クリーパー";
                break;
            case HappeningConst.SPAWN_ENDERMAN:
                message = "エンダーマン";
                break;
            case HappeningConst.SPAWN_BAT:
                message = "Bat";
                break;
            case HappeningConst.FALL_ANVIL:
                message = "金床の雨";
                break;
            case HappeningConst.FALL_SAND:
                message = "砂の雨";
                break;
            case HappeningConst.FALL_ARROW:
                message = "矢の雨";
                break;
            case HappeningConst.FALL_METEOR:
                message = "隕石の雨";
                break;
            case HappeningConst.CHANGE_FOOT_BLOCK_RANDOM:
                message = "足元がランダムにチェンジ";
                break;
            case HappeningConst.CHANGE_FOOT_BLOCK_MAGMA:
                message = "足元がマグマブロックにチェンジ";
                break;
            case HappeningConst.CHANGE_FOOT_BLOCK_TNT:
                message = "足元がTNTにチェンジ";
                break;
            case HappeningConst.ADD_PLAYER_INVISIBLE:
                message = "透明化";
                break;
            case HappeningConst.ADD_PLAYER_LEVITATION:
                message = "浮遊";
                break;
            case HappeningConst.ADD_PLAYER_BLINDNESS:
                message = "盲目";
                break;
            case HappeningConst.ADD_PLAYER_RANDOM_POTION:
                message = "ランダムに効果";
                break;
            case HappeningConst.ADD_PLAYER_POISON:
                message = "毒";
                break;
            case HappeningConst.ADD_PLAYER_MOVE_SPEED_UP:
                message = "移動速度アップ";
                break;
            case HappeningConst.ADD_PLAYER_MINING_SPEED_UP:
                message = "採掘速度アップ";
                break;
            case HappeningConst.ADD_PLAYER_JUMP_POWER_UP:
                message = "ジャンプ力アップ";
                break;
            case HappeningConst.UPGRADE_TOOL:
                message = "ツールアップグレード";
                break;
            case HappeningConst.DOWNGRADE_TOOL:
                message = "ツールダウングレード";
                break;
            case HappeningConst.CLEAR_HOTBAR_ITEM:
                message = "ホットバーアイテム消滅";
                break;
            case HappeningConst.TRANS_HOTBAR_ITEM:
                message = "ホットバーに鉄装備";
                break;
            case HappeningConst.TELEPORT_PLAYER:
                message = "どこかにテレポート";
                break;
            case HappeningConst.CHANGE_PLAYER_DIMENSION:
                message = "次元移動";
                break;
            case HappeningConst.DIE_ON_FOOT_BLOCK:
                message = "草、土、ネザーラックの上に立つと死亡";
                break;
            case HappeningConst.BE_ONE_HP:
                message = "体力1";
                break;
            case HappeningConst.SYNC_DEATH_EVERYONE:
                message = "一人死亡で全員死亡";
                break;
            case HappeningConst.SYNC_DEATH_RANDOM:
                message = "一人死亡でもう一人死亡";
                break;
            case HappeningConst.BE_VEAGAN:
                message = "ヴィーガン化";
                break;
            case HappeningConst.BE_CARNIVORE:
                message = "肉食主義化";
                break;
            case HappeningConst.PROHIBIT_WATER:
                message = "水源・水流に入ると死亡";
                break;
            case HappeningConst.PROHIBIT_JUMP:
                message = "ジャンプすると死亡";
                break;
            case HappeningConst.CONVERT_BLOCK_TO_STONE:
                message = "周囲ブロック石化";
                break;
            case HappeningConst.BE_SPONGE_BODY:
                message = "体がスポンジ化";
                break;
            case HappeningConst.PROHIBIT_MAN:
                message = "男は女に近づくと炎上";
                break;
            case HappeningConst.PROHIBIT_WOMAN:
                message = "女は男に近づくと炎上";
                break;
        }
        return message;
    }
}
