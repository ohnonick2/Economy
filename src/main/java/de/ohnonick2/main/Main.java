package de.ohnonick2.main;

import de.ohnonick2.commands.*;
import de.ohnonick2.listerner.Join;

import de.ohnonick2.utils.MessageConfigManger;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private static Main instance;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;


        System.out.println("Plugin is enabled! Made by Ohnonick2");



        PluginManager manager = Bukkit.getPluginManager();
        manager.registerEvents(new Join(), this);


        getCommand("money").setExecutor(new money_CMD());
        getCommand("addmoney").setExecutor(new addmoney_CMD());
        getCommand("setmoney").setExecutor(new setmoney_CMD());
        getCommand("pay").setExecutor(new pay_CMD());
        getCommand("removemoney").setExecutor(new remove_CMD());

        MessageConfigManger messageConfigManger = new MessageConfigManger();


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("Plugin is disabled! peepoSADGE  Made by Ohnonick2");


    }

    public static Main getInstance() {
        return instance;
    }
}
