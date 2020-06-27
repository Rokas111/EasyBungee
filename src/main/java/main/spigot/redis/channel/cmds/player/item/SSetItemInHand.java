package main.spigot.redis.channel.cmds.player.item;

import api.spigot.world.item.SpigotItem;
import main.data.redis.channel.references.spigot.player.SetItemInHand;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class SSetItemInHand extends SetItemInHand {
    @Override
    public Boolean run(String[] args) {
        SpigotItem item = new SpigotItem(args[1]);
        if (Bukkit.getPlayer(args[0]) == null || Material.getMaterial(item.getMaterial()) == null) return false;
        ItemStack sitem = item.getDurability() != 0?new ItemStack(Material.valueOf(item.getMaterial()),item.getAmount(),item.getDurability()):new ItemStack(Material.valueOf(item.getMaterial()),item.getAmount());
        Bukkit.getPlayer(args[0]).setItemInHand(sitem);
        return true;
    }
}
