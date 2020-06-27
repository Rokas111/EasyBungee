package main.spigot.redis.channel.cmds.world;

import api.spigot.world.SpigotLocation;
import api.spigot.world.item.SpigotItem;
import main.data.redis.channel.references.spigot.world.DropItem;
import main.spigot.SpigotMain;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class SDropItem extends DropItem {
    @Override
    public Boolean run(String[] args) {
        SpigotLocation loc = new SpigotLocation(args[0]);
        SpigotItem item = new SpigotItem(args[1]);
        if (Bukkit.getWorld(loc.getWorld().getName()) == null || Material.getMaterial(item.getMaterial()) == null) return false;
        ItemStack sitem = item.getDurability() != 0?new ItemStack(Material.valueOf(item.getMaterial()),item.getAmount(),item.getDurability()):new ItemStack(Material.valueOf(item.getMaterial()),item.getAmount());
        new BukkitRunnable() {
            @Override
            public void run() {
                Bukkit.getWorld(loc.getWorld().getName()).dropItem(new Location(Bukkit.getWorld(loc.getWorld().getName()),loc.getX(),loc.getY(),loc.getZ(),loc.getPitch(),loc.getYaw()),sitem);
            }
        }.runTask(SpigotMain.pl);
        return true;
    }
}
