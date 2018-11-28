package com.georlegacy.general.mcct.util.scripting;

import com.georlegacy.general.mcct.data.objects.hook.enumeration.TreePattern;
import com.georlegacy.general.mcct.util.TreeAPIUtil;

public class TreeAPIScriptFunctions {

    public boolean on() {
        TreeAPIUtil.on();
        return true;
    }

    public boolean off() {
        TreeAPIUtil.off();
        return true;
    }

    public boolean setPattern(TreePattern pattern) {
        TreeAPIUtil.setPattern(pattern);
        return true;
    }

}
