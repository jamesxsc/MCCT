package com.georlegacy.general.mcct.util;

import org.bukkit.ChatColor;

public class ColorUtil {

    public static final String PREFIX = color("&a&l[&2&lMCCT&a&l] ");

    public static String color(String toColor) {
        return ChatColor.translateAlternateColorCodes('&', toColor);
    }

}
