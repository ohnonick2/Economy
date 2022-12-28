package de.ohnonick2.utils;

import de.ohnonick2.main.Main;
import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;

public class MessageConfigManger {
    public static File messageDir = new File(Main.getInstance().getDataFolder().getAbsolutePath());
    public static File messageFile = new File(messageDir, "config.yml");

    public static YamlConfiguration messageConfig = YamlConfiguration.loadConfiguration(messageFile);
    public MessageConfigManger(){

        if (!messageDir.exists()){
            messageDir.mkdir();
            System.out.println("Message folder created!");
        }
        if (!messageFile.exists()){
            try {
                messageFile.createNewFile();

                Main.getInstance().saveDefaultConfig();

            }catch (Exception e){
                System.out.println(e);
            }
        }


    }





    public static String getMessage(String path){

        String message = messageConfig.getString(path);

        if (message.contains("%prefix%")) {
            message = message.replace("%prefix%", messageConfig.getString("prefix"));
        }
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static String getMessage(String path, Player player , Player target , double amount){

        String message = messageConfig.getString(path);

        if (message.contains("%prefix%")) {
            message = message.replace("%prefix%", messageConfig.getString("prefix"));
        }
        if (message.contains("%player%")) {
            message = message.replace("%player%", player.getName());
        }

        if (target != null) {
            if (message.contains("%target%")) {
                message = message.replace("%target%", target.getName());
            }
        }

        if (message.contains("%amount%")) {
            message = message.replace("%amount%", String.valueOf(amount));
        }
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static String getMessage(String path, String player , String target){

        String message = messageConfig.getString(path);

        if (message.contains("%prefix%")) {
            message = message.replace("%prefix%", messageConfig.getString("prefix"));
        }
        if (message.contains("%player%")) {
            message = message.replace("%player%", player);
        }

        if (target != null) {
            if (message.contains("%target%")) {
                message = message.replace("%target%", target);
            }
        }
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
