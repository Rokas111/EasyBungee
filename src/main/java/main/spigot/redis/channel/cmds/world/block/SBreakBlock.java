package main.spigot.redis.channel.cmds.world.block;

import api.spigot.world.SpigotLocation;
import main.data.redis.channel.references.spigot.block.BreakBlock;
import main.spigot.SpigotMain;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

public class SBreakBlock extends BreakBlock {
    @Override
    public Boolean run(String[] args) {
        SpigotLocation loc = new SpigotLocation(args[0]);
        if (Bukkit.getWorld(loc.getWorld().getName()) == null ) return false;
        new BukkitRunnable() {
            @Override
            public void run() {
                Bukkit.getWorld(loc.getWorld().getName()).getBlockAt((int)loc.getX(),(int)loc.getY(),(int)loc.getZ()).breakNaturally();
            }
        }.runTask(SpigotMain.pl);
        return true;
    }
}
