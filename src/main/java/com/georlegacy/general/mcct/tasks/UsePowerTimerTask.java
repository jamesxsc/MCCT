package com.georlegacy.general.mcct.tasks;

import com.georlegacy.general.mcct.MCCTPlugin;
import com.georlegacy.general.mcct.data.objects.hook.enumeration.TreePattern;
import com.georlegacy.general.mcct.util.TreeAPIUtil;
import com.georlegacy.general.mcct.util.TreeOnTriggeredSignalUtil;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class UsePowerTimerTask extends BukkitRunnable {

    @Override
    public void run() {
        Player player = Bukkit.getPlayer(MCCTPlugin.getInstance().getUsePowerTimer().getKey());
        int power = MCCTPlugin.getInstance().getPowerLevels().getOrDefault(player.getUniqueId(), 0);
        if (power < 1) {
            TreeAPIUtil.setPattern(TreePattern.ALLOFF);
            player.playSound(player.getLocation(), Sound.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 1, 1);
            TextComponent tc = new TextComponent("You have run out of power!");
            tc.setColor(ChatColor.GREEN);
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, tc);
            TreeAPIUtil.setPattern(TreePattern.ALLOFF);
            TreeOnTriggeredSignalUtil.triggerAll();
            Bukkit.getScheduler().cancelTask(MCCTPlugin.getInstance().getUsePowerTimer().getValue());
        }
        else {
            MCCTPlugin.getInstance().getPowerLevels().put(player.getUniqueId(),
                    MCCTPlugin.getInstance().getPowerLevels().containsKey(player.getUniqueId()) ?
                            MCCTPlugin.getInstance().getPowerLevels().get(player.getUniqueId()) - 1 :
                            0
            );

            if (power == 1) {
                player.playSound(player.getLocation(), Sound.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 1, 1);
                TextComponent tc = new TextComponent("You have run out of power, the tree will turn off in 1 minute.");
                tc.setColor(ChatColor.GREEN);
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, tc);
                Bukkit.getScheduler().scheduleSyncDelayedTask(MCCTPlugin.getInstance(),
                        new NoPowerTreeOffTask(), 60 * 20);
                Bukkit.getScheduler().cancelTask(MCCTPlugin.getInstance().getUsePowerTimer().getValue());
            }
            else {
                player.playSound(player.getLocation(), Sound.ITEM_BOTTLE_FILL, SoundCategory.BLOCKS, 1, 1);
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < MCCTPlugin.getInstance().getPowerLevels().get(player.getUniqueId()); i++)
                    sb.append("\u2588");
                for (int i = 0; i < 20 - MCCTPlugin.getInstance().getPowerLevels().get(player.getUniqueId()); i++)
                    sb.append("\u2591");
                TextComponent tc = new TextComponent(sb.toString());
                tc.setColor(ChatColor.YELLOW);
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, tc);
            }
        }
    }

}
