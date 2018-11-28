package com.georlegacy.general.mcct.util;

import com.georlegacy.general.mcct.data.objects.hook.enumeration.TreePattern;

import java.util.regex.Pattern;

public class ForestNameUtil {

    // Encoding

    public static final String powerBlockCreatorForestLevelName = "server_forest.mcct_domain.items_sub_unit.powerblockcreator";

    public static String patternGUIItemForestLevelName(TreePattern pattern) {
        return String.format("server_forest.mcct_domain.items_sub_unit.pattern_items_site.%s", pattern.getApiCode());
    }

    // Decoding

    public static TreePattern forestLevelNamedPatternGUIItemToTreePattern(String forestLevelName) {
        if (forestLevelName.trim().startsWith("server_forest.mcct_domain.items_sub_unit.pattern_items_site")) {
            return TreePattern.valueOf(forestLevelName.split(Pattern.quote("."))[4].toUpperCase());
        }
        else throw new IllegalArgumentException("Wrong Forest/Domain/Sub Unit/Site for decoder; Try another decoder");
    }

}
