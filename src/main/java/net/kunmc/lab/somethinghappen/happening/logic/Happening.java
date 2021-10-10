package net.kunmc.lab.somethinghappen.happening.logic;

import org.bukkit.entity.Player;

import java.util.List;

public class Happening {
    private String name;
    private String title;

    public Happening(String name, String title) {
        this.name = name;
        this.title = title;
    }

    // 事件発生直後のみ実行する処理
    public void beginHappening(List<Player> players) {
    }

    // 継続的に処理を行う場合に実行する処理
    public void continueHappening() {
    }

    // 事件終了時に行う処理
    public void endHappening() {
    }

    // ログイン時やリスポーン時に再度発生させたい処理
    public void beginHappeningOnLoginOrRespawn(Player player) {
    }

    // ログアウト時など個別のプレイヤーに対して終了処理を適用したい場合に実行する処理
    public void endPlayerHappening(Player player) {

    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }
}
