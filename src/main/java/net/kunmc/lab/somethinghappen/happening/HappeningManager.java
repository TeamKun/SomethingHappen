package net.kunmc.lab.somethinghappen.happening;

import net.kunmc.lab.somethinghappen.Config;
import net.kunmc.lab.somethinghappen.game.GameManager;
import net.kunmc.lab.somethinghappen.happening.logic.Happening;

import java.sql.Time;

public class HappeningManager {
    public static int timer = 0;
    public static Happening currentHappening;

    public static void switchHappening(){
        System.out.println("switchHappening");
        timer = 0;
        // 今持っている事件の処理を終了
        if (currentHappening != null) {
            currentHappening.endHappening();
        }

        // ランダムに事件を取得
        Object[] happeningNames = Config.happenings.keySet().toArray();
        Object happeningName = happeningNames[GameManager.rand.nextInt(happeningNames.length)];
        currentHappening = HappeningFactory.createHappening((String) happeningName);
        System.out.println(currentHappening.getName());

        // 次の事件を開始
        currentHappening.beginHappening();
    }

    public static boolean shouldSwitch() {
        boolean ret = false;
        if (timer > Config.happeningSwitchTime * 20) {
            ret = true;
        }
        return ret;
    }

    public static String getHappeningName (){
        return currentHappening.getName();
    }

    public static void endHappening (){
        currentHappening.endHappening();
    }

    public static void incrementTimer (){
        timer++;
    }
}
