package com.georlegacy.general.mcct.data.objects.entities;

import org.bukkit.Location;
import org.bukkit.Material;

import java.io.Serializable;

public class TreeOnTriggeredSignal implements Serializable {

    public TreeOnTriggeredSignal(Location l) {
        this.x = l.getBlockX();
        this.y = l.getBlockY();
        this.z = l.getBlockZ();
        this.worldName = l.getWorld().getName();
    }

    private final int x, y, z;

    private final String worldName;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public String getWorldName() {
        return worldName;
    }

}
