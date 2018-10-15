package com.georlegacy.general.mcct.util;

import com.georlegacy.general.mcct.data.objects.entities.PowerBlock;
import org.bukkit.Location;
import org.bukkit.World;

public class LocationUtil {

    public static boolean is(int x, int y, int z, World world, int x2, int y2, int z2, World world2) {
        return x == x2 &&
                y == y2 &&
                z == z2 &&
                world.getName().equals(world2.getName());
    }

    public static boolean is(PowerBlock pb, Location toCompare) {
        return is(pb.getX(), pb.getY(), pb.getZ(), pb.getWorld(),
                toCompare.getBlockX(), toCompare.getBlockY(), toCompare.getBlockZ(), toCompare.getWorld());
    }

}
