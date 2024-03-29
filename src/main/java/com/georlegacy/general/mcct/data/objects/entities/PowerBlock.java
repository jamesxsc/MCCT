package com.georlegacy.general.mcct.data.objects.entities;

import org.bukkit.Location;
import org.bukkit.Material;

import java.io.Serializable;

public class PowerBlock implements Serializable {

    public PowerBlock(Location l, Material type) {
        this.x = l.getBlockX();
        this.y = l.getBlockY();
        this.z = l.getBlockZ();
        this.worldName = l.getWorld().getName();
        this.type = type;
    }

    private final int x, y, z;

    private final String worldName;

    private final Material type;


    private transient long lastUse;

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

    public Material getType() {
        return type;
    }

    public long getLastUse() {
        return lastUse;
    }

    public void setLastUse(long unixTimeStamp) {
        this.lastUse = unixTimeStamp;
    }

}
