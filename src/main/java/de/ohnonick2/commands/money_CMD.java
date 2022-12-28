package de.ohnonick2.commands;

import de.ohnonick2.utils.MessageConfigManger;
import de.ohnonick2.utils.MoneyManger;
import org.bukkit.Bukkit;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

//TODO add a message file for the messages

public class money_CMD implements CommandExecutor , TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
       if (sender instanceof Player) {
           Player player = (Player) sender;
           if (args.length == 0) {
               player.sendMessage(MessageConfigManger.getMessage("Commands.money_succes_self" , player , null , Double.parseDouble(MoneyManger.getMoney(player.getUniqueId()))));
           } else if (args.length == 1) {

               if (player.hasPermission("money.see.other")) {
                   Player target = Bukkit.getPlayer(args[0]);
                   if (target != null) {
                       player.sendMessage(MessageConfigManger.getMessage("Commands.money_succes_other" , target , player , Double.parseDouble(MoneyManger.getMoney(target.getUniqueId()))));
                   } else {
                       player.sendMessage(MessageConfigManger.getMessage("PLAYER_NOT_ONLINE", player.getName(), target.getName()));
                   }
               } else {
                   player.sendMessage(MessageConfigManger.getMessage("NO_PERMISSION"));
               }


           }


       } else {

           sender.sendMessage(MessageConfigManger.getMessage("NO_PLAYER"));

       }





        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        if (args.length == 1){

            if (sender.hasPermission("money.see.other")){



                return MoneyManger.getTabCompleter();
            }

        }




        return null;
    }
}
