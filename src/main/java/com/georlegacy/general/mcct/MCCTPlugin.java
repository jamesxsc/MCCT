package com.georlegacy.general.mcct;

import com.georlegacy.general.mcct.command.GetPowerBlockCreatorCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class MCCTPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        this.registerCommands();
        super.onEnable();
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    private void registerCommands() {
        this.getServer().getPluginCommand("getpowerblockcreator").setExecutor(new GetPowerBlockCreatorCommand());
    }

}
