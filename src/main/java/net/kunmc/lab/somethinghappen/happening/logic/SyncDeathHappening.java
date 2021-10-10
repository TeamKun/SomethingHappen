package net.kunmc.lab.somethinghappen.happening.logic;

import java.util.ArrayList;
import java.util.List;

public class SyncDeathHappening extends Happening {
    public static List<String> players = new ArrayList<>();

    public SyncDeathHappening(String name, String title) {
        super(name, title);
    }
}
