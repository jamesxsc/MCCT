package com.georlegacy.general.mcct.tasks.async;

import com.georlegacy.general.mcct.MCCTPlugin;
import com.georlegacy.general.mcct.data.objects.entities.PowerBlock;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.scheduler.BukkitRunnable;

public class PowerBlockParticleTask extends BukkitRunnable {

    @Override
    public void run() {
        if (MCCTPlugin.getInstance() == null ||
                MCCTPlugin.getInstance().getDataStore() == null ||
                MCCTPlugin.getInstance().getDataStore().getPowerBlocks() == null)
            return;

        for (PowerBlock pb : MCCTPlugin.getInstance().getDataStore().getPowerBlocks()) {
            Location loc = new Location(Bukkit.getWorld(pb.getWorldName()),
                    (double) pb.getX(), (double) pb.getY(), (double) pb.getZ());
            loc.setX(loc.getX() + .5);
            loc.setY(loc.getY() + .5);
            loc.setZ(loc.getZ() + .5);
            loc.getWorld().spawnParticle(Particle.REDSTONE, loc, 15, .30, .30, .30);
        }
    }

}
