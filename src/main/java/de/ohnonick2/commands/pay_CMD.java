package de.ohnonick2.commands;

import de.ohnonick2.utils.MessageConfigManger;
import de.ohnonick2.utils.MoneyManger;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class pay_CMD implements CommandExecutor , TabCompleter {
    @Override
    public boolean onCommand(org.bukkit.command.@NotNull CommandSender sender, org.bukkit.command.@NotNull Command command, @NotNull String label, String[] args) {

        if (sender instanceof Player) {
            Player player  = (Player) sender;

            if (args.length == 2) {
                Player target = org.bukkit.Bukkit.getPlayer(args[0]);

                if (target == null) {
                    player.sendMessage(MessageConfigManger.getMessage("pay.player-not-online"));
                    return false;
                }

                try {
                    double money = Double.parseDouble(args[1]);

                    if (MoneyManger.getMoney(player.getUniqueId()) == null) {
                        player.sendMessage(MessageConfigManger.getMessage("NO_ACCOUNT"));
                        return false;
                    }
                    if (Double.parseDouble(Objects.requireNonNull(MoneyManger.getMoney(player.getUniqueId()))) < money) {
                        player.sendMessage(MessageConfigManger.getMessage("NO_MONEY"));
                        return false;
                    }

                    if (target == player) {
                        player.sendMessage(MessageConfigManger.getMessage("Commands.pay_no_self"));
                        return false;
                    }
                    MoneyManger.setMoney(player.getUniqueId() , Double.parseDouble(Objects.requireNonNull(MoneyManger.getMoney(player.getUniqueId()))) - money);
                    MoneyManger.setMoney(target.getUniqueId() , Double.parseDouble(Objects.requireNonNull(MoneyManger.getMoney(target.getUniqueId()))) + money);
                    player.sendMessage(MessageConfigManger.getMessage("Commands.pay_success_sender" , target ,  player , money));
                    target.sendMessage(MessageConfigManger.getMessage("Commands.pay_success_target" , player ,  target , money));


                } catch (NumberFormatException e) {
                    player.sendMessage(MessageConfigManger.getMessage("NO_NEGATIVE_NUMBER"));
                    return false;
                }


            }



        } else {
            sender.sendMessage(MessageConfigManger.getMessage("NO_PLAYER"));
        }




        return false;
    }

    @Override
    public java.util.List<String> onTabComplete(org.bukkit.command.@NotNull CommandSender sender, org.bukkit.command.@NotNull Command command, @NotNull String alias, String[] args) {

        if (args.length == 1){

            return MoneyManger.getTabCompleter();

        } else if (args.length == 2){

            List<String> list = new ArrayList<>();

            try {
                double money = Double.parseDouble(args[1]);
                list.add(String.valueOf(money));


            } catch (NumberFormatException e) {
                list.add(MessageConfigManger.getMessage("NO_NEGATIVE_NUMBER"));
            }

            return list;



        }



        return null;
    }
}

