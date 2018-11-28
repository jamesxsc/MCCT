package com.georlegacy.general.mcct.tasks;

import com.georlegacy.general.mcct.data.objects.hook.enumeration.TreePattern;
import com.georlegacy.general.mcct.util.TreeAPIUtil;
import com.georlegacy.general.mcct.util.TreeOnTriggeredSignalUtil;
import org.bukkit.scheduler.BukkitRunnable;

public class NoPowerTreeOffTask extends BukkitRunnable {

    @Override
    public void run() {
        TreeAPIUtil.setPattern(TreePattern.ALLOFF);
        TreeOnTriggeredSignalUtil.triggerAll();
    }

}
