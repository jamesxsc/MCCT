package com.georlegacy.general.mcct.listener;

import com.georlegacy.general.mcct.MCCTPlugin;
import com.georlegacy.general.mcct.data.objects.hook.enumeration.TreePattern;
import com.georlegacy.general.mcct.util.TreeAPIUtil;
import com.georlegacy.general.mcct.util.TreeOnTriggeredSignalUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        if (MCCTPlugin.getInstance().getUsePowerTimer() == null) {
            return;
        }
        if (MCCTPlugin.getInstance().getUsePowerTimer().getKey() == null) {
            return;
        }
        if (MCCTPlugin.getInstance().getUsePowerTimer().getKey().equals(player.getUniqueId())) {
            Bukkit.getScheduler().cancelTask(MCCTPlugin.getInstance().getUsePowerTimer().getValue());
            TreeAPIUtil.setPattern(TreePattern.ALLOFF);
            TreeOnTriggeredSignalUtil.triggerAll();
        }
    }

}
