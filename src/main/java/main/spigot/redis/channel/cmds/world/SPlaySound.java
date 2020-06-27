package main.spigot.redis.channel.cmds.world;

import api.spigot.SpigotSound;
import api.spigot.world.SpigotLocation;
import main.data.redis.channel.references.spigot.world.PlaySound;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;

import java.util.stream.Stream;

public class SPlaySound extends PlaySound {
    @Override
    public Boolean run(String[] args) {
        SpigotLocation loc = new SpigotLocation(args[0]);
        SpigotSound sound = new SpigotSound(args[1]);
        if (Bukkit.getWorld(loc.getWorld().getName()) == null || Stream.of(Sound.values()).noneMatch(value -> value.name().equals(sound.getName()))) return false;
        Bukkit.getWorld(loc.getWorld().getName()).playSound(new Location(Bukkit.getWorld(loc.getWorld().getName()),loc.getX(),loc.getY(),loc.getZ(),loc.getPitch(),loc.getYaw()),Sound.valueOf(sound.getName()),sound.getVolume(),sound.getPitch());
        return true;
    }
}
