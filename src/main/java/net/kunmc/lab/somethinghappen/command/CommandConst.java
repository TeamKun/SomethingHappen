package net.kunmc.lab.somethinghappen.command;

public class CommandConst {
    // メインコマンド
    public final static String MAIN = "sh";

    public final static String START = "start";
    public final static String STOP = "stop";
    public final static String ADD = "add";
    public final static String REMOVE = "remove";

    // コンフィグ系コマンド
    public final static String SET_CONFIG = "setConfig";
    public final static String RELOAD_CONFIG = "reloadConfig";

    // 設定可能なコマンド
    public final static String CONFIG_HAPPENING_SWITCH_TIME = "happeningSwitchTime";
    public final static String CONFIG_NEXT_HAPPENING_SHOW_TIME = "nextHappeningShowTime";
    public final static String CONFIG_SPAWN_MOB_NUM = "spawnMobNum";
    public final static String CONFIG_FALL_BLOCK_NUM = "fallBlockNum";
    public final static String CONFIG_TELEPORT_RANGE = "teleportRange";
    public final static String CONFIG_CONVERT_BLOCK_RANGE = "convertBlockRange";
    public final static String CONFIG_OFF_HAPPENING = "offHappening";
    public final static String CONFIG_ON_HAPPENING = "onHappening";
    public final static String CONFIG_ADD_WOMAN_PLAYER = "addWomanPlayer";
    public final static String CONFIG_REMOVE_WOMAN_PLAYER = "removeWomanPlayer";
    public final static String CONFIG_ADD_NONBINARY_PLAYER = "addNonbinaryPlayer";
    public final static String CONFIG_REMOVE_NONBINARY_PLAYER = "removeNonbinaryPlayer";

    // 状況確認
    public final static String SHOW_STATUS = "showStatus";
}