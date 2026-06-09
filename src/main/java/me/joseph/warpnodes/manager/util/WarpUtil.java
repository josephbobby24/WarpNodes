package me.joseph.warpnodes.manager.util;

import me.joseph.warpnodes.WarpNodes;
import net.kyori.adventure.text.Component;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
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
                    new BukkitRunnable() {
                        int duration = 0;

                        @Override
                        public void run() {
                            if (duration >= 3) {
                                cancel();
                                return;
                            }

                            for (int i = 0; i < 10; i++) {
                                ParticleUtil.spawnHorizontalCircle(
                                        world,
                                        toTeleport.clone().add(0, i, 0),
                                        radius,
                                        Particle.END_ROD
                                );
                            }

                            duration ++;
                        }
                    }.runTaskTimer(plugin, 0, 20);
                    for (Entity entity: location.getNearbyEntities(radius, radius, radius)) {
                        if (!(entity instanceof LivingEntity livingEntity)) continue;
                        world.playSound(location, Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1);
                        livingEntity.teleport(toTeleport);
                        world.playSound(toTeleport, Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1);

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
