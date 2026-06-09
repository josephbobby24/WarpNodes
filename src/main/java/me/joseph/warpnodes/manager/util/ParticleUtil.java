package me.joseph.warpnodes.manager.util;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public class ParticleUtil {

    public static void spawnVerticalCircle(World world, Location center, double radius, Particle.DustOptions options) {
        Vector forward = center.getDirection().normalize();

        Vector right = forward.clone().crossProduct(new Vector(0, 1, 0));

        if (right.lengthSquared() == 0) {
            right = new Vector(1, 0, 0);
        }

        right.normalize();

        Vector up = right.clone().crossProduct(forward).normalize();

        for (int i = 0; i < 360; i += 10) {

            double radians = Math.toRadians(i);

            double x = Math.cos(radians) * radius;
            double y = Math.sin(radians) * radius;

            Vector offset = right.clone().multiply(x)
                    .add(up.clone().multiply(y));

            Location particleLoc = center.clone().add(offset);

            world.spawnParticle(
                    Particle.DUST,
                    particleLoc,
                    1,
                    options
            );
        }
    }

    public static List<Location> generateSphereLocations(Location center, int radius) {

        List<Location> locations = new ArrayList<>();

        double step = Math.PI / 12;

        for (double i = 0; i <= Math.PI; i += step) {

            double sin = Math.sin(i);
            double cos = Math.cos(i);

            for (double o = 0; o < Math.PI * 2; o += step) {

                double x = radius * sin * Math.cos(o);
                double y = radius * cos;
                double z = radius * sin * Math.sin(o);

                Location location = center.clone().add(x, y, z);
                locations.add(location);
            }
        }

        return locations;
    }

    public static void spawnSphere(World world, Location center, int radius, Particle.DustOptions dustOptions) {
        for (Location location : generateSphereLocations(center, radius)) {

            world.spawnParticle(
                    Particle.DUST,
                    location,
                    1,
                    0,
                    0,
                    0,
                    0,
                    dustOptions
            );
        }
    }

    public static List<Location> generateHorizontalCircleLocations(Location center, int radius) {
        List<Location> locations = new ArrayList<>();

        double step = Math.PI / 12;

        for (double angle = 0; angle < Math.PI * 2; angle += step) {

            double x = radius * Math.cos(angle);
            double z = radius * Math.sin(angle);

            locations.add(center.clone().add(x, 0, z));
        }

        return locations;
    }

    public static void spawnHorizontalCircle(World world, Location center, int radius, Particle.DustOptions dustOptions) {
        for (Location location : generateHorizontalCircleLocations(center, radius)) {

            world.spawnParticle(
                    Particle.DUST,
                    location,
                    1,
                    0,
                    0,
                    0,
                    0,
                    dustOptions
            );
        }
    }

    public static void drawLine(
            World world,
            Location origin,
            Vector direction,
            double maxLength,
            double spacing,
            Particle particle
    ) {
        Vector normalized = direction.clone().normalize();

        for (double d = 0; d < maxLength; d += spacing) {

            Location point = origin.clone().add(
                    normalized.clone().multiply(d)
            );

            world.spawnParticle(
                    particle,
                    point,
                    1,
                    0,
                    0,
                    0,
                    0
            );
        }
    }

    public static void drawLine(
            World world,
            Location origin,
            Vector direction,
            double maxLength,
            double spacing,
            Particle.DustOptions options
    ) {
        Vector normalized = direction.clone().normalize();

        for (double d = 0; d < maxLength; d += spacing) {

            Location point = origin.clone().add(
                    normalized.clone().multiply(d)
            );

            world.spawnParticle(
                    Particle.DUST,
                    point,
                    1,
                    0,
                    0,
                    0,
                    0,
                    options
            );
        }
    }

}
