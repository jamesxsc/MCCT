package com.georlegacy.general.mcct.util;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;

public class ItemUtil {

    public static ItemStack getPowerBlockCreator() {
        ItemStack powerBlockCreator = new ItemStack(Material.IRON_NUGGET);

        ItemMeta powerBlockCreatorMeta = powerBlockCreator.getItemMeta();
        powerBlockCreatorMeta.setDisplayName(ChatColor.GREEN + "Power Block Creator");
        powerBlockCreatorMeta.setLore(new ArrayList<String>(Arrays.asList(
                ColorUtil.color("&2&oRight Click Any Block"),
                ColorUtil.color("&2&oTo Create A Power Block"),
                "",
                ColorUtil.color("&c&oNote:"),
                ColorUtil.color("&7&oYou must have the permission"),
                ColorUtil.color("&emcct.setup.usepowerblockcreator"),
                ColorUtil.color("&7&oto use")
        )));
        powerBlockCreatorMeta.setLocalizedName("server_forest.mcct_domain.items_sub_unit.powerblockcreator");
        powerBlockCreator.setItemMeta(powerBlockCreatorMeta);

        return powerBlockCreator;
    }

}
