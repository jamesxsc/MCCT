package com.georlegacy.general.mcct;

import com.georlegacy.general.mcct.command.GetPowerBlockCreatorCommand;
import com.georlegacy.general.mcct.data.objects.core.MCCTDataStore;
import com.georlegacy.general.mcct.data.storage.DataFileManager;
import com.georlegacy.general.mcct.listener.UsePowerBlockCreatorListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class MCCTPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        instance = this;

        dataFileManager = new DataFileManager();
        dataFileManager.loadDataStore();

        this.registerCommands();
        this.registerListeners();
        super.onEnable();
    }

    @Override
    public void onDisable() {
        dataFileManager.saveDataStore();
        super.onDisable();
    }

    private static MCCTPlugin instance;

    public static MCCTPlugin getInstance() {
        return instance;
    }

    private DataFileManager dataFileManager;

    public MCCTDataStore getDataStore() {
        return dataFileManager.getDataStore();
    }

    private void registerCommands() {
        this.getServer().getPluginCommand("getpowerblockcreator").setExecutor(new GetPowerBlockCreatorCommand());
    }

    private void registerListeners() {
        this.getServer().getPluginManager().registerEvents(new UsePowerBlockCreatorListener(), this);
    }

}
