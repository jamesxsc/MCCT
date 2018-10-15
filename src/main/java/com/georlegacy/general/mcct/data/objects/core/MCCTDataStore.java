package com.georlegacy.general.mcct.data.objects.core;

import com.georlegacy.general.mcct.data.objects.entities.PowerBlock;
import org.bukkit.Location;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class MCCTDataStore implements Serializable {

    public MCCTDataStore() {
        this.powerBlocks = new HashSet<>();
    }

    private final Set<PowerBlock> powerBlocks;

    public Set<PowerBlock> getPowerBlocks() {
        return powerBlocks;
    }

    public PowerBlock getPowerBlock(Location location) {
        return powerBlocks.stream().filter(pb -> {
            return location.getBlockX() == pb.getX() &&
                    location.getBlockY() == pb.getY() &&
                    location.getBlockZ() == pb.getZ() &&
                    location.getWorld() == pb.getWorld();

        }).findFirst().orElse(null);
    }
}
