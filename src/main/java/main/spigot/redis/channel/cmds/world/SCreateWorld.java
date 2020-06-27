package main.spigot.redis.channel.cmds.world;

import api.spigot.world.SpigotWorld;
import main.data.redis.channel.references.spigot.world.CreateWorld;
import main.spigot.SpigotMain;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.scheduler.BukkitRunnable;

public class SCreateWorld extends CreateWorld {
    @Override
    public Boolean run(String[] args) {
        SpigotWorld w = new SpigotWorld(args[0]);
        if (Bukkit.getWorld(w.getName()) != null) return false;
        new BukkitRunnable() {
            @Override
            public void run() {
                Bukkit.createWorld(new WorldCreator(w.getName()).environment(World.Environment.NORMAL).type(WorldType.NORMAL));
            }
        }.runTask(SpigotMain.pl);
        return true;
    }
}
