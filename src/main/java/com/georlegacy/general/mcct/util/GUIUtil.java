package com.georlegacy.general.mcct.util;

import com.georlegacy.general.mcct.data.objects.hook.enumeration.TreePattern;
import org.bukkit.Bukkit;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GUIUtil {

    private static final Inventory patternGUIInv = Bukkit.createInventory(null, 54,
            ColorUtil.color("&aControl The Tree"));

    public static void updatePatternGUIInv() {
        patternGUIInv.clear();

        ItemStack[] contents = new ItemStack[53];
        TreePattern current = TreeAPIUtil.getCurrentPattern();
        for (int i = 0; i < TreePattern.values().length; i++) {
            ItemStack is = new ItemStack(TreePattern.values()[i].getDisplayMaterial(), 1,
                    (short) TreePattern.values()[i].getData());
            ItemMeta im = is.getItemMeta();
            im.setDisplayName(ColorUtil.color(TreePattern.values()[i].getDisplayName()));
            im.setLocalizedName(ForestNameUtil.patternGUIItemForestLevelName(TreePattern.values()[i]));
            if ((current != null) && TreePattern.values()[i].getApiCode().equals(current.getApiCode()))
                im.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
            is.setItemMeta(im);
            contents[i] = is;
        }
        //todo use 45-54
        patternGUIInv.setContents(contents);
        patternGUIInv.setMaxStackSize(1);
    }

    public static Inventory getPatternGUIInv() {
        updatePatternGUIInv();
        return patternGUIInv;
    }

}
