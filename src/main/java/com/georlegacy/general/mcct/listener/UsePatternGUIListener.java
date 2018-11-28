package com.georlegacy.general.mcct.listener;

import com.georlegacy.general.mcct.MCCTPlugin;
import com.georlegacy.general.mcct.data.objects.entities.util.impl.Entry;
import com.georlegacy.general.mcct.data.objects.hook.enumeration.TreePattern;
import com.georlegacy.general.mcct.tasks.UsePowerTimerTask;
import com.georlegacy.general.mcct.util.*;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.UUID;

public class UsePatternGUIListener implements Listener {

    @EventHandler
    public void onInteract(InventoryClickEvent event) {
        if (event.getClickedInventory() == null) {
            return;
        }
        if (event.getClickedInventory().getName().equals(ColorUtil.color("&aControl The Tree"))) {
            event.setCancelled(true);
            if (event.getCurrentItem() == null ||
                    event.getCurrentItem().getType().equals(Material.AIR) ||
                    event.getCurrentItem().getItemMeta() == null ||
                    event.getCurrentItem().getItemMeta().getLocalizedName() == null) {
                return;
            }

            if (!event.getClick().isLeftClick()) {
                return;
            }

            TreePattern clicked = ForestNameUtil.forestLevelNamedPatternGUIItemToTreePattern(
                    event.getCurrentItem().getItemMeta().getLocalizedName());
            if (clicked.equals(TreePattern.ALLOFF)) {
                TreeAPIUtil.setPattern(TreePattern.ALLOFF);
            }
            int power = MCCTPlugin.getInstance().getPowerLevels().getOrDefault(
                    event.getWhoClicked().getUniqueId(), 0);
            if (power < 1) {
                ((Player) event.getWhoClicked()).playSound(event.getWhoClicked().getLocation(),
                        Sound.BLOCK_FIRE_EXTINGUISH, 1, 1);
                TextComponent tc = new TextComponent("You have run out of power!");
                tc.setColor(ChatColor.GREEN);
                ((Player) event.getWhoClicked()).spigot().sendMessage(ChatMessageType.ACTION_BAR, tc);
                event.getWhoClicked().closeInventory();
            } else {
                if (!event.getWhoClicked().hasPermission("mcct.usage.controltree.overridepatternrelease") &&
                        clicked.getUnlockDay() != 0) {
                    long currentUnix = System.currentTimeMillis();
                    long releaseMillis = /* Start of december 2018 GMT */ 1543622400L +
                            (clicked.getUnlockDay() * 24 * 60 * 60 * 1000);
                    if (releaseMillis > currentUnix) {
                        ((Player) event.getWhoClicked()).playSound(event.getWhoClicked().getLocation(),
                                Sound.BLOCK_ANVIL_FALL, 1, 1);
                        TextComponent tc = new TextComponent("That pattern will only be available on Day " +
                                clicked.getUnlockDay() + "!");
                        tc.setColor(ChatColor.GREEN);
                        ((Player) event.getWhoClicked()).spigot().sendMessage(ChatMessageType.ACTION_BAR, tc);
                        return;
                    }
                }
                ((Player) event.getWhoClicked()).playSound(event.getWhoClicked().getLocation(),
                        Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 1);
                TreeAPIUtil.setPattern(clicked);
                GUIUtil.updatePatternGUIInv();
                TreeOnTriggeredSignalUtil.triggerAll();
                power--;
                MCCTPlugin.getInstance().getPowerLevels().put(event.getWhoClicked().getUniqueId(), power);
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < MCCTPlugin.getInstance().getPowerLevels().get(event.getWhoClicked().getUniqueId()); i++)
                    sb.append("\u2588");
                for (int i = 0; i < 20 - MCCTPlugin.getInstance().getPowerLevels().get(event.getWhoClicked().getUniqueId()); i++)
                    sb.append("\u2591");
                TextComponent tc = new TextComponent(sb.toString());
                tc.setColor(ChatColor.YELLOW);
                ((Player) event.getWhoClicked()).spigot().sendMessage(ChatMessageType.ACTION_BAR, tc);
                if (MCCTPlugin.getInstance().getUsePowerTimer() != null) {
                    Bukkit.getScheduler().cancelTask(MCCTPlugin.getInstance().getUsePowerTimer().getValue());
                }

                if (!clicked.equals(TreePattern.ALLOFF)) {
                    MCCTPlugin.getInstance().setUsePowerTimer(new Entry<UUID, Integer>(event.getWhoClicked().getUniqueId(),
                            Bukkit.getScheduler()
                                    .scheduleSyncRepeatingTask(MCCTPlugin.getInstance(),
                                            new UsePowerTimerTask(), 60 * 20, 60 * 20)));
                }
            }
        }
    }

}
