package main.spigot.redis.channel.cmds.world.block;

import api.spigot.world.SpigotBlock;
import main.data.redis.channel.references.spigot.block.SetBlock;
import main.spigot.SpigotMain;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.scheduler.BukkitRunnable;

public class SSetBlock extends SetBlock {
    @Override
    public Boolean run(String[] args) {
        SpigotBlock b = new SpigotBlock(args[0]);
        if (Bukkit.getWorld(b.getLocation().getWorld().getName()) == null) return false;
        Block bl = Bukkit.getWorld(b.getLocation().getWorld().getName()).getBlockAt((int)b.getLocation().getX(),(int)b.getLocation().getY(),(int)b.getLocation().getZ());
        new BukkitRunnable() {
            @Override
            public void run() {
                if (!b.getMaterial().equals(bl.getType().name())) bl.setType(Material.getMaterial(b.getMaterial()));
                if (b.getBlockStateData() != (bl.getState().getData().getData())) bl.getState().setRawData((byte) b.getBlockStateData());
            }
        }.runTask(SpigotMain.pl);
        return true;
    }
}
