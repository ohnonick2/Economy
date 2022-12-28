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

public class addmoney_CMD implements CommandExecutor , TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (player.hasPermission("money.add")) {
                if (args.length == 1) {
                    try {
                        double money = Double.parseDouble(args[0]);


                        MoneyManger.addMoney(player.getUniqueId(), money);
                        player.sendMessage(MessageConfigManger.getMessage("Commands.addmoney_success" , player , null , money));

                    } catch (NumberFormatException e) {
                        player.sendMessage(MessageConfigManger.getMessage("NO_NEGATIVE_NUMBER"));
                        return true;
                    }
                } else if (args.length == 2) {
                    Player target = Bukkit.getPlayer(args[0]);

                    if (target != null) {
                        try {
                            double money = Double.parseDouble(args[1]);
                            MoneyManger.addMoney(target.getUniqueId(), money);

                            player.sendMessage(MessageConfigManger.getMessage("Commands.addmoney_success" , target ,  player , money));

                        } catch (NumberFormatException e) {
                            player.sendMessage(MessageConfigManger.getMessage("NO_NUMBER"));
                            return true;
                        }
                    } else {
                        player.sendMessage(MessageConfigManger.getMessage("PLAYER_NOT_ONLINE", player.getName() , target.getName()));
                    }

                } else {
                   player.sendMessage(MessageConfigManger.getMessage("Commands.addmoney_usage"));
                }


            } else {

              sender.sendMessage(MessageConfigManger.getMessage("NO_PERMISSION"));

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
