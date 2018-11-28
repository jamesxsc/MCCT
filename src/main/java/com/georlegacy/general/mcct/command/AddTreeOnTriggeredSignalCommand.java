package com.georlegacy.general.mcct.command;

import com.georlegacy.general.mcct.MCCTPlugin;
import com.georlegacy.general.mcct.data.objects.entities.TreeOnTriggeredSignal;
import com.georlegacy.general.mcct.util.ColorUtil;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AddTreeOnTriggeredSignalCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Sorry! This command can only be used by players.");
            return true;
        }
        Player player = (Player) sender;
        if (!player.hasPermission("mcct.setup.createtreeontriggeredsignal")) {
            player.sendMessage(ColorUtil.PREFIX + ColorUtil.color(
                    "&cYou do not have permission to use this command."));
            return true;
        }
        Block lookingAt = player.getTargetBlock(null ,5);
        if (lookingAt == null ||
                lookingAt.getType() == null ||
                lookingAt.getType().equals(Material.AIR)) {
            player.sendMessage(ColorUtil.PREFIX + ColorUtil.color(
                    "&cYou need to look at a block to turn into a Tree On Triggered Signal."));
            return true;
        }
        if (MCCTPlugin.getInstance().getDataStore().getTreeOnTriggeredSignal(lookingAt.getLocation()) != null) {
            player.sendMessage(ColorUtil.PREFIX + ColorUtil.color(
                    "&cThat block is already a Tree On Triggered Signal."));
            return true;
        }
        MCCTPlugin.getInstance().getDataStore().getTreeOnTriggeredSignals().add(
                new TreeOnTriggeredSignal(lookingAt.getLocation()));
        player.sendMessage(ColorUtil.PREFIX + ColorUtil.color(
                "&aThat block has successfully been turned into a Tree On Triggered Signal."));
        return true;
    }

}
