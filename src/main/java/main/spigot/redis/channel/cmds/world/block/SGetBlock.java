package main.spigot.redis.channel.cmds.world.block;

import api.spigot.world.SpigotBlock;
import api.spigot.world.SpigotLocation;
import main.data.redis.channel.references.spigot.block.GetBlock;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;

public class SGetBlock extends GetBlock {
    @Override
    public SpigotBlock run(String[] args) {
        SpigotLocation loc = new SpigotLocation(args[0]);
        if (Bukkit.getWorld(loc.getWorld().getName()) == null ) return null;
        Block b = Bukkit.getWorld(loc.getWorld().getName()).getBlockAt((int)loc.getX(),(int)loc.getY(),(int)loc.getZ());
        return new SpigotBlock(b.getType().name(),loc,b.getState().getData().getData());
    }
}
