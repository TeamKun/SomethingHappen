package net.kunmc.lab.somethinghappen.game;

import net.kunmc.lab.somethinghappen.happening.HappeningManager;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

public class GameManager {
    public static Random rand = new Random();
    public static GameMode runningMode = GameMode.NEUTRAL;
    // 事件の対象プレイヤー
    public static Set<UUID> player = new HashSet<>();

    public static void controller(GameMode runningMode) {
        // モードを設定
        GameManager.runningMode = runningMode;

        switch (runningMode) {
            case NEUTRAL:
                break;
            case RUNNING:
                break;
        }
    }

    public enum GameMode {
        NEUTRAL,
        RUNNING
    }

    public static Set<UUID> getPlayers(){
        return player;
    }

    public static boolean canEventProcess(){
        if (GameManager.runningMode == GameManager.GameMode.NEUTRAL ||
                HappeningManager.currentHappening == null)
            return false;
        return true;
    }
}