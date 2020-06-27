package main.spigot.redis.channel.cmds.player.misc;

import api.spigot.SpigotServer;
import api.spigot.player.SpigotPlayer;
import api.spigot.world.SpigotLocation;
import api.spigot.world.SpigotWorld;
import main.data.redis.channel.references.spigot.player.GetLocation;
import org.bukkit.Bukkit;
import org.bukkit.Location;

public class SGetLocation extends GetLocation {
    @Override
    public SpigotLocation run(String[] args) {
        if (Bukkit.getPlayer(args[0]) == null) return null;
        Location loc = Bukkit.getPlayer(args[0]).getLocation();
        return new SpigotLocation(loc.getX(),loc.getY(),loc.getZ(),loc.getPitch(),loc.getYaw(),new SpigotWorld(new SpigotPlayer(args[0]).getCurrentServer().getName(),loc.getWorld().getName()));
    }
}
