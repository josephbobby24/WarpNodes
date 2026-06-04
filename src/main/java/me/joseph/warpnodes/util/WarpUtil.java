package me.joseph.warpnodes.util;

import me.joseph.warpnodes.WarpNodes;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.scheduler.BukkitRunnable;

public class WarpUtil {
    public static void warp(WarpNodes plugin, Location location, int radius, int modifier, Location toTeleport, int size) {
        World world = location.getWorld();
        Location loc2 = location.clone().add(0, modifier, 0);

        new BukkitRunnable() {
            int seconds = 0;

            @Override
            public void run() {
                if (seconds >= 10) {
                    for (Entity entity: location.getNearbyEntities(radius, radius, radius)) {
                        if (!(entity instanceof LivingEntity livingEntity)) continue;
                        world.playSound(toTeleport, Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1);
                        world.playSound(location, Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1);
                        livingEntity.teleport(toTeleport);
                    }
                    cancel();
                    return;
                }

                world.playSound(
                        location,
                        Sound.ITEM_TRIDENT_RETURN,
                        1,
                        2
                );

                ParticleUtil.spawnHorizontalCircle(
                        world,
                        location,
                        radius,
                        new Particle.DustOptions(
                                Color.WHITE,
                                size
                        )
                );
                ParticleUtil.spawnHorizontalCircle(
                        world,
                        loc2,
                        radius,
                        new Particle.DustOptions(
                                Color.WHITE,
                                size
                        )
                );
                seconds++;
            }
        }.runTaskTimer(plugin, 0, 20);
    }
}
