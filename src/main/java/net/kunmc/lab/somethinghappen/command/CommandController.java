package net.kunmc.lab.somethinghappen.command;

import net.kunmc.lab.somethinghappen.Config;
import net.kunmc.lab.somethinghappen.game.GameManager;
import net.kunmc.lab.somethinghappen.happening.HappeningManager;
import net.kunmc.lab.somethinghappen.util.DecolationConst;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Entity;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CommandController implements CommandExecutor, TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> completions = new ArrayList<>();

        if (args.length == 1) {
            completions.addAll(Stream.of(
                    CommandConst.START,
                    CommandConst.STOP,
                    CommandConst.RELOAD_CONFIG,
                    CommandConst.SET_CONFIG,
                    CommandConst.SHOW_STATUS)
                    .filter(e -> e.startsWith(args[0])).collect(Collectors.toList()));
        } else if (args.length == 3 && args[0].equals(CommandConst.SET_CONFIG) &&
                ((args[1].equals(CommandConst.CONFIG_HAPPENING_SWITCH_TIME) ||
                        args[1].equals(CommandConst.CONFIG_NEXT_HAPPENING_SHOW_TIME) ||
                        args[1].equals(CommandConst.CONFIG_FALL_BLOCK_NUM) ||
                        args[1].equals(CommandConst.CONFIG_CONVERT_BLOCK_RANGE) ||
                        args[1].equals(CommandConst.CONFIG_SPAWN_MOB_NUM) ||
                        args[1].equals(CommandConst.CONFIG_TELEPORT_RANGE)))) {
            completions.add("<数字>");
        } else if (args.length == 2 && args[0].equals(CommandConst.SET_CONFIG)){
            completions.addAll(Stream.of(
                    CommandConst.CONFIG_HAPPENING_SWITCH_TIME,
                    CommandConst.CONFIG_NEXT_HAPPENING_SHOW_TIME,
                    CommandConst.CONFIG_FALL_BLOCK_NUM,
                    CommandConst.CONFIG_CONVERT_BLOCK_RANGE,
                    CommandConst.CONFIG_SPAWN_MOB_NUM,
                    CommandConst.CONFIG_TELEPORT_RANGE,
                    CommandConst.CONFIG_ADD_WOMAN_PLAYER,
                    CommandConst.CONFIG_ADD_NONBINARY_PLAYER,
                    CommandConst.CONFIG_REMOVE_WOMAN_PLAYER,
                    CommandConst.CONFIG_REMOVE_NONBINARY_PLAYER,
                    CommandConst.CONFIG_ON_HAPPENING,
                    CommandConst.CONFIG_OFF_HAPPENING)
                    .filter(e -> e.startsWith(args[1])).collect(Collectors.toList()));
        } else if (args.length == 3 && args[1].equals(CommandConst.CONFIG_ON_HAPPENING)) {
            for(Map.Entry<String, Boolean> happening: Config.happenings.entrySet()) {
                if (!happening.getValue()) {
                    completions.add(happening.getKey());
                }
            }
        } else if (args.length == 3 && args[1].equals(CommandConst.CONFIG_OFF_HAPPENING)) {
            for(Map.Entry<String, Boolean> happening: Config.happenings.entrySet()) {
                if (happening.getValue()) {
                    completions.add(happening.getKey());
                }
            }
        } else if (args.length == 3 && args[1].equals(CommandConst.CONFIG_ADD_WOMAN_PLAYER)){
            completions.addAll(Bukkit.getOnlinePlayers().stream().map(HumanEntity::getName)
                    .filter(e ->  !Config.womanPlayer.contains(e) && !Config.nonbinaryPlayer.contains(e))
                    .filter(e -> e.startsWith(args[2])).collect(Collectors.toList()));
        } else if (args.length == 3 && args[1].equals(CommandConst.CONFIG_ADD_NONBINARY_PLAYER)){
            completions.addAll(Bukkit.getOnlinePlayers().stream().map(HumanEntity::getName)
                    .filter(e ->  !Config.womanPlayer.contains(e) && !Config.nonbinaryPlayer.contains(e))
                    .filter(e -> e.startsWith(args[2])).collect(Collectors.toList()));
        } else if (args.length == 3 && args[1].equals(CommandConst.CONFIG_REMOVE_WOMAN_PLAYER)){
        completions.addAll(Bukkit.getOnlinePlayers().stream().map(HumanEntity::getName)
                .filter(e -> Config.womanPlayer.contains(e))
                .filter(e -> e.startsWith(args[2])).collect(Collectors.toList()));
        } else if (args.length == 3 && args[1].equals(CommandConst.CONFIG_REMOVE_NONBINARY_PLAYER)) {
            completions.addAll(Bukkit.getOnlinePlayers().stream().map(HumanEntity::getName)
                    .filter(e -> Config.nonbinaryPlayer.contains(e))
                    .filter(e -> e.startsWith(args[2])).collect(Collectors.toList()));
        }

        return completions;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            sendUsage(sender);
            return true;
        }
        if (!(sender instanceof Player)) {
            sender.sendMessage("このコマンドはゲーム内からのみ実行できます");
            return true;
        }

        String commandName = args[0];
        switch (commandName) {
            case CommandConst.START:
                if (GameManager.runningMode == GameManager.GameMode.RUNNING) {
                    sender.sendMessage(DecolationConst.RED + "すでに開始しています");
                    return true;
                }
                if (!checkArgsNum(sender,args.length, 1)) return true;
                GameManager.controller(GameManager.GameMode.RUNNING);
                sender.sendMessage(DecolationConst.GREEN + "開始します");
                break;
            case CommandConst.STOP:
                if (GameManager.runningMode == GameManager.GameMode.NEUTRAL) {
                    sender.sendMessage(DecolationConst.RED + "開始されていません");
                    return true;
                }
                if (args.length != 1) {
                    sender.sendMessage(DecolationConst.RED + "引数が不要なコマンドです");
                    return true;
                }
                GameManager.controller(GameManager.GameMode.NEUTRAL);
                sender.sendMessage(DecolationConst.GREEN + "ブロック透過化終了、可視化します");
                break;
            case CommandConst.RELOAD_CONFIG:
                if (args.length != 1) {
                    sender.sendMessage(DecolationConst.RED + "引数が不要なコマンドです");
                    return true;
                }
                Config.loadConfig(true);
                break;
            case CommandConst.SET_CONFIG:
                switch (args[1]) {
                    case CommandConst.CONFIG_HAPPENING_SWITCH_TIME:
                        if (!checkArgsNum(sender, args.length, 3)) return true;
                        int ret = validateNum(sender, args[2]);
                        if (ret == -1) return true;

                        Config.happeningSwitchTime = ret;
                        sender.sendMessage(DecolationConst.GREEN + CommandConst.CONFIG_HAPPENING_SWITCH_TIME + "の値を" + Config.happeningSwitchTime + "に変更しました");
                        if (Config.nextHappeningShowTime > Config.happeningSwitchTime) {
                            Config.nextHappeningShowTime = Config.happeningSwitchTime;
                            sender.sendMessage(DecolationConst.AQUA + CommandConst.CONFIG_NEXT_HAPPENING_SHOW_TIME + "の値を" + Config.nextHappeningShowTime + "に変更しました");
                        }
                        break;
                    case CommandConst.CONFIG_NEXT_HAPPENING_SHOW_TIME:
                        if (!checkArgsNum(sender, args.length, 3)) return true;
                        ret = validateNum(sender, args[2]);
                        if (ret == -1) return true;

                        Config.happeningSwitchTime = ret;
                        sender.sendMessage(DecolationConst.GREEN + CommandConst.CONFIG_NEXT_HAPPENING_SHOW_TIME + "の値を" + Config.nextHappeningShowTime + "に変更しました");
                        if (Config.nextHappeningShowTime > Config.happeningSwitchTime) {
                            Config.happeningSwitchTime = Config.nextHappeningShowTime;
                            sender.sendMessage(DecolationConst.AQUA + CommandConst.CONFIG_HAPPENING_SWITCH_TIME + "の値を" + Config.happeningSwitchTime + "に変更しました");
                        }
                        break;
                    case CommandConst.CONFIG_CONVERT_BLOCK_RANGE:
                        if (!checkArgsNum(sender, args.length, 3)) return true;
                        ret = validateNum(sender, args[2]);
                        if (ret == -1) return true;

                        Config.convertBlockRange = ret;
                        sender.sendMessage(DecolationConst.GREEN + CommandConst.CONFIG_CONVERT_BLOCK_RANGE + "の値を" + Config.convertBlockRange + "に変更しました");
                        break;
                    case CommandConst.CONFIG_FALL_BLOCK_NUM:
                        if (!checkArgsNum(sender, args.length, 3)) return true;
                        ret = validateNum(sender, args[2]);
                        if (ret == -1) return true;

                        Config.fallBlockNum = ret;
                        sender.sendMessage(DecolationConst.GREEN + CommandConst.CONFIG_FALL_BLOCK_NUM + "の値を" + Config.fallBlockNum + "に変更しました");
                        break;
                    case CommandConst.CONFIG_TELEPORT_RANGE:
                        if (!checkArgsNum(sender, args.length, 3)) return true;
                        ret = validateNum(sender, args[2]);
                        if (ret == -1) return true;

                        Config.teleportRange = ret;
                        sender.sendMessage(DecolationConst.GREEN + CommandConst.CONFIG_TELEPORT_RANGE + "の値を" + Config.teleportRange + "に変更しました");
                        break;
                    case CommandConst.CONFIG_SPAWN_MOB_NUM:
                        if (!checkArgsNum(sender, args.length, 3)) return true;
                        ret = validateNum(sender, args[2]);
                        if (ret == -1) return true;

                        Config.spawnMobNum = ret;
                        sender.sendMessage(DecolationConst.GREEN + CommandConst.CONFIG_SPAWN_MOB_NUM + "の値を" + Config.spawnMobNum + "に変更しました");
                        break;
                    case CommandConst.CONFIG_ON_HAPPENING:
                        if (!checkArgsNum(sender, args.length, 3)) return true;
                        if (!Config.happenings.containsKey(args[2]))
                            sender.sendMessage(DecolationConst.RED + "存在しないHappeningです");
                        if (Config.happenings.get(args[2])) sender.sendMessage(DecolationConst.AQUA + "すでにONになっています");
                        Config.happenings.put(args[2], true);
                        break;
                    case CommandConst.CONFIG_OFF_HAPPENING:
                        if (!checkArgsNum(sender, args.length, 3)) return true;
                        if (!Config.happenings.containsKey(args[2]))
                            sender.sendMessage(DecolationConst.RED + "存在しないHappeningです");
                        if (!Config.happenings.get(args[2])) sender.sendMessage(DecolationConst.AQUA + "すでにOFFになっています");
                        Config.happenings.put(args[2], false);
                        break;
                    case CommandConst.CONFIG_ADD_WOMAN_PLAYER:
                        List<Entity> players;
                        if (!checkArgsNum(sender, args.length, 3)) return true;
                        try {
                            players = Bukkit.selectEntities(sender, args[2]);
                        } catch (Exception e) {
                            sender.sendMessage(DecolationConst.RED + "存在しないプレイヤー名です");
                            return true;
                        }
                        if (players.isEmpty() || args[2].equals("@e")) {
                            sender.sendMessage(DecolationConst.RED + "存在しないまたはサーバに接続していないプレイヤー名です");
                            return true;
                        }
                        for (Entity p : players) {
                            if (Config.nonbinaryPlayer.contains(p.getName())) {
                                sender.sendMessage(DecolationConst.AQUA + p.getName() + "はnonBinaryPlayerに存在しています");
                            } else if (Config.womanPlayer.contains(p.getName())) {
                                sender.sendMessage(DecolationConst.AQUA + p.getName() + "は追加済みです");
                            } else {
                                Config.womanPlayer.add(p.getName());
                                sender.sendMessage(DecolationConst.GREEN + p.getName() + "を追加しました");
                            }
                        }
                        break;
                    case CommandConst.CONFIG_ADD_NONBINARY_PLAYER:
                        if (!checkArgsNum(sender, args.length, 3)) return true;
                        try {
                            players = Bukkit.selectEntities(sender, args[2]);
                        } catch (Exception e) {
                            sender.sendMessage(DecolationConst.RED + "存在しないプレイヤー名です");
                            return true;
                        }
                        if (players.isEmpty() || args[2].equals("@e")) {
                            sender.sendMessage(DecolationConst.RED + "存在しないまたはサーバに接続していないプレイヤー名です");
                            return true;
                        }
                        for (Entity p : players) {
                            if (Config.womanPlayer.contains(p.getName())) {
                                sender.sendMessage(DecolationConst.AQUA + p.getName() + "はwomanPlayerに存在しています");
                            } else if (Config.nonbinaryPlayer.contains(p.getName())) {
                                sender.sendMessage(DecolationConst.AQUA + p.getName() + "は追加済みです");
                            } else {
                                Config.nonbinaryPlayer.add(p.getName());
                                sender.sendMessage(DecolationConst.GREEN + p.getName() + "を追加しました");
                            }
                        }
                        break;
                    case CommandConst.CONFIG_REMOVE_WOMAN_PLAYER:
                        if (!checkArgsNum(sender,args.length, 3)) return true;
                        try {
                            players = Bukkit.selectEntities(sender, args[2]);
                        } catch (Exception e) {
                            sender.sendMessage(DecolationConst.RED + "存在しないプレイヤー名です");
                            return true;
                        }
                        if (players.isEmpty() || args[2].equals("@e")) {
                            sender.sendMessage(DecolationConst.RED + "存在しないまたはサーバに接続していないプレイヤー名です");
                            return true;
                        }
                        for (Entity p : players) {
                            if (!Config.womanPlayer.contains(p.getUniqueId())) {
                                sender.sendMessage(DecolationConst.AQUA + p.getName() + "は追加されていません");
                            } else {
                                Config.womanPlayer.remove(p.getUniqueId());
                                sender.sendMessage(DecolationConst.GREEN + p.getName() + "を削除しました");
                            }
                        }
                        break;
                    case CommandConst.CONFIG_REMOVE_NONBINARY_PLAYER:
                        if (!checkArgsNum(sender,args.length, 3)) return true;
                        try {
                            players = Bukkit.selectEntities(sender, args[2]);
                        } catch (Exception e) {
                            sender.sendMessage(DecolationConst.RED + "存在しないプレイヤー名です");
                            return true;
                        }
                        if (players.isEmpty() || args[2].equals("@e")) {
                            sender.sendMessage(DecolationConst.RED + "存在しないまたはサーバに接続していないプレイヤー名です");
                            return true;
                        }
                        for (Entity p : players) {
                            if (!Config.nonbinaryPlayer.contains(p.getUniqueId())) {
                                sender.sendMessage(DecolationConst.AQUA + p.getName() + "は追加されていません");
                            } else {
                                Config.nonbinaryPlayer.remove(p.getUniqueId());
                                sender.sendMessage(DecolationConst.GREEN + p.getName() + "を削除しました");
                            }
                        }
                        break;
                }
               break;
            case CommandConst.SHOW_STATUS:
                if (args.length != 1) {
                    sender.sendMessage(DecolationConst.RED + "引数が不要なコマンドです");
                    return true;
                }
                sender.sendMessage(DecolationConst.GREEN + "設定値一覧");
                List<String> happenings = new ArrayList<>();
                for (Map.Entry<String, Boolean> happening: Config.happenings.entrySet()) {
                    if (happening.getValue()) happenings.add(happening.getKey());
                }
                String prefix = "  ";
                sender.sendMessage(String.format("%s%s: %s", prefix, CommandConst.CONFIG_HAPPENING_SWITCH_TIME, Config.happeningSwitchTime));
                sender.sendMessage(String.format("%s%s: %s", prefix, CommandConst.CONFIG_NEXT_HAPPENING_SHOW_TIME, Config.nextHappeningShowTime));
                sender.sendMessage(String.format("%shappenings: ", prefix, Config.happeningSwitchTime) + happenings);
                sender.sendMessage(String.format("%s%s: %s", prefix, CommandConst.CONFIG_CONVERT_BLOCK_RANGE, Config.convertBlockRange));
                sender.sendMessage(String.format("%s%s: %s", prefix, CommandConst.CONFIG_FALL_BLOCK_NUM, Config.fallBlockNum));
                sender.sendMessage(String.format("%s%s: %s", prefix, CommandConst.CONFIG_TELEPORT_RANGE, Config.teleportRange));
                sender.sendMessage(String.format("%s%s: %s", prefix, CommandConst.CONFIG_SPAWN_MOB_NUM, Config.spawnMobNum));
                sender.sendMessage(String.format("%swomanPlayer: %s", prefix, Config.womanPlayer));
                sender.sendMessage(String.format("%snonbinaryPlayer: %s", prefix, Config.nonbinaryPlayer));
                if (GameManager.canEventProcess()) {
                    sender.sendMessage(String.format("%s現在のハプニング: ", prefix) + HappeningManager.currentHappening.getTitle());
                }
                break;
            default:
                sender.sendMessage(DecolationConst.RED + "存在しないコマンドです");
                sendUsage(sender);
        }
        return true;
    }

    private void sendUsage(CommandSender sender) {
        String usagePrefix = String.format("  /%s ", CommandConst.MAIN);
        String descPrefix = "  ";
        sender.sendMessage(DecolationConst.GREEN + "Usage:");
        sender.sendMessage(String.format("%s%s"
                , usagePrefix, CommandConst.START));
        sender.sendMessage(String.format("%s開始", descPrefix));
        sender.sendMessage(String.format("%s%s"
                , usagePrefix, CommandConst.STOP));
        sender.sendMessage(String.format("%s終了", descPrefix));
        sender.sendMessage(String.format("%s%s %s <number>"
                , usagePrefix, CommandConst.SET_CONFIG, CommandConst.CONFIG_HAPPENING_SWITCH_TIME));
        sender.sendMessage(String.format("%sHappeningの切り替え間隔(秒)", descPrefix));
        sender.sendMessage(String.format("%s%s %s <number>"
                , usagePrefix, CommandConst.SET_CONFIG, CommandConst.CONFIG_NEXT_HAPPENING_SHOW_TIME));
        sender.sendMessage(String.format("%sHappening発生前の事前表示をする時間(秒)", descPrefix));
        sender.sendMessage(String.format("%s%s %s <number>"
                , usagePrefix, CommandConst.SET_CONFIG, CommandConst.CONFIG_CONVERT_BLOCK_RANGE));
        sender.sendMessage(String.format("%s周囲石化発生時の石化範囲", descPrefix));
        sender.sendMessage(String.format("%s%s %s <number>"
                , usagePrefix, CommandConst.SET_CONFIG, CommandConst.CONFIG_FALL_BLOCK_NUM));
        sender.sendMessage(String.format("%s上から矢などが降ってくる時の同時に降ってくる数", descPrefix));
        sender.sendMessage(String.format("%s%s %s <number>"
                , usagePrefix, CommandConst.SET_CONFIG, CommandConst.CONFIG_TELEPORT_RANGE));
        sender.sendMessage(String.format("%sテレポートの範囲", descPrefix));
        sender.sendMessage(String.format("%s%s %s <number>"
                , usagePrefix, CommandConst.SET_CONFIG, CommandConst.CONFIG_SPAWN_MOB_NUM));
        sender.sendMessage(String.format("%s周囲にモブが発生する時の数", descPrefix));
        sender.sendMessage(String.format("%s%s %s <Player名>"
                , usagePrefix, CommandConst.SET_CONFIG, CommandConst.CONFIG_ADD_NONBINARY_PLAYER));
        sender.sendMessage(String.format("%s性別指定のないプレイヤーのリスト追加", descPrefix));
        sender.sendMessage(String.format("%s%s %s <Player名>"
                , usagePrefix, CommandConst.SET_CONFIG, CommandConst.CONFIG_REMOVE_NONBINARY_PLAYER));
        sender.sendMessage(String.format("%ss性別指定のないプレイヤーのリスト削除", descPrefix));
        sender.sendMessage(String.format("%s%s %s <Player名>"
                , usagePrefix, CommandConst.SET_CONFIG, CommandConst.CONFIG_ADD_WOMAN_PLAYER));
        sender.sendMessage(String.format("%s女参加勢のリスト追加", descPrefix));
        sender.sendMessage(String.format("%s%s %s <Player名>"
                , usagePrefix, CommandConst.SET_CONFIG, CommandConst.CONFIG_REMOVE_WOMAN_PLAYER));
        sender.sendMessage(String.format("%s女参加勢のリスト削除", descPrefix));
        sender.sendMessage(String.format("%s%s %s <Happening名>"
                , usagePrefix, CommandConst.SET_CONFIG, CommandConst.CONFIG_ON_HAPPENING));
        sender.sendMessage(String.format("%s発生するHappeningの追加", descPrefix));
        sender.sendMessage(String.format("%s%s %s <Happening名>"
                , usagePrefix, CommandConst.SET_CONFIG, CommandConst.CONFIG_OFF_HAPPENING));
        sender.sendMessage(String.format("%s発生するHappeningの削除", descPrefix));

        sender.sendMessage(String.format("%s%s"
                , usagePrefix, CommandConst.SHOW_STATUS));
        sender.sendMessage(String.format("%s設定などゲームの状態を確認", descPrefix));

    }

    private int validateNum(CommandSender sender, String target) {
        // 不正な場合は-1を返す
        int num;
        try {
            num = Integer.parseInt(target);
        } catch (NumberFormatException e) {
            sender.sendMessage(DecolationConst.RED + "整数以外が入力されています");
            return -1;
        }
        if (num < 1) {
            sender.sendMessage(DecolationConst.RED + "0より大きい整数を入力してください");
            return -1;
        }
        return num;
    }

    private boolean checkArgsNum(CommandSender sender, int argsLength, int validLength){
        if (argsLength != validLength) {
            sender.sendMessage(DecolationConst.RED + "引数の数が不正です");
            return false;
        }
        return true;
    }
}