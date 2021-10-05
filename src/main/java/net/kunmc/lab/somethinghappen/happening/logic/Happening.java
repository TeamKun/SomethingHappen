package net.kunmc.lab.somethinghappen.happening.logic;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Happening {
    private String name;
    public Happening(String name){
        this.name = name;
    }

    // 事件発生直後のみ実行する処理
    public void beginHappening (List<Player> players){
    }

    // 継続的に処理を行う場合に実行する処理
    public void continueHappening (){
    }

    public void endHappening (){

    }

    public String getName (){
        return name;
    }
}
