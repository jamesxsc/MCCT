package com.georlegacy.general.mcct.listener;

import com.georlegacy.general.mcct.MCCTPlugin;
import com.georlegacy.general.mcct.data.objects.entities.PowerBlock;
import com.georlegacy.general.mcct.util.ColorUtil;
import com.georlegacy.general.mcct.util.ForestNameUtil;
import com.georlegacy.general.mcct.util.LocationUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

public class UsePowerBlockCreatorListener implements Listener {

    @EventHandler
    public void onUse(PlayerInteractEvent event) {
        if (event == null ||
                event.getHand() == null) {
            // Prevent citizens errors
            return;
        }
        if (event.getHand().equals(EquipmentSlot.OFF_HAND)) {
            return;
        }
        if (event.getItem() == null || event.getItem().getItemMeta() == null ||
                event.getItem().getItemMeta().getLocalizedName() == null) {
            return;
        }
        if (!event.getItem().getItemMeta().getLocalizedName().equals(ForestNameUtil.powerBlockCreatorForestLevelName)) {
            return;
        }
        if (event.getAction().equals(Action.LEFT_CLICK_AIR) ||
                event.getAction().equals(Action.LEFT_CLICK_BLOCK) ||
                event.getAction().equals(Action.RIGHT_CLICK_AIR)) {
            return;
        }
        Player player = event.getPlayer();
        if (!player.hasPermission("mcct.setup.usepowerblockcreator")) {
            player.sendMessage(ColorUtil.PREFIX + ColorUtil.color(
                    "&cYou do not have permission to use a power block creator."));
            return;
        }

        if (player.isSneaking()) {
            for (PowerBlock pb : MCCTPlugin.getInstance().getDataStore().getPowerBlocks()) {
                if (LocationUtil.is(pb, event.getClickedBlock().getLocation())) {
                    MCCTPlugin.getInstance().getDataStore().getPowerBlocks().remove(pb);
                    player.sendMessage(ColorUtil.PREFIX + ColorUtil.color(
                            "&7The block at &e&oXYZ:" +
                                    String.join(", ",
                                            String.valueOf(event.getClickedBlock().getLocation().getBlockX()),
                                            String.valueOf(event.getClickedBlock().getLocation().getBlockY()),
                                            String.valueOf(event.getClickedBlock().getLocation().getBlockZ())) +
                                    " &7is no longer a power block."
                    ));
                    return;
                }
            }
            player.sendMessage(ColorUtil.PREFIX + ColorUtil.color(
                    "&7The block at &e&oXYZ:" +
                            String.join(", ",
                                    String.valueOf(event.getClickedBlock().getLocation().getBlockX()),
                                    String.valueOf(event.getClickedBlock().getLocation().getBlockY()),
                                    String.valueOf(event.getClickedBlock().getLocation().getBlockZ())) +
                            " &7was never a power block."
            ));
        } else {
            if (MCCTPlugin.getInstance().getDataStore() != null) {
                for (PowerBlock pb : MCCTPlugin.getInstance().getDataStore().getPowerBlocks()) {
                    if (LocationUtil.is(pb, event.getClickedBlock().getLocation())) {
                        player.sendMessage(ColorUtil.PREFIX + ColorUtil.color(
                                "&7The block at &e&oXYZ:" +
                                        String.join(", ",
                                                String.valueOf(event.getClickedBlock().getLocation().getBlockX()),
                                                String.valueOf(event.getClickedBlock().getLocation().getBlockY()),
                                                String.valueOf(event.getClickedBlock().getLocation().getBlockZ())) +
                                        " &7is already a power block."
                        ));
                        return;
                    }
                }
            }
            if (MCCTPlugin.getInstance().getDataStore() == null) {
                MCCTPlugin.getInstance().getDataFileManager().loadDataStore();
            }
            if (MCCTPlugin.getInstance().getDataStore().getPowerBlocks() == null) {
                MCCTPlugin.getInstance().getDataStore().reloadPowerBlocks();
            }
            MCCTPlugin.getInstance().getDataStore().getPowerBlocks().add(new PowerBlock(
                    event.getClickedBlock().getLocation(), event.getMaterial()));
            player.sendMessage(ColorUtil.PREFIX + ColorUtil.color(
                    "&7The block at &e&oXYZ:" +
                            String.join(", ",
                                    String.valueOf(event.getClickedBlock().getLocation().getBlockX()),
                                    String.valueOf(event.getClickedBlock().getLocation().getBlockY()),
                                    String.valueOf(event.getClickedBlock().getLocation().getBlockZ())) +
                            " &7is now a power block."
            ));
        }
    }

}
