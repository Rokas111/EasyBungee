package main.spigot.redis.channel.cmds.world;

import api.spigot.world.SpigotLocation;
import main.data.redis.channel.references.spigot.world.SpawnEntity;
import main.spigot.SpigotMain;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.stream.Stream;

public class SSpawnEntity extends SpawnEntity {
    @Override
    public Boolean run(String[] args) {
        SpigotLocation loc = new SpigotLocation(args[0]);
        if (Bukkit.getWorld(loc.getWorld().getName()) == null || Stream.of(EntityType.values()).noneMatch(value -> value.name().equals(args[1]))) return false;
        new BukkitRunnable() {
            @Override
            public void run() {
                Bukkit.getWorld(loc.getWorld().getName()).spawnEntity(new Location(Bukkit.getWorld(loc.getWorld().getName()),loc.getX(),loc.getY(),loc.getZ(),loc.getPitch(),loc.getYaw()),EntityType.valueOf(args[1]));
            }
        }.runTask(SpigotMain.pl);
        Bukkit.getWorld(loc.getWorld().getName()).spawnEntity(new Location(Bukkit.getWorld(loc.getWorld().getName()),loc.getX(),loc.getY(),loc.getZ(),loc.getPitch(),loc.getYaw()),EntityType.valueOf(args[1]));
        return true;
    }

}
