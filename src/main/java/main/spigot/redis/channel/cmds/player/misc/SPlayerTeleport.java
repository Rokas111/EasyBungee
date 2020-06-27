package main.spigot.redis.channel.cmds.player.misc;

import api.spigot.world.SpigotLocation;
import main.data.redis.channel.references.spigot.player.PlayerTeleport;
import org.bukkit.Bukkit;
import org.bukkit.Location;

public class SPlayerTeleport extends PlayerTeleport {
    @Override
    public Boolean run(String[] args) {
        SpigotLocation loc = new SpigotLocation(args[1]);
        if (Bukkit.getPlayer(args[0]) == null || Bukkit.getWorld(loc.getWorld().getName()) == null) return false;
        Bukkit.getPlayer(args[0]).teleport(new Location(Bukkit.getWorld(loc.getWorld().getName()),loc.getX(),loc.getY(),loc.getZ(),loc.getPitch(),loc.getYaw()));
        return true;
    }
}
