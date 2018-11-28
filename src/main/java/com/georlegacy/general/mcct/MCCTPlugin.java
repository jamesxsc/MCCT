package com.georlegacy.general.mcct;

import com.georlegacy.general.mcct.command.AddTreeOnTriggeredSignalCommand;
import com.georlegacy.general.mcct.command.ControlTreeCommand;
import com.georlegacy.general.mcct.command.GetPowerBlockCreatorCommand;
import com.georlegacy.general.mcct.data.objects.core.MCCTDataStore;
import com.georlegacy.general.mcct.data.objects.entities.util.impl.Entry;
import com.georlegacy.general.mcct.data.objects.hook.enumeration.TreePattern;
import com.georlegacy.general.mcct.data.storage.DataFileManager;
import com.georlegacy.general.mcct.listener.PlayerQuitListener;
import com.georlegacy.general.mcct.listener.UsePatternGUIListener;
import com.georlegacy.general.mcct.listener.UsePowerBlockCreatorListener;
import com.georlegacy.general.mcct.listener.UsePowerBlockListener;
import com.georlegacy.general.mcct.tasks.async.PowerBlockParticleTask;
import com.georlegacy.general.mcct.util.TreeAPIUtil;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class MCCTPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        instance = this;

        dataFileManager = new DataFileManager();
        dataFileManager.loadDataStore();

        powerLevels = new HashMap<>();

        this.getServer().getScheduler().scheduleAsyncRepeatingTask(this, new PowerBlockParticleTask(),
                1, 10);

        loadTree();

        this.registerCommands();
        this.registerListeners();

        super.onEnable();
    }

    @Override
    public void onDisable() {
        dataFileManager.saveDataStore();
        super.onDisable();

        TreeAPIUtil.off();
    }

    private static MCCTPlugin instance;

    public static MCCTPlugin getInstance() {
        return instance;
    }

    private DataFileManager dataFileManager;

    public DataFileManager getDataFileManager() {
        return dataFileManager;
    }

    public MCCTDataStore getDataStore() {
        return dataFileManager.getDataStore();
    }

    private Map<UUID, Integer> powerLevels;

    public Map<UUID, Integer> getPowerLevels() {
        return powerLevels;
    }

    private Entry<UUID, Integer> usePowerTimer;

    public Entry<UUID, Integer> getUsePowerTimer() {
        return this.usePowerTimer;
    }

    public void setUsePowerTimer(Entry<UUID, Integer> usePowerTimer) {
        this.usePowerTimer = usePowerTimer;
    }

    private void registerCommands() {
        this.getServer().getPluginCommand("addtreeontriggeredsignal").setExecutor(new AddTreeOnTriggeredSignalCommand());
        this.getServer().getPluginCommand("controltree").setExecutor(new ControlTreeCommand());
        this.getServer().getPluginCommand("getpowerblockcreator").setExecutor(new GetPowerBlockCreatorCommand());
    }

    private void registerListeners() {
        this.getServer().getPluginManager().registerEvents(new PlayerQuitListener(), this);
        this.getServer().getPluginManager().registerEvents(new UsePatternGUIListener(), this);
        this.getServer().getPluginManager().registerEvents(new UsePowerBlockCreatorListener(), this);
        this.getServer().getPluginManager().registerEvents(new UsePowerBlockListener(), this);
    }

    private void loadTree() {
        TreeAPIUtil.on();
        TreeAPIUtil.setPattern(TreePattern.ALLOFF);
    }

}
