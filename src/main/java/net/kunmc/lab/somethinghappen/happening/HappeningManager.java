package net.kunmc.lab.somethinghappen.happening;

import net.kunmc.lab.somethinghappen.Config;
import net.kunmc.lab.somethinghappen.game.GameManager;
import net.kunmc.lab.somethinghappen.happening.logic.Happening;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class HappeningManager {
    public static int timer = 0;
    public static Happening currentHappening;
    public static Happening nextHappening;

    private static boolean setNextHappening = false;

    public static void switchHappening(){
        System.out.println("switchHappening");
        timer = 0;
        // 今持っている事件の処理を終了
        if (currentHappening != null) {
            currentHappening.endHappening();
        }

        // 次の事件を開始
        currentHappening = nextHappening;
        getHappeningTargetPlayers().forEach(p -> {
            p.sendTitle(currentHappening.getTitle(),"",1,20,1);
        });
        currentHappening.beginHappening(getHappeningTargetPlayers());
        currentHappening.continueHappening();
        setNextHappening = false;
    }

    public static boolean shouldSwitch() {
        boolean ret = false;
        if (timer > Config.happeningSwitchTime * 20) {
            ret = true;
        }
        return ret;
    }

    public static List<Player> getHappeningTargetPlayers(){
        return Bukkit.getOnlinePlayers().stream().collect(Collectors.toList());
        //return Bukkit.getOnlinePlayers().stream().filter(e -> GameManager.player.contains(e.getUniqueId())).collect(Collectors.toList());
    }

    public static void setNextHappening () {
        List<String> happenings = new ArrayList<>();
        for(Map.Entry<String, Boolean> happening: Config.happenings.entrySet()) {
            if (happening.getValue()) {
                happenings.add(happening.getKey());
            }
        }
        Object[] happeningNames = happenings.toArray();
        Object happeningName = happeningNames[GameManager.rand.nextInt(happeningNames.length)];
        nextHappening = HappeningFactory.createHappening((String) happeningName);
        setNextHappening = true;
    }

    public static void showNextHappening (){
        int time = getNextHappeningTime();

        TextComponent component = new TextComponent();
        component.setText(nextHappening.getTitle() + "発生まで " + time);

        Bukkit.getOnlinePlayers().stream().forEach(p -> {
            p.sendMessage(ChatMessageType.ACTION_BAR, component);
        });
    }

    public static boolean updatedNextHappening(){
        return setNextHappening;
    }

    public static boolean shouldShowNextHappening(){
        int time = getNextHappeningTime();
        if (Config.nextHappeningShowTime <= time) return true;
        return false;
    }

    private static int getNextHappeningTime(){
        return (Config.happeningSwitchTime * 20 - timer)/20;
    }

    public static void incrementTimer (){
        timer++;
    }
}
