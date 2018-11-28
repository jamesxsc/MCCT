package com.georlegacy.general.mcct.data.objects.hook.enumeration;

import org.bukkit.Material;

@SuppressWarnings("SpellCheckingInspection")
public enum TreePattern {

    ALLOFF("alloff", "&7All Off", Material.CONCRETE, 15, 0),
    ALLRED("allred", "&4All Red", Material.INK_SACK, 1, 0),
    ALLBLUE("allblue", "&1All Blue", Material.INK_SACK, 4, 0),
    ALLGREEN("allgreen", "&2All Green", Material.INK_SACK, 10, 0),
    ALLWHITE("allwhite", "&fAll White", Material.SUGAR, 0, 0),
    TWOSPLIT("twosplit", "&aTwo &eSplit", Material.STEP, 7, 0),
    TWOSPLITWAVE("twosplitwave", "&eTwo &aSplit &eW&aa&ev&ae", Material.STEP, 7, 2),
    COLOURWIPEMIDDLE("colourwipemiddle", "&4C&6o&el&2o&1u&5r &fWipe Mi&edd&fle", Material.EMPTY_MAP, 0, 3),
    WIPEINOUT("wipeinout", "&fWipe &2In&f/&4Out", Material.DIODE, 0, 0),
    WIPEINOUTRANDOM("wipeinoutrandom", "&fWipe &2In&f/&4Out &fRandom", Material.MAGENTA_GLAZED_TERRACOTTA, 0, 4),
    BLOCKCHASE("blockchase", "&fBlock &2Ch&4as&1e", Material.IRON_BOOTS, 0, 0),
    BLOBRUN("blobrun", "&fBl&4o&fb &fRun", Material.GOLD_BOOTS, 0, 2),
    COLOURFLASH("colourflash", "&4C&6o&el&2o&1u&5r &fFlash", Material.SEA_LANTERN, 0, 0),
    CRAZY("crazy", "&2&oC&1&mr&5&ma&4&lz&6&o&my", Material.SPONGE, 0, 5),
    COLOURWIPE("colourwipe", "&4C&6o&el&2o&1u&5r &fWipe", Material.PAPER, 0, 0),
    ;

    private final String apiCode;
    private final String displayName;
    private final Material displayMaterial;
    private final int data;
    private final int unlockDay;

    public String getApiCode() {
        return apiCode;
    }

    public String getDisplayName() {
        return displayName;
    }

    public Material getDisplayMaterial() {
        return displayMaterial;
    }

    public int getData() {
        return data;
    }

    public int getUnlockDay() {
        return unlockDay;
    }

    TreePattern(String apiCode, String displayName, Material displayMaterial, int data, int unlockDay) {
        this.apiCode = apiCode;
        this.displayName = displayName;
        this.displayMaterial = displayMaterial;
        this.data = data;
        this.unlockDay = unlockDay;
    }

}
