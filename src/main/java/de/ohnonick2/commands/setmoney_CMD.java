package de.ohnonick2.commands;

import de.ohnonick2.utils.MessageConfigManger;
import de.ohnonick2.utils.MoneyManger;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class setmoney_CMD implements CommandExecutor , TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (!player.hasPermission("money.set")) {
                player.sendMessage(MessageConfigManger.getMessage("NO_PERMISSION"));
                return false;
            }
            if (args.length == 1) {

                try {
                    double money = Double.parseDouble(args[0]);

                    if (money < 0) {
                        player.sendMessage(MessageConfigManger.getMessage("NO_NEGATIVE_NUMBER"));
                        return false;
                    }
                    MoneyManger.setMoney(player.getUniqueId() , money);
                    player.sendMessage(MessageConfigManger.getMessage("Commands.setmoney_success" , player , null , money));
                } catch (NumberFormatException e) {
                    player.sendMessage(MessageConfigManger.getMessage("NO_NEGATIVE_NUMBER"));
                }



            } else if (args.length == 2) {
                Player target = Bukkit.getPlayer(args[0]);

                if (target == null) {
                    player.sendMessage(MessageConfigManger.getMessage("PLAYER_NOT_ONLINE", player.getName() , target.getName()));
                    return false;
                }

                try {
                    double money = Double.parseDouble(args[1]);
                    MoneyManger.setMoney(target.getUniqueId() , money);
                    player.sendMessage(MessageConfigManger.getMessage("Commands.setmoney_success_other" , target , player , money));
                } catch (NumberFormatException e) {
                    player.sendMessage(MessageConfigManger.getMessage("NO_NUMBER"));
                }


            } else {
                player.sendMessage(MessageConfigManger.getMessage("Commands.setmoney_usage"));
            }


        } else {

         sender.sendMessage(MessageConfigManger.getMessage("NO_PLAYER"));

        }


        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {

        if (sender.hasPermission("money.add")){

            if (args.length == 1){

                return MoneyManger.getTabCompleter();

            } else if (args.length == 2){

                List<String> list = new ArrayList<>();

                try {
                    double money = Double.parseDouble(args[1]);
                    list.add(String.valueOf(money));


                } catch (NumberFormatException e) {
                    list.add(MessageConfigManger.getMessage("NO_NUMBER"));
                }

                return list;



            }
        }
        return null;
    }
}
