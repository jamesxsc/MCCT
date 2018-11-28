package com.georlegacy.general.mcct.command;

import com.georlegacy.general.mcct.util.ColorUtil;
import com.georlegacy.general.mcct.util.GUIUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ControlTreeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Sorry! This command can only be used by players.");
            return true;
        }
        Player player = (Player) sender;
        if (!player.hasPermission("mcct.usage.controltree")) {
            player.sendMessage(ColorUtil.PREFIX + ColorUtil.color(
                    "&cYou do not have permission to use this command."));
            return true;
        }
        player.openInventory(GUIUtil.getPatternGUIInv());
        return true;
    }

}
