package com.georlegacy.general.mcct.util;

import com.georlegacy.general.mcct.MCCTPlugin;
import com.georlegacy.general.mcct.data.objects.hook.enumeration.TreePattern;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;

public class TreeOnTriggeredSignalUtil {

    public static void triggerAll() {
        TreePattern current = TreeAPIUtil.getCurrentPattern();
        boolean isOn;
        if (current == null)
            isOn = false;
        else
            isOn = !current.equals(TreePattern.ALLOFF);
        MCCTPlugin.getInstance().getDataStore().getTreeOnTriggeredSignals().forEach(signal ->
                new Location(Bukkit.getWorld(signal.getWorldName()), signal.getX(), signal.getY(), signal.getZ())
                        .getBlock().setType(isOn ? Material.REDSTONE_BLOCK : Material.RED_GLAZED_TERRACOTTA));
    }

}
