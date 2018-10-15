package com.georlegacy.general.mcct.command;

import com.georlegacy.general.mcct.util.ColorUtil;
import com.georlegacy.general.mcct.util.ItemUtil;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GetPowerBlockCreatorCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;
        if (!player.hasPermission("mcct.setup.getpowerblockcreator")) {
            player.sendMessage(ColorUtil.PREFIX + ColorUtil.color(
                    "&cYou do not have permission to use this command."));
            return true;
        }

        boolean hasSpace = false;
        for (ItemStack is : player.getInventory().getStorageContents()) {
            if (is == null || is.getType().equals(Material.AIR)) {
                hasSpace = true;
                break;
            }
        }

        System.out.println(hasSpace);

        if (!hasSpace) {
            player.sendMessage(ColorUtil.PREFIX + ColorUtil.color("&cYour inventory is full!"));
            return true;
        }

        player.getInventory().addItem(ItemUtil.getPowerBlockCreator());
        player.sendMessage(ColorUtil.PREFIX + ColorUtil.color("&7You have been given a power block creator."));
        return true;
    }

}
