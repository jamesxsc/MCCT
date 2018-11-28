package com.georlegacy.general.mcct.listener;

import com.georlegacy.general.mcct.MCCTPlugin;
import com.georlegacy.general.mcct.data.objects.entities.PowerBlock;
import com.georlegacy.general.mcct.util.ForestNameUtil;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

public class UsePowerBlockListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if (event == null ||
                event.getHand() == null) {
            // Prevent citizens errors
            return;
        }
        if (event.getHand().equals(EquipmentSlot.OFF_HAND)) {
            return;
        }
        if (event.getClickedBlock() == null ||
                event.getClickedBlock().getType().equals(Material.AIR)) {
            return;
        }
        if (MCCTPlugin.getInstance().getDataStore() == null) {
            return;
        }
        if (MCCTPlugin.getInstance().getDataStore().getPowerBlock(event.getClickedBlock().getLocation()) == null) {
            return;
        }
        PowerBlock pb = MCCTPlugin.getInstance().getDataStore().getPowerBlock(event.getClickedBlock().getLocation());
        Player player = event.getPlayer();

        if (pb == null) {
            return;
        }
        if (!(event.getItem() == null) && !(event.getItem().getItemMeta() == null) &&
                !(event.getItem().getItemMeta().getLocalizedName() == null)) {
            if (event.getItem().getItemMeta().getLocalizedName().equals(ForestNameUtil.powerBlockCreatorForestLevelName)) {
                return;
            }
        }
        if (event.getAction().equals(Action.LEFT_CLICK_AIR) ||
                event.getAction().equals(Action.LEFT_CLICK_BLOCK) ||
                event.getAction().equals(Action.RIGHT_CLICK_AIR)) {
            return;
        }

        if (player.isSneaking()) {
            return;
        }

        if (pb.getLastUse() > System.currentTimeMillis() - 600000 /* 10 mins */) {
            player.playSound(player.getLocation(), Sound.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 1, 1);
            TextComponent tc = new TextComponent("Try again in " + (10 - ((int) (System.currentTimeMillis() -
                    pb.getLastUse()) / 60000) + " minute(s)"));
            tc.setColor(ChatColor.GREEN);
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, tc);
            event.setCancelled(true);
        } else {
            if (MCCTPlugin.getInstance().getPowerLevels().getOrDefault(player.getUniqueId(), 0) == 20) {
                player.playSound(player.getLocation(), Sound.ITEM_ARMOR_EQUIP_GOLD, SoundCategory.BLOCKS, 1, 1);
                TextComponent tc = new TextComponent("Your power storage is full");
                tc.setColor(ChatColor.GREEN);
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, tc);
                event.setCancelled(true);
                return;
            }

            MCCTPlugin.getInstance().getPowerLevels().put(player.getUniqueId(),
                    MCCTPlugin.getInstance().getPowerLevels().containsKey(player.getUniqueId()) ?
                            MCCTPlugin.getInstance().getPowerLevels().get(player.getUniqueId()) + 1 :
                            1
            );

            player.playSound(player.getLocation(), Sound.ITEM_ARMOR_EQUIP_GOLD, SoundCategory.BLOCKS, 1, 1);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < MCCTPlugin.getInstance().getPowerLevels().get(player.getUniqueId()); i++)
                sb.append("\u2588");
            for (int i = 0; i < 20 - MCCTPlugin.getInstance().getPowerLevels().get(player.getUniqueId()); i++)
                sb.append("\u2591");
            TextComponent tc = new TextComponent(sb.toString());
            tc.setColor(ChatColor.YELLOW);
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, tc);
            pb.setLastUse(System.currentTimeMillis());
            event.setCancelled(true);
        }
    }

}
