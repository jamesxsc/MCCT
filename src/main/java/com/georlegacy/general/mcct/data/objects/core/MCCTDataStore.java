package com.georlegacy.general.mcct.data.objects.core;

import com.georlegacy.general.mcct.data.objects.entities.PowerBlock;
import com.georlegacy.general.mcct.data.objects.entities.TreeOnTriggeredSignal;
import org.bukkit.Location;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class MCCTDataStore implements Serializable {

    public MCCTDataStore() {
        this.powerBlocks = new HashSet<>();
        this.treeOnTriggeredSignals = new HashSet<>();
    }

    private Set<PowerBlock> powerBlocks;
    private final Set<TreeOnTriggeredSignal> treeOnTriggeredSignals;

    public Set<PowerBlock> getPowerBlocks() {
        return powerBlocks;
    }

    public Set<TreeOnTriggeredSignal> getTreeOnTriggeredSignals() {
        return treeOnTriggeredSignals;
    }

    public PowerBlock getPowerBlock(Location location) {
        return powerBlocks.stream().filter(pb -> location.getBlockX() == pb.getX() &&
                location.getBlockY() == pb.getY() &&
                location.getBlockZ() == pb.getZ() &&
                location.getWorld().getName().equals(pb.getWorldName())).findFirst().orElse(null);
    }

    public TreeOnTriggeredSignal getTreeOnTriggeredSignal(Location location) {
        return treeOnTriggeredSignals.stream().filter(signal -> location.getBlockX() == signal.getX() &&
                location.getBlockY() == signal.getY() &&
                location.getBlockZ() == signal.getZ() &&
                location.getWorld().getName().equals(signal.getWorldName())).findFirst().orElse(null);
    }

    public void reloadPowerBlocks() {
        this.powerBlocks = new HashSet<>();
    }

}
