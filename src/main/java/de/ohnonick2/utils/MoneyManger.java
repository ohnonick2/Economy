package de.ohnonick2.utils;

import org.bukkit.Bukkit;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MoneyManger {

    private static ConfigManger configManger = new ConfigManger();


    public static @Nullable String getMoney(UUID uuid){
        return configManger.moneyConfig.getString("Money." + uuid);
    }

    public static void addMoney(UUID uuid , double money){
        double currentMoney = configManger.moneyConfig.getDouble("Money." + uuid);

        configManger.setMoney(uuid , currentMoney + money);
        configManger.saveMoneyConfig();

    }

    public static void removeMoney(UUID uuid , double money) {
        double currentMoney = configManger.moneyConfig.getDouble("Money." + uuid);

        configManger.setMoney(uuid, currentMoney - money);
        configManger.saveMoneyConfig();
    }
    public static void setMoney(UUID uuid , double money){
        configManger.setMoney(uuid , money);
        configManger.saveMoneyConfig();
    }


    public static List<String> getTabCompleter() {
        List<String> tabCompleter = new ArrayList<>();

        Bukkit.getOnlinePlayers().forEach(player -> tabCompleter.add(player.getName()));

        return tabCompleter;
    }

}
