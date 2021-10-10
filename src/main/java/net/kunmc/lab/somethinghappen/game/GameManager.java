package net.kunmc.lab.somethinghappen.game;

import net.kunmc.lab.somethinghappen.happening.HappeningManager;

import java.util.Random;

public class GameManager {
    public static Random rand = new Random();
    public static GameMode runningMode = GameMode.NEUTRAL;

    public static void controller(GameMode runningMode) {
        // モードを設定
        GameManager.runningMode = runningMode;

        switch (runningMode) {
            case NEUTRAL:
                break;
            case RUNNING:
                HappeningManager.setNextHappening();
                HappeningManager.switchHappening();
                break;
        }
    }

    public enum GameMode {
        NEUTRAL,
        RUNNING
    }

    public static boolean canEventProcess() {
        if (GameManager.runningMode == GameManager.GameMode.NEUTRAL ||
                HappeningManager.currentHappening == null)
            return false;
        return true;
    }
}