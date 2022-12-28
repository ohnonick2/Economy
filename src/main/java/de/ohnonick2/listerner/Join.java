package de.ohnonick2.listerner;

import de.ohnonick2.utils.ConfigManger;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.UUID;

public class Join implements Listener {

    @EventHandler(ignoreCancelled = true)
    public void onPlayerJoin(PlayerJoinEvent event) {
        UUID uuid = event.getPlayer().getUniqueId();

        ConfigManger configManger = new ConfigManger();

        if (configManger.moneyConfig.get("Money." + uuid) == null){
            configManger.setMoney(uuid , 0);
            configManger.saveMoneyConfig();
        }
    }
}
